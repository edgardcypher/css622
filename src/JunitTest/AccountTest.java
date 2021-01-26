package JunitTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import Accounts.Account;
import Accounts.Liberian;
import Accounts.Person;
import Constants.AccountStatus;
import DisplayMenu.DisplayLiberianMainMenu;
import DisplayMenu.Menu;

public class AccountTest {
	
	@Test
	void testCredential() {
		Account liberian = new Liberian(new Person(),"lib0231","alpha01",AccountStatus.Active,"Liberian");
		Menu liberianMenuLogin = new DisplayLiberianMainMenu();
		liberianMenuLogin.setMemberId("1234");
		liberianMenuLogin.setPassword("abcd");
		
		//Testing checkCredential method
		assertFalse(liberianMenuLogin.checkCredential(liberian.getId(), liberian.getPassword()));
		liberianMenuLogin.setMemberId("lib0231");
		liberianMenuLogin.setPassword("alpha01");
		assertTrue(liberianMenuLogin.checkCredential(liberian.getId(), liberian.getPassword()));

	}
	
}
