package JunitTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import Accounts.Account;
import Accounts.Credential;
import Accounts.Liberian;
import Accounts.Person;
import Constants.AccountStatus;
import DisplayMenu.DisplayLiberianMainMenu;
import DisplayMenu.Menu;

public class AccountTest {
	
	@Test
	void testCredential() {
		Account liberian = new Liberian(new Person(),"lib0231","alpha01","testUsername",AccountStatus.Active,"Liberian");
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

	}
	
}
