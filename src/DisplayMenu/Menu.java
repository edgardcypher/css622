package DisplayMenu;

import java.util.LinkedList;
import java.util.Scanner;

import Accounts.Credential;

/*Represent the parent class of both classes
 * DisplayLiberianMainMenu and DisplayMemberMainMenu
 * this class has an abstract method displayMenu()
 * which will be implemented by both child classes
 * */
public abstract class Menu {
	private String memberId;
	private String password;
	private String user;
	
	public void displayLogInMenu(Scanner scan, String user) {
		this.setUser(user);
		System.out.println(" \nLog In as "+ this.getUser());
		System.out.print("Id:");
		String memberId = scan.next();
		this.setMemberId(memberId);
		System.out.print("Password:");
		String pwd = scan.next();
		this.setPassword(pwd);
		
	}
	public boolean checkCredential(LinkedList<Credential> credentials,String id, String password) {
		for (Credential credential : credentials) {
			if(credential.getMemberId().equals(id) && credential.getPwd().equals(password)) {
				return true;
			}
		}
		return false;
	}
	public abstract void displayMenu();// abstract method will be implemented by child classes
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
