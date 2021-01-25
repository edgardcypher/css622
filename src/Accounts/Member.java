package Accounts;

import java.util.Date;

public class Member extends Account {
	private Date dateOfBecomingMember;
	private int totalBookBorrored;
	
	
	/**
	 * @param person
	 * @param id
	 * @param password
	 * @param status
	 * @param dateOfBecomingMember
	 * @param totalBookBorrored
	 */
	public Member(Person person, String id, String password, AccountStatus status, Date dateOfBecomingMember,
			int totalBookBorrored) {
		super(person, id, password, status);
		this.dateOfBecomingMember = dateOfBecomingMember;
		this.totalBookBorrored = totalBookBorrored;
	}


	public  boolean createAnAccount() {
		System.out.println("membership account create successfully");
		return true;
	}


	public Date getDateOfBecomingMember() {
		return dateOfBecomingMember;
	}


	public void setDateOfBecomingMember(Date dateOfBecomingMember) {
		this.dateOfBecomingMember = dateOfBecomingMember;
	}


	public int getTotalBookBorrored() {
		return totalBookBorrored;
	}


	public void setTotalBookBorrored(int totalBookBorrored) {
		this.totalBookBorrored = totalBookBorrored;
	}
	
	
}
