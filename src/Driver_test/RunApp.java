package Driver_test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


import Accounts.Account;
import Accounts.Address;
import Accounts.Liberian;
import Accounts.Member;
import Accounts.Person;
import Constants.AccountStatus;
import DisplayMenu.DisplayLiberianMainMenu;
import DisplayMenu.DisplayMemberMainMenu;
import DisplayMenu.Menu;

public class RunApp {

	public static void main(String[] args) {
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
		
		
		Account liberianAccount = new Liberian(liberianPerson,"lib0231","alpha01",AccountStatus.Active,"Liberian");
		Account member_1Account = new Member(memberPerson_1,"memb123","margared21",AccountStatus.Active,dateOfBecomingMember1,1,"Member");
		Account member_2Account = new Member(memberPerson_2,"memb124","nathan21",AccountStatus.Active,dateOfBecomingMember2,1,"Member");
		int choice = theLogger(scanner);
		
		if(choice == 1) {
			Menu liberianMenu = new DisplayLiberianMainMenu();
			liberianMenu.displayLogInMenu(scanner,"Liberian");
			if(liberianMenu.checkCredential(liberianAccount.getId(), liberianAccount.getPassword())) {
				System.out.println("you are sucessful logged in as a liberian");
				liberianMenu.displayMenu();
				Liberian liberian = (Liberian)liberianAccount; // downcasting
				liberian.addBook();
				liberian.updateBook();
				liberian.closeAccount();
				System.exit(0);
			} else {
				System.out.println("sorry wrong credential");
				System.exit(0);
			}
		}else {
			Menu memberMenu = new DisplayMemberMainMenu();
			memberMenu.displayLogInMenu(scanner,"Member");
			if(memberMenu.checkCredential(member_1Account.getId(), member_1Account.getPassword())) {
				System.out.println("you are sucessful logged in as a liberian");
				memberMenu.displayMenu();
				member_1Account.closeAccount();
				System.exit(0);
			} else {
				System.out.println("sorry wrong credential");
				System.exit(0);
			}
		}
		
		scanner.close();

	}
	
	private static int theLogger(Scanner scanner) {
		System.out.println(" Log as:\n");
		System.out.println(" 1- Liberian:\n");
		System.out.println(" 2- Member:\n");
		System.out.print(" Enter your choice 1 or 2:");
		int choice = scanner.nextInt();
		return choice;
	}

}
