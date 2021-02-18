package Accounts;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import Constants.AccountStatus;

public class Member extends Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalBookBorrored;
	
	
	/**
	 * @param person
	 * @param id
	 * @param password
	 * @param status
	 * @param dateOfBecomingMember
	 * @param totalBookBorrored
	 */
	public Member(Person person, String id, String password, String username, AccountStatus status, String typeAccount) {
		super(person, id, password,username, status, typeAccount);
		this.setTotalBookBorrored(0);
	}


	public Member() {
		super();
		this.setTotalBookBorrored(0);
	}


	public  boolean createAnAccount() {
		System.out.println("membership account create successfully");
		return true;
	}

	public int getTotalBookBorrored() {
		return totalBookBorrored;
	}


	public void setTotalBookBorrored(int totalBookBorrored) {
		this.totalBookBorrored = totalBookBorrored;
	}
	
	public void borrowBook() {
		System.out.println("I am borrowing a book from Library");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Account type: "+this.getTypeAccount()+" id: "+this.getId()+" Username: "+this.getUsername()+" status: "+ this.getStatus()
		+" "+this.getPerson()+" total borrowed books: "+this.getTotalBookBorrored() ;
	}
	

}
