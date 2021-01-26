package Accounts;

import java.util.Date;

import Constants.AccountStatus;

public class Member extends Account {
	private String dateOfBecomingMember;
	private int totalBookBorrored;
	
	
	/**
	 * @param person
	 * @param id
	 * @param password
	 * @param status
	 * @param dateOfBecomingMember
	 * @param totalBookBorrored
	 */
	public Member(Person person, String id, String password, AccountStatus status, String dateOfBecomingMember,
			int totalBookBorrored,String typeAccount) {
		super(person, id, password, status, typeAccount);
		this.dateOfBecomingMember = dateOfBecomingMember;
		this.totalBookBorrored = totalBookBorrored;
	}


	public  boolean createAnAccount() {
		System.out.println("membership account create successfully");
		return true;
	}


	public String getDateOfBecomingMember() {
		return dateOfBecomingMember;
	}


	public void setDateOfBecomingMember(String dateOfBecomingMember) {
		this.dateOfBecomingMember = dateOfBecomingMember;
	}


	public int getTotalBookBorrored() {
		return totalBookBorrored;
	}


	public void setTotalBookBorrored(int totalBookBorrored) {
		this.totalBookBorrored = totalBookBorrored;
	}
	
	
}
