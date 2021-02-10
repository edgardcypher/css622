package JunitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Accounts.Account;
import Accounts.Address;
import Accounts.Credential;
import Accounts.Liberian;
import Accounts.Member;
import Accounts.Person;
import Constants.AccountStatus;
import DisplayMenu.DisplayLiberianMainMenu;
import DisplayMenu.Menu;
import manageBook.BookItem;

public class AccountTest {
	
	@Test
	void testCredential() {
		
		Account liberian = new Liberian(new Person("Victor Zoe", new Address(), "victor@hotmail.com", "301-304-253"),"lib0231","alpha01","testUsername",AccountStatus.Active,"Liberian");
		Menu liberianMenuLogin = new DisplayLiberianMainMenu();
		liberianMenuLogin.setMemberId("1234");
		liberianMenuLogin.setPassword("abcd");
		
		LinkedList<Credential> credentialList = new LinkedList<>();//create an empty list of credential
		credentialList.add(new Credential("lib0231","alpha01","testUsername"));
		
		//Testing checkCredential method
		assertFalse(liberianMenuLogin.checkCredential(credentialList,liberianMenuLogin.getMemberId(), liberianMenuLogin.getPassword()));
		
		liberianMenuLogin.setMemberId("lib0231");
		liberianMenuLogin.setPassword("alpha01");
		assertTrue(liberianMenuLogin.checkCredential(credentialList,liberianMenuLogin.getMemberId(), liberianMenuLogin.getPassword()));
		
		Person person1 = new Person("Joe Doe", new Address(), "joe@hotmail.com", "201-304-203");
		LocalDateTime myDate = LocalDateTime.of(2019, 10, 5, 11, 10);
		String dateOfBecomingMember1 = myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		Account member1 = new Member(person1, "memb1", "beta1", "Jdoe", AccountStatus.Active, dateOfBecomingMember1,"Member");
		
		Person person2 = new Person("Joe Smith", new Address(), "joe@hotmail.com", "201-207-203");
		LocalDateTime date = LocalDateTime.of(2018, 11, 5, 11, 10);
		String dateOfBecomingMember2 = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		Account member2 = new Member(person2, "memb2", "beta2", "Jsmith", AccountStatus.Active, dateOfBecomingMember2,"Member");
		
		List<Account> allAccounts = new ArrayList<>();
		allAccounts.add(liberian);
		allAccounts.add(member1);
		allAccounts.add(member2);
		
		//Testing isAccountExist method
		assertFalse(((Liberian)liberian).isAccountExist(allAccounts,"memb3"));
		assertTrue(((Liberian)liberian).isAccountExist(allAccounts,"memb1"));
		assertTrue(((Liberian)liberian).isAccountExist(allAccounts,"memb2"));
		assertTrue(((Liberian)liberian).isAccountExist(allAccounts,"lib0231"));
		
		
		//Testing searchMemberByName method
		assertEquals(((Liberian)liberian).searchMemberByName(allAccounts, "doe").size(), 1);
		assertEquals(((Liberian)liberian).searchMemberByName(allAccounts, "smith").size(), 1);
		
		//Testing deleteAnAccount method
		assertFalse(((Liberian)liberian).deleteAnAccount(allAccounts,"memb3"));
		assertEquals(allAccounts.size(), 3);
		
		assertTrue(((Liberian)liberian).deleteAnAccount(allAccounts,"memb1"));
		assertTrue(((Liberian)liberian).deleteAnAccount(allAccounts,"memb2"));
		assertEquals(allAccounts.size(), 1);
		
		LocalDateTime publicationDate = LocalDateTime.of(2019, 10, 5, 11, 10);
		LocalDateTime publicationDate2 = LocalDateTime.of(2018, 10, 5, 11, 10);
		LocalDateTime dateOfPurchase = LocalDateTime.of(2020, 10, 5, 11, 10);
		LocalDateTime dateOfPurchase2 = LocalDateTime.of(2020, 11, 1, 11, 10);
		
		BookItem book1 = new BookItem(9781593275846L,"Eloquent JavaScript Second Edition","Martin Howard","programming","No Starch Press","English",120, "Hardcover","$200",publicationDate,"book1",dateOfPurchase,5,"5C4L2");
		BookItem book2 = new BookItem(9781493275846L,"Learning Pytho","Herve Kona","programming","Versaile printing","English",100, "Hardcover","$200",publicationDate2,"book2",dateOfPurchase2,2,"2C5L2");
		
		List<BookItem> bookItems = new ArrayList<BookItem>();
		bookItems.add(book1);
		bookItems.add(book2);
		
		//Testing searchBookByAuhtorName method
		assertEquals(((Liberian)liberian).searchBookByAuhtorName(bookItems, "Martin").size(), 1);
		assertEquals(((Liberian)liberian).searchBookByAuhtorName(bookItems, "Herve").size(), 1);
		assertEquals(((Liberian)liberian).searchBookByAuhtorName(bookItems, "Edward").size(), 0);
		
		
		
 	}
	
}
