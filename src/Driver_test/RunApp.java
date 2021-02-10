package Driver_test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


import Accounts.Account;
import Accounts.Address;
import Accounts.Credential;
import Accounts.Liberian;
import Accounts.Member;
import Accounts.Person;
import Constants.AccountStatus;
import DisplayMenu.DisplayLiberianMainMenu;
import DisplayMenu.DisplayMemberMainMenu;
import DisplayMenu.Menu;
import manageBook.Book;
import manageBook.BookItem;

public class RunApp {

	public static void main(String[] args) {
		List<BookItem> allBooks = new ArrayList<>();// will hold information about each book
		// create a list of credential	
		LinkedList<Credential> credentialList = new LinkedList<>();//create an empty list of credential
		Scanner scanner = new Scanner(System.in);
		Address addressLiberian = new Address("Manchester", "Summer St", "01944", "USA","MA", 92);
		Person liberianPerson = new Person("Margaret J. Witman", addressLiberian, "MargaretJWitman@jourrapide.com", "571-921-5508");
		
		Address addressMember_1 = new Address("Holden", "Bullard St", "01520", "USA","MA", 325);
		Person memberPerson_1 = new Person("Ora J. Whitty", addressMember_1, "OraJWhitty@dayrep.com", "310-269-0220");
		LocalDateTime myDate = LocalDateTime.of(2019, 10, 5, 11, 10);
		String dateOfBecomingMember1 = myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		
		Address addressMember_2 = new Address("Bradford", "Coal St", "16701", "USA","PA", 3119);
		Person memberPerson_2 = new Person("Nathan T. Gardner", addressMember_2, "NathanTGardner@jourrapide.com", "814-363-1687");
		LocalDateTime myDateObj = LocalDateTime.of(2020, 11, 4, 11, 10);
		String dateOfBecomingMember2 = myDateObj.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		
		
		Account liberianAccount = new Liberian(liberianPerson,"lib0231","alpha01","Jmagaret",AccountStatus.Active,"Liberian"); // upcasting
		Account member_1Account = new Member(memberPerson_1,"memb123","margared21","Jora",AccountStatus.Active,dateOfBecomingMember1,1,"Member"); // upcasting
		Account member_2Account = new Member(memberPerson_2,"memb124","nathan21","t_nathan",AccountStatus.Active,dateOfBecomingMember2,1,"Member"); // upcasting
		
		List<Account> allAccounts = new ArrayList<Account>();
		allAccounts.add(liberianAccount);
		allAccounts.add(member_1Account);
		allAccounts.add(member_2Account);
		
		int choice = 0;
		try {
			choice = theLogger(scanner);
		} catch (BadInputException e) {
			System.out.println(e.getMessage());
		}
		readAccountCredential(credentialList,scanner);// read all credentials from file
		try {
			readBookTable(allBooks,scanner);// read all books information from file
		} catch (BadInputException e) {
			System.out.println(e.getMessage());

		}
		
		if(choice == 1) {
			Menu liberianMenu = new DisplayLiberianMainMenu();
			liberianMenu.displayLogInMenu(scanner,"Liberian");
			if(liberianMenu.checkCredential(credentialList,liberianMenu.getMemberId(), liberianMenu.getPassword())) {
				System.out.println("you are sucessful logged in as a liberian");
				liberianMenu.displayMenu(liberianAccount.getUsername()); // polymorphism
				try {
					handleUserChoice(scanner,liberianAccount,allBooks,allAccounts);
				} catch (BadInputException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("sorry wrong credential");
				System.exit(0);
			}
		}else if (choice == 2)  {
			Menu memberMenu = new DisplayMemberMainMenu();
			memberMenu.displayLogInMenu(scanner,"Member");
			if(memberMenu.checkCredential(credentialList,memberMenu.getMemberId(), memberMenu.getPassword())) {
				System.out.println("you are sucessful logged in as a liberian");
				memberMenu.displayMenu(member_1Account.getUsername()); // polymorphism
				Member member = (Member)member_1Account; // downcasting
				member.borrowBook();
				member.closeAccount();
				System.exit(0);
			} else {
				System.out.println("sorry wrong credential");
				System.exit(0);
			}
		} else {
			System.out.println("the program will stop here because you entered a wrong choice");
			System.exit(0);
		}
		
		scanner.close();

	}
	
	private static int theLogger(Scanner scanner) throws BadInputException {
		//predondition: a scanner object which will help to read the file
		//postcondition: a int representing user choice 
		
		System.out.println(" Please what kind of user would you like to Log as?:\n");
		System.out.println(" 1- Liberian:\n");
		System.out.println(" 2- Member:\n");
		System.out.print(" Enter your choice 1 or 2:");
		int choice = 0;
		try {
			choice = scanner.nextInt();
		}catch (InputMismatchException e) { // an exception will be raised if the user enter a string or character
			throw new BadInputException(); // call user-defined exception
		}
		return choice;
	}
	
	/*
	 * Read account_credentials_db.txt file to find all the saved credentials
	 * */
	private static void readAccountCredential(LinkedList<Credential> CredentialList,Scanner scanner) {
		//Precondition1: a variable list which will hold all credential found in the file
		//predondition2: a scanner object which will help to read the file
		//Postcondition: return a list of all credentials found in the file
		
		File file;
		try {
			 file = new File("account_credentials_db.txt");// create a file object with the pathfilename 
			scanner = new Scanner(file);// read the file
			
		} catch (FileNotFoundException e) {// return a catch in case the file is not found
			System.out.println("file for credentials not found");
		}
		if(scanner != null) {
			while (scanner.hasNextLine()) {// go through each file line
				Credential credential = new Credential();
				String username = scanner.next();// read the first element in the line until the space delimiter
				credential.setUsrname(username);
				String memberId = scanner.next();
				credential.setMemberId(memberId);
				String pswd = scanner.next();
				credential.setPwd(pswd);
				CredentialList.add(credential);
			}
		}
	}
	
	
	/*
	 * Read account_credentials_db.txt file to find all the saved credentials
	 * */
	private static void readBookTable(List<BookItem> listOfbooks,Scanner scanner) throws BadInputException {
		//Precondition1: a variable list which will hold all books information found in the file
		//predondition2: a scanner object which will help to read the file
		//Postcondition: return a list of all books found in the file
		
		try {
			File file = new File("booksTable.txt");// create a file object with the pathfilename 
			scanner = new Scanner(file);// read the file
			scanner.useDelimiter(",");

			if(scanner != null) {
				while (scanner.hasNextLine()) {// go through each file line
					String thisLine = scanner.nextLine();
					String[] words = thisLine.split(",");
					BookItem bookItem = new BookItem();// bookItem object where each information retrieved wiil be saved
					
					String bookId = words[0];
					bookItem.setBookId(bookId);
					
					String isbn = words[1];
					bookItem.setISBN(Long.parseLong(isbn));

					String title = words[2];
					bookItem.setTitle(title);
										
					String author = words[3];
					bookItem.setAuthor(author);
					
					String subject = words[4];
					bookItem.setSubject(subject);
					
					String publisher = words[5];
					bookItem.setPublisher(publisher);
					
					String language = words[6];
					bookItem.setLanguage(language);
					
					String totalPages = words[7];
					bookItem.setPages(Integer.parseInt(totalPages));
					
					String rackNumber = words[8];
					bookItem.setRackNumber(Integer.parseInt(rackNumber));
					
					String price = words[9];
					bookItem.setPrice(price);
					
					String publicationDate = words[10];
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					bookItem.setPublicationDate(LocalDate.parse(publicationDate, formatter).atStartOfDay());
					
					String dateOfpurchase = words[11];
					bookItem.setDateOfPurchase(LocalDate.parse(dateOfpurchase, formatter).atStartOfDay());
					
					String format = words[12];
					bookItem.setFormat(format);
					
					String bookLocation = words[13];
					bookItem.setRackLocation(bookLocation);
					
					String avaibilityBook = words[14];
					bookItem.setStatus(avaibilityBook);
					
					listOfbooks.add(bookItem);
				}
			}
		} catch (FileNotFoundException e) {// return a catch in case the file is not found
			System.out.println("file of books information is not found");
		}catch (InputMismatchException e) {// return a catch in case the wrong type of variable is read
			throw new BadInputException(); 
		}
	}
	
	private static void handleUserChoice(Scanner scanner , Account user, List<BookItem> listBooks, List<Account> allAccounts) throws BadInputException {
		int choice = 0;
		String name,id;
		boolean hasBeenDeleted;
		Liberian liberian = ((Liberian) user);
		try {
			choice = scanner.nextInt();
		}catch (InputMismatchException e) {
			throw new BadInputException(); 
		}
		
		System.out.println("you entered: "+choice);
		if(user instanceof Liberian) {
			switch (choice) {
			case 1:
				((Liberian) user).addBook(listBooks, scanner);
				break;
			case 2:
//				((Liberian) user).updateBook(listBooks, bookToUpdate)
				break;
			case 3:
				System.out.print("Please enter the id of the book you want to delete:");
				try {
					id = scanner.next();
				}catch (InputMismatchException e) {
					throw new BadInputException(); 
				}
				hasBeenDeleted= liberian.deleteBook(listBooks, id);
				String bookinfo = "";
				if(hasBeenDeleted) {
				for (Book book : listBooks) {
					if (book instanceof BookItem) {
						bookinfo += liberian.buildStringFromBook((BookItem)book);
					}
				}
				liberian.writeNewBookInfoIntoTable(bookinfo,false);
				}
				break;
			case 4:
				((Liberian) user).issueBook();
				break;
			case 5:
				((Liberian) user).checkOverDueBook();
				break;
			case 6:
//				((Liberian) user).createAnAccount(listOfAccounts, accountToCreate);
				break;
			case 7:
				System.out.println("\nList available account:");
				displayElement(allAccounts);
				System.out.print("Please enter the id of the account you want to delete:");
				try {
					id = scanner.next();
				}catch (InputMismatchException e) {
					throw new BadInputException(); 
				}
				hasBeenDeleted = liberian.deleteAnAccount(allAccounts, id);
				if(hasBeenDeleted) {
					System.out.println("\nUpdated List accounts available:");
					displayElement(allAccounts);
				}
				break;
			case 8:
				displayElement(allAccounts);
				System.out.print("Please enter the member name you are looking for:");
				try {
					name = scanner.next();
				}catch (InputMismatchException e) {
					throw new BadInputException(); 
				}
				List<Account> foundAccount = ((Liberian) user).searchMemberByName(allAccounts, name);
				System.out.println(" Below is the result of the search:");
				displayElement(foundAccount);
				break;
			case 9:
				System.out.print("Please enter the author name of the book you are looking for:");
				try {
					name = scanner.next();
				}catch (InputMismatchException e) {
					throw new BadInputException(); 
				}
				List<BookItem> foundBook = ((Liberian) user).searchBookByAuhtorName(listBooks, name);
				System.out.println(" Below is the result of the search:");
				displayElement(foundBook);
				break;	
			case 10:
				((Liberian) user).logout();
				System.out.println("you log out successfully");
			default:
				System.out.println("you entered a wrong choice. the program will stop.Thank you");
				System.exit(0);
				break;
			}
			
		}
		else if(user instanceof Member) {
			
		}
	}
	/*Generic method which helps to displays either list of books or list of member accounts
	 * found via the searchName method*/
	private static <T> void displayElement(List<T> t) {
		int count = 1;
		if(t.size() >=1) {
			for (T t1 : t) {
				System.out.println(count++ +" : "+t1);
			}
		}
		else {
			System.out.print("Nothing found");
			System.exit(0);
		}
	}
	
	private static void listExistingAccount(List<Account> accounts) {
		for (Account account : accounts) {
			System.out.println("Account type: "+account.getTypeAccount()+" id: "+account.getId()+" username: "+account.getUsername()+" status: "+ account.getStatus());
		}
		
	}
	
	

}
