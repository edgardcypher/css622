package Accounts;

import Constants.AccountStatus;

public class Liberian extends Account {
	
	public Liberian(Person person, String id, String password, AccountStatus status, String accountType) {
		super(person, id, password, status, accountType);
	}

	public  boolean createAnAccount() {
		System.out.println("membership account  or liberian account create successfully");
		return true;
	}
	public void addBook() {
		System.out.println("book has been added");
	}
	public void updateBook() {
		System.out.println("book has been updated");
	}
	public void deleteBook() {
		System.out.println("book has been delete");
	}
}
