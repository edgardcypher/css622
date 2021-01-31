package Driver_test;

import java.io.File;
import java.io.FileNotFoundException;
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
		List<Book> allBooks = new ArrayList<>();
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
		
		
		Account liberianAccount = new Liberian(liberianPerson,"lib0231","alpha01","Jmagaret",AccountStatus.Active,"Liberian");
		Account member_1Account = new Member(memberPerson_1,"memb123","margared21","Jora",AccountStatus.Active,dateOfBecomingMember1,1,"Member");
		Account member_2Account = new Member(memberPerson_2,"memb124","nathan21","t_nathan",AccountStatus.Active,dateOfBecomingMember2,1,"Member");
		int choice = theLogger(scanner);
		readAccountCredential(credentialList,scanner);
		if(choice == 1) {
			Menu liberianMenu = new DisplayLiberianMainMenu();
			liberianMenu.displayLogInMenu(scanner,"Liberian");
			if(liberianMenu.checkCredential(credentialList,liberianMenu.getMemberId(), liberianMenu.getPassword())) {
				System.out.println("you are sucessful logged in as a liberian");
				liberianMenu.displayMenu(); // polymorphism
				Liberian liberian = (Liberian)liberianAccount; // downcasting
//				liberian.addBook(allBooks,new BookItem());
//				liberian.updateBook(allBooks,new BookItem());
//				liberian.closeAccount();
				System.exit(0);
			} else {
				System.out.println("sorry wrong credential");
				System.exit(0);
			}
		}else if (choice == 1)  {
			Menu memberMenu = new DisplayMemberMainMenu();
			memberMenu.displayLogInMenu(scanner,"Member");
			if(memberMenu.checkCredential(credentialList,memberMenu.getMemberId(), memberMenu.getPassword())) {
				System.out.println("you are sucessful logged in as a liberian");
				memberMenu.displayMenu(); // polymorphism
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
	
	private static int theLogger(Scanner scanner) {
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
			System.out.println("the program will stop here because you entered a non numeric character");
			System.exit(0);
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
		
		try {
			File file = new File("account_credentials_db.txt");// create a file object with the pathfilename 
			scanner = new Scanner(file);// read the file
			
		} catch (FileNotFoundException e) {// return a catch in case the file is not found
			System.out.println("file not found");
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

}
