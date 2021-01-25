package Accounts;

public abstract class Account {
  private Person person;
  private String id;
  private String password;
  private AccountStatus status;
  
	public Account(Person person, String id, String password, AccountStatus status) {
		super();
		this.person = person;
		this.id = id;
		this.password = password;
		this.status = status;
	}
	
  public boolean resetPassword() {
	  System.out.println("password reset successfully");
	  return true;
  }
  
  public boolean closeAccount(){
	  System.out.println("account close successfully");
	  return true;
  }
  
  abstract boolean createAnAccount();
  
  
}
