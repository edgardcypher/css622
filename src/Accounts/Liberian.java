package Accounts;

import java.util.List;

import Constants.AccountStatus;
import manageBook.Book;
import manageBook.BookItem;

public class Liberian extends Account {
	
	
	public Liberian(Person person, String id, String password,String username, AccountStatus status, String accountType) {
		super(person, id, password,username, status, accountType);
	}

	public boolean addBook(List<Book> listBooks, BookItem newBookItem ) {
		// precondition the book which has to be added should not exist in the list 
		// Postcondition: an updated list of books listBooks which contains the new newBookItem
		int count = 0;
		for (Book book : listBooks) {
			count++;
			if(book instanceof BookItem) {
				if(((BookItem)book).getBookId().equals(newBookItem.getBookId())) {// downcasting
					System.out.println("that book exist already it cannot be added");
					return false;
				} else if(listBooks.size() == count) {
					break;
				}
			}
		}
		listBooks.add(newBookItem);
		System.out.println("Book was sucessfully added");
		return true;
	}
	public boolean updateBook(List<Book> listBooks, BookItem bookToUpdate) {
		// precondition the book which has to be updated should exist in the list
		// Postcondition: an list of books listBooks which contains an updated BookItem
		int count = 0;
		for (Book book : listBooks) {
			count++;
			if(book instanceof BookItem) {
				if(((BookItem)book).getBookId().equals(bookToUpdate.getBookId())) {// downcasting
					listBooks.remove(book);
					break;
				}else if (listBooks.size() == count) {
					System.out.println("that book does not exist");
					return false;
				}
			}
		}
		listBooks.add(bookToUpdate);
		System.out.println("book has been updated");
		return true;
	}
	public boolean deleteBook(List<Book> listBooks, String bookId) {
		// precondition the book which has to be deleted should exist in the list
		// Postcondition: an updated list of books listBooks which doesnt contains the deleted BookItem
		int count = 0;
		for (Book book : listBooks) {
			count++;
			if(book instanceof BookItem) {
				if(((BookItem)book).getBookId().equals(bookId)) {
					listBooks.remove(book);
					break;
				}else if (listBooks.size() == count) {
					System.out.println("that book does not exist");
					return false;
				}
			}
			
		}
		return true;
	}

	public boolean createAnAccount(List<Account> listOfAccounts, Account accountToCreate) {
		//Precondition: the new account should not exist
		// Postcondition: add a new account to a list of accounts
			if(accountToCreate instanceof Member) {
				if(!isAccountExist(listOfAccounts,accountToCreate.getId()) 
						&& isUsernameExist(listOfAccounts, accountToCreate.getUsername())) {// check if account already exist
					listOfAccounts.add(accountToCreate);
					System.out.println("membership account has been created successfully");
					return true;
				}
			} else if (accountToCreate instanceof Liberian) {
				if(!isAccountExist(listOfAccounts,accountToCreate.getId())) {//check if account already exist
					listOfAccounts.add(accountToCreate);
					System.out.println("liberian account has been created successfully");
					return true;
				}
		}
		return false;
	}
	
	
	public boolean deleteAnAccount(List<Account> listOfAccounts, String accountToDeleteId) {
		//Precondition: the account should exist
		// Postcondition: a new updated list of accounts without the deleted one
		for (Account thisMember : listOfAccounts) {
			if(thisMember instanceof Member) {
				if(thisMember.getId().equals(accountToDeleteId)) {
					listOfAccounts.remove(thisMember);
					System.out.println("membership account has been deleted successfully");
					return true;
				}
			}else if (thisMember instanceof Liberian) {
				if(thisMember.getId().equals(accountToDeleteId)) {
					listOfAccounts.remove(thisMember);
					System.out.println("Liberian account has been deleted successfully");
					return true;
				}
			}
		}
		return false;
	}
	private boolean isAccountExist(List<Account> listAccounts, String  accountId) {
	//Postcondition: return true if the account already exists in the list unless it returns false
	boolean foundAnAccount = false;
		for (Account account : listAccounts) {
			if(account.getId().equals(accountId)) {
				foundAnAccount = true;
				return foundAnAccount;
			}else {
				continue;
			}
		}
		return foundAnAccount;
	}
	
	
}
