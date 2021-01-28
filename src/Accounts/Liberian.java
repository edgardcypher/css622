package Accounts;

import java.util.List;

import Constants.AccountStatus;
import manageBook.Book;

public class Liberian extends Account {
	
	public Liberian(Person person, String id, String password, AccountStatus status, String accountType) {
		super(person, id, password, status, accountType);
	}

	public  boolean createAnAccount() {
		System.out.println("membership account  or liberian account create successfully");
		return true;
	}
	public void addBook(List<Book> listBooks ,Book bookToAdd ) {
		listBooks.add(bookToAdd);
		System.out.println("Book was sucessfully added");
	}
	public void updateBook() {
		System.out.println("book has been updated");
	}
	public void deleteBook() {
		System.out.println("book has been delete");
	}
}
