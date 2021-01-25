package Accounts;

public class Liberian extends Account {
	
	public Liberian(Person person, String id, String password, AccountStatus status) {
		super(person, id, password, status);
	}

	public  boolean createAnAccount() {
		System.out.println("membership account  or liberian account create successfully");
		return true;
	}
	public void addBook() {}
	public void updateBook() {}
	public void deleteBook() {}
}
