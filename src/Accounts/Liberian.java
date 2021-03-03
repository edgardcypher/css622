package Accounts;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Constants.AccountStatus;
import Driver_test.BadDateFormatException;
import Driver_test.BadInputException;
import manageBook.Book;
import manageBook.BookItem;
import manageBook.BookItem.BookStatus;

public class Liberian extends Account implements  Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookItem bookToIssue;
	private Member memberWhoBorrow;
	

	public void run() {
		this.issueBook(this.getBookToIssue(), this.getMemberWhoBorrow());
	}
	
	public void issueBook(BookItem bookToIssue, Member memberWhoBorrow) {
		if(memberWhoBorrow.getStatus().equals(AccountStatus.Active.toString()) ) {
			if(bookToIssue.getStatus().equals(BookStatus.available.toString())) {
				bookToIssue.setStatus(BookStatus.borrowed.toString());
				int borrowedBook = memberWhoBorrow.getTotalBookBorrored();
				memberWhoBorrow.setTotalBookBorrored(borrowedBook++);
				System.out.println("the book with Bookid "+bookToIssue.getBookId()+ " has been issued to member with memberid: "+memberWhoBorrow.getId());
			}
			else {
				System.out.println("Sorry that book is not avalaible now");
			}
		}
		else {
			System.out.println("Sorry you the account member is not active");
		}
	}

	
	
	public Liberian(Person person, String id, String password,String username, AccountStatus status, String accountType) {
		super(person, id, password,username, status.toString(), accountType);
	}
	public Liberian(String id, String password,String username, AccountStatus status) {
		super(id, password,username, status);
		this.setTypeAccount("Liberian");
	}
	
	public Member getMemberWhoBorrow() {
		return memberWhoBorrow;
	}



	public void setMemberWhoBorrow(Member memberWhoBorrow) {
		this.memberWhoBorrow = memberWhoBorrow;
	}



	public BookItem getBookToIssue() {
		return bookToIssue;
	}



	public void setBookToIssue(BookItem bookToIssue) {
		this.bookToIssue = bookToIssue;
	}

	public BookItem addBook(List<BookItem> listBooks, Scanner scanner ) {
		// precondition the book which has to be added should not exist in the list 
		// Postcondition: an updated list of books listBooks which contains the new newBookItem
		
		BookItem newBookItem = null;
		try {
			newBookItem = captureAndWriteBookInformation();
		}
		catch (BadInputException | BadDateFormatException e) {// handle exception in case of bad input
			System.out.println(e.getMessage());  // message to display in the console when the exception raise
		}
		int count = 0;
		if(newBookItem != null) {
			for (Book book : listBooks) {
				count++;
				if(book instanceof BookItem) {
					if(((BookItem)book).getBookId().equals(newBookItem.getBookId())) {// downcasting
						System.out.println("that book exist already it cannot be added");
						return null;
					} else if(listBooks.size() == count) {
						break;
					}
				}
			}
			listBooks.add(newBookItem);
			System.out.println("Book was sucessfully added");
			return newBookItem;
		}
		System.out.println("Book was not sucessfully added");
		return null;
	
	}
	public boolean updateBook(List<Book> listBooks, BookItem bookToUpdate) {
		// precondition the book which has to be updated should exist in the list
		// Postcondition: an list of books listBooks which contains an updated BookItem
		int count = 0;
		for (Book book : listBooks) {
			count++;
			if(book instanceof BookItem) {
				if(((BookItem)book).getBookId().equals(bookToUpdate.getBookId())) {// downcasting
					listBooks.remove(book);
					break;
				}else if (listBooks.size() == count) {
					System.out.println("that book does not exist");
					return false;
				}
			}
		}
		listBooks.add(bookToUpdate);
		System.out.println("book has been updated");
		return true;
	}
	public boolean deleteBook(List<BookItem> listBooks, String bookId) {
		// precondition the book which has to be deleted should exist in the list
		// Postcondition: an updated list of books listBooks which doesnt contains the deleted BookItem
		int count = 0;
		for (Book book : listBooks) {
			count++;
			if(book instanceof BookItem) {
				if(((BookItem)book).getBookId().equals(bookId)) {
					listBooks.remove(book);
					System.out.println("the book with the id: "+bookId+ " has been sucessfully removed" );
					break;
				}else if (listBooks.size() == count) {
					System.out.println("The book you want to delete does not exist");
					return false;
				}
			}
			
		}
		return true;
	}

	public void checkOverDueBook() {
		System.out.println("checking overdue book");
		
	}
	public void logout() {
		System.out.println("you log out successfully");
	}
   /*help to add a new account to the list if the account does not exist yets
    * */
	public Account createAnAccount() {
		//Precondition: the new account should not exist
		// Postcondition: add a new account to a list of accounts
		Account newlyCreatedAccount = null;
		try {
			newlyCreatedAccount = captureAccoutInformation();
		} catch (BadInputException e) {
			System.out.println("you entered a wrong format value");
		}
		return newlyCreatedAccount;
	}
	
	/*helps to go through the list of accounts and found the one to delete
	 * */
	public boolean deleteAnAccount(List<Account> listOfAccounts, String accountToDeleteId) {
		// Postcondition: a new updated list of accounts without the deleted one
		List<Account> newUpdatedAccount = listOfAccounts.stream()
				.filter(account -> !account.getId().toLowerCase().equals(accountToDeleteId.toLowerCase()))
				.collect(Collectors.toList());
		if(newUpdatedAccount.size() != listOfAccounts.size() ) {
			System.out.println("membership account with id: "+accountToDeleteId+" has been deleted successfully");
			writeAccountDataInBinFile(newUpdatedAccount);// update the binary file with the new list of accounts
			return true;
		}
		System.out.println("membership account with id: "+accountToDeleteId+" was not found");
		return false;
	}
	
	/*help to check if the account which is about to be created,deleted,update exists or not */
	public boolean isAccountExist(List<Account> listAccounts, String  accountId) {
	//precondition:	list of existing accounts and accountid
	//Postcondition: return true if the account already exists in the list unless it returns false
	boolean foundAnAccount = false;
		for (Account account : listAccounts) {
			if(account.getId().equals(accountId)) {
				foundAnAccount = true;
				return foundAnAccount;
			}else {
				continue;
			}
		}
		return foundAnAccount;
	}
	
	
	/*Help to capture the new book info from user input. those information will be saved in the file.
	 * */
	private BookItem captureAndWriteBookInformation() throws BadInputException, BadDateFormatException {
		//Postcondition: will return a new object that represent a new book
		
		Scanner scanner = new Scanner(System.in).useDelimiter("\n");
		BookItem newBook = new BookItem();
		System.out.println(" \nPlease enter the information of the new book");
		String newBookInfo = "" ;
		try {
		System.out.print("BookId:");
		String bookId = scanner.next();
		newBook.setBookId(bookId);
		newBookInfo+=bookId;
		
		
		System.out.print("ISBN(13 digits number):");
		long iSBN = scanner.nextLong();
		newBook.setISBN(iSBN);
		newBookInfo+=","+String.valueOf(iSBN);
		
		System.out.print("Title of Book:");
		String title = scanner.next();
		newBook.setTitle(title);
		newBookInfo+=","+title;
		
		System.out.print("Author of Book:");
		String author = scanner.next();
		newBook.setAuthor(author);
		newBookInfo+=","+author;
		
		System.out.print("Subject of Book:");
		String subject = scanner.next();
		newBook.setSubject(subject);
		newBookInfo+=","+subject;
		
		System.out.print("Publisher of Book:");
		String publisher = scanner.next();
		newBook.setPublisher(publisher);
		newBookInfo+=","+publisher;
		
		System.out.print("Language of Book:");
		String language = scanner.next();
		newBook.setLanguage(language);
		newBookInfo+=","+language;
		
		System.out.print("Total pages of Book:");
		int pages = scanner.nextInt();
		newBook.setPages(pages);
		newBookInfo+=","+pages;
		
		System.out.print("Rack number:");
		int rack = scanner.nextInt();
		newBook.setRackNumber(rack);
		newBookInfo+=","+rack;
		
		
		System.out.print("Price of Book:");
		int priceBook = scanner.nextInt();
		newBook.setPrice("$"+Integer.toString(priceBook));
		newBookInfo+=","+newBook.getPrice();
		
		System.out.print("Date publication of Book(MM/DD/YYYY):");
		String datePublication = scanner.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		newBook.setPublicationDate(LocalDate.parse(datePublication, formatter).atStartOfDay());
		newBookInfo+=","+datePublication;
		
		System.out.print("Date purchase of Book(MM/DD/YYYY):");
		String datePurchase = scanner.next();
		newBook.setDateOfPurchase(LocalDate.parse(datePurchase, formatter).atStartOfDay());
		newBookInfo+=","+datePurchase;
		
		System.out.print("Format of Book:");
		String format = scanner.next();
		newBook.setFormat(format);
		newBookInfo+=","+format;
		
		System.out.print("Location Book on the rack:");
		String locationBookOnrack = scanner.next();
		newBook.setRackLocation(locationBookOnrack);
		newBookInfo+=","+locationBookOnrack;
		
		newBookInfo+= buildStringFromBook(newBook);
		} catch(InputMismatchException e) {
			throw new BadInputException();
		} catch (DateTimeParseException e) {
			throw new BadDateFormatException();
		} 
		finally {
			scanner.close();
		}
		writeNewBookInfoIntoFile(newBookInfo,true);// write the book information in the file
		return newBook;
	}
	
	/*Function to write book information to the file
	 * */
	public void writeNewBookInfoIntoFile(String newBookInfo, boolean writeEndFile) {
		BufferedWriter bufferWrite = null;
	      try {
		 String mycontent = newBookInfo;
	         
		 File file = new File("booksFile.txt");//Specify the file name where to write

		 /* check if the file exists if not it will be created*/
		  if (!file.exists()) {
		     file.createNewFile();
		  }

		  FileWriter fw = new FileWriter(file,writeEndFile);
		  bufferWrite = new BufferedWriter(fw);
		  bufferWrite.write(mycontent);
	          System.out.println("Book information written Successfully");

	      } catch (IOException ioe) {
		   ioe.printStackTrace();
		}
		finally
		{ 
		   try{
		      if(bufferWrite!=null)
			 bufferWrite.close();
		   }catch(Exception ex){
		       System.out.println("Error in closing the BufferedWriter"+ex);
		    }
		}
		
	}
	/*Collect all the information for the new account and save them in the binary file if the accountid does not exist*/
	private Account captureAccoutInformation() throws BadInputException {
		
		Scanner scanner = new Scanner(System.in).useDelimiter("\n");
		Person person = new Person();
		System.out.print("\n Enter the personal information of the account you want to open:\n");
		System.out.print("Fullname:");
		String fullname = scanner.next();
		person.setName(fullname);
		
		System.out.print("Email:");
		String email = scanner.next();
		person.setEmail(email);
		
		System.out.print("Phone number:");
		String phoneNumber = scanner.next();
		person.setPhoneNumber(phoneNumber);
		
		System.out.print("\n Now enter the address information of the account holder you want to open:\n");
		
		Address address = new Address();
		
		System.out.print("Street number:");
		int streetNumber = scanner.nextInt();
		address.setStreetNumber(streetNumber);
		
		System.out.print("Street Name:");
		String streetName = scanner.next();
		address.setStreet(streetName);
		
		System.out.print("City Name:");
		String city = scanner.next();
		address.setCity(city);
		
		System.out.print("State Name:");
		String state = scanner.next();
		address.setState(state);
		
		System.out.print("Country Name:");
		String country = scanner.next();
		address.setCountry(country);
		
		System.out.print("Zip code:");
		String zipCode = scanner.next();
		address.setZipCode(zipCode);
		
		person.setAddress(address);
		
		System.out.print("\n Now enter the account information of the member you want to open:\n");
		
		Account account = new Member();
		account.setPerson(person);
		
		System.out.print("Username:");
		String username = scanner.next();
		account.setUsername(username);
		
		System.out.print("account Id:");
		String accountId = scanner.next();
		account.setId(accountId);
		
		System.out.print("account password:");
		String password = scanner.next();
		account.setPassword(password);
		account.setTypeAccount("Member");
		
		List<Account>  accounts = readAllAccount();
		
		if(!isAccountExist(accounts, account.getId())) {
			accounts.add(account);
			writeAccountDataInBinFile(accounts);
			System.out.println("Great! The account was succesfully created");
		}else {
			System.out.println("Sorry the accountid already exist;");
		}
    
		scanner.close();
		return account;
	}
	
	//Write in the binary file all accounts contain in the list
	private void writeAccountDataInBinFile(List<Account> accounts) {
		// Precondition: a list of accounts
		// poscondition: a binary file with data of newly created account
		try {
			
			try (ObjectOutputStream outfile = new ObjectOutputStream(new FileOutputStream("accounts.dat"));){
				for (Account account : accounts) {
					outfile.writeObject(account);
				}
				outfile.flush();
			}
		}
		catch (FileNotFoundException ex)
	     {
	         System.out.println("FileNotFoundException"); 
	         ex.printStackTrace();   
	     }

	     catch (IOException ex)
	     {
	         System.out.println("IOException");
	         ex.printStackTrace();    
	     }
	}
	
	/*Go through the binary file and read all available account information file*/
	public List<Account>  readAllAccount() {
		//Postcondition: read each object from the file and add it to the list allAccounts which will be returned

		List<Account> allAccounts = new ArrayList<Account>();
		try {
			
			FileInputStream br = new FileInputStream("accounts.dat");
				if(br.read() != -1) {// make sure the binary file is not empty
					ObjectInputStream infile = new ObjectInputStream(new FileInputStream("accounts.dat"));
					while (true)
					{
						try {
							Account retrievedAccount = (Account)(infile.readObject());// read the account object
							allAccounts.add(retrievedAccount);// add the account object to the list
						} catch (EOFException ex) {
							infile.close();
							break;
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					} 
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException"); 
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error during the reading of data");
			e.printStackTrace();
		}
		return allAccounts;
	}
	
	//use a lamda function and stream to find a specific account
	public List<Account> findMemberFromSpecificCity(List<Account> accounts, String city) {
		//PostCondition: list of account holder which live in the specified city
		
		Predicate<Account> filterForCity = c -> c.getPerson().getAddress().getCity().toLowerCase().equals(city.toLowerCase());
		List<Account> memberLivingInThisCity = accounts
				  .stream()
				  .filter(filterForCity)
				  .collect(Collectors.toList());
		return memberLivingInThisCity;
	}
	
	
	/*help to search and return all members which have the searched name in their name */
	public List<Account> searchMemberByName(List<Account> listAccounts, String nameMember) {
		Search<Account,String> searchAccounts = new Search<Account,String>();// instantiate the generic class
		return searchAccounts.searchByName(listAccounts, nameMember);
	}
	
	/*help to search and return all members which have the searched username */
	public List<Account> searchMemberByUsername(List<Account> listAccounts, String username) {
		//Precondition: a list of accounts and the username to look for
		// poscondition: a new list of accounts which contains accounts with matching username
		Predicate<Account> filterForMatchingUsername = c -> c.getUsername().toLowerCase().equals(username.toLowerCase()) 
				|| c.getUsername().toLowerCase().matches(".*"+username.toLowerCase()+".*");
		List<Account> memberWithThatUsername = listAccounts
				  .stream()
				  .filter(filterForMatchingUsername)
				  .collect(Collectors.toList());
		return memberWithThatUsername;
	}
	
	/*help to search and return all books which have the searched name in as author name */
	public List<BookItem> searchBookByAuhtorName(List<BookItem> listBooks, String authorName) {
		Search<BookItem,String> searchBooks = new Search<BookItem,String>(); // instantiate the Generic class
		return searchBooks.searchByName(listBooks, authorName); // call the generic method
	}
	
	public  String buildStringFromBook (BookItem book) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		return book.getBookId()+","+book.getISBN()+","+book.getTitle()+
				","+book.getAuthor()+","+book.getSubject()+","+book.getPublisher()
				+","+book.getLanguage()+","+book.getPages()+","+book.getRackNumber()
				+","+book.getPrice()+","+book.getPublicationDate().format(formatter)+","+book.getDateOfPurchase().format(formatter)
				+","+book.getFormat()+","+book.getRackLocation()+","+book.getStatus()+"\n";
	}
	
 }
