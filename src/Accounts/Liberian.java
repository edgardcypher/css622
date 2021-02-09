package Accounts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Constants.AccountStatus;
import Driver_test.BadDateFormatException;
import Driver_test.BadInputException;
import manageBook.Book;
import manageBook.BookItem;

public class Liberian extends Account {
	
	
	public Liberian(Person person, String id, String password,String username, AccountStatus status, String accountType) {
		super(person, id, password,username, status, accountType);
	}

	public boolean addBook(List<BookItem> listBooks, Scanner scanner ) {
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
						return false;
					} else if(listBooks.size() == count) {
						break;
					}
				}
			}
			listBooks.add(newBookItem);
			System.out.println("Book was sucessfully added");
			return true;
		}
		System.out.println("Book was not sucessfully added");
		return false;
	
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
	public void issueBook() {
		System.out.println("issuing a book");
	}
	public void checkOverDueBook() {
		System.out.println("checking overdue book");
		
	}
	public void logout() {
		System.out.println("you log out successfully");
	}
   /*help to add a new account to the list if the account does not exist yets
    * */
	public boolean createAnAccount(List<Account> listOfAccounts, Account accountToCreate) {
		//Precondition: the new account should not exist
		// Postcondition: add a new account to a list of accounts
			if(accountToCreate instanceof Member) {
				if(!isAccountExist(listOfAccounts,accountToCreate.getId()) 
						&& isUsernameExist(listOfAccounts, accountToCreate.getUsername())) {// check if account already exist
					listOfAccounts.add(accountToCreate);
					System.out.println("membership account has been created successfully");
					return true;
				}
			} else if (accountToCreate instanceof Liberian) {
				if(!isAccountExist(listOfAccounts,accountToCreate.getId())) {//check if account already exist
					listOfAccounts.add(accountToCreate);
					System.out.println("liberian account has been created successfully");
					return true;
				}
		}
		return false;
	}
	
	
	/*helps to go through the list of accounts and found the one to delete
	 * */
	public boolean deleteAnAccount(List<Account> listOfAccounts, String accountToDeleteId) {
		//Precondition: the account should exist in the list of account
		// Postcondition: a new updated list of accounts without the deleted one
		for (Account thisMember : listOfAccounts) {
			if(thisMember instanceof Member) {
				if(thisMember.getId().equals(accountToDeleteId)) {
					listOfAccounts.remove(thisMember);
					System.out.println("membership account with id: "+accountToDeleteId+" has been deleted successfully");
					return true;
				}
			}else if (thisMember instanceof Liberian) {
				if(thisMember.getId().equals(accountToDeleteId)) {
					listOfAccounts.remove(thisMember);
					System.out.println("Liberian account has been deleted successfully");
					return true;
				}
			}
		}
		System.out.println("there is not account with that id");
		return false;
	}
	/*help to check if the account which is about to be created,deleted,update exists or not */
	private boolean isAccountExist(List<Account> listAccounts, String  accountId) {
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
		writeNewBookInfoIntoTable(newBookInfo,true);// write the book information in the file
		return newBook;
	}
	
	/*Function to write book information to the file
	 * */
	public void writeNewBookInfoIntoTable(String newBookInfo, boolean writeEndFile) {
		BufferedWriter bufferWrite = null;
	      try {
		 String mycontent = newBookInfo;
	         
		 File file = new File("booksTable.txt");//Specify the file name where to write

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
	
	
	/*help to search and return all members which have the searched name in their name */
	public List<Account> searchMemberByName(List<Account> listAccounts, String nameMmber) {
		Search<Account,String> searchAccounts = new Search<Account,String>();
		return searchAccounts.searchByName(listAccounts, nameMmber);
	}
	/*help to search and return all books which have the searched name in as author name */
	public List<BookItem> searchBookByAuhtorName(List<BookItem> listBooks, String authorName) {
		Search<BookItem,String> searchBooks = new Search<BookItem,String>();
		return searchBooks.searchByName(listBooks, authorName);
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
