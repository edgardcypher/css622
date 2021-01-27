package DisplayMenu;

import java.util.Scanner;

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
	public boolean checkCredential(String id, String password) {
		if(this.getMemberId().equals(id) && this.getPassword().equals(password)) {
			return true;
		}
		return false;
		
	}
	public abstract void displayMenu();
	
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
