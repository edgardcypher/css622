package Driver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ExistingAnnotationsType;

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
		String myDateOfBecomingMember = myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		
		Address addressMember_2 = new Address("Bradford", "Coal St", "16701", "USA","PA", 3119);
		Person memberPerson_2 = new Person("Nathan T. Gardner", addressMember_2, "NathanTGardner@jourrapide.com", "814-363-1687");
		LocalDateTime myDateObj = LocalDateTime.of(2020, 11, 4, 11, 10);
		String dateOfBecomingMember = myDateObj.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		
		
		Account liberian = new Liberian(liberianPerson,"lib0231","alpha01",AccountStatus.Active,"Liberian");
		Account member_1 = new Member(memberPerson_1,"memb123","margared21",AccountStatus.Active,myDateOfBecomingMember,1,"Member");
		Account member_2 = new Member(memberPerson_2,"memb124","nathan21",AccountStatus.Active,dateOfBecomingMember,1,"Member");
		
		Menu liberianMenu = new DisplayLiberianMainMenu();
		liberianMenu.displayLogInMenu(scanner,"Liberian");
		
		if(liberianMenu.checkCredential(liberian.getId(), liberian.getPassword())) {
			System.out.println("you are sucessful logged in");
			liberianMenu.displayMenu();
			System.exit(0);
		} else {
			System.out.println("sorry wrong credential");
			System.exit(0);
		}
		Menu memberMenu = new DisplayMemberMainMenu();
		
		
		memberMenu.displayLogInMenu(scanner,"Member");
		
		
		memberMenu.displayMenu();
		scanner.close();

	}

}
