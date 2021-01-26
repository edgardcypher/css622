package Accounts;

import Constants.AccountStatus;

public abstract class Account {
  private Person person;
  private String id;
  private String password;
  private AccountStatus status;
  private String typeAccount;
  
  
	public Account(Person person, String id, String password, AccountStatus status, String typeAcc) {
		super();
		this.person = person;
		this.id = id;
		this.password = password;
		this.status = status;
		this.typeAccount = typeAcc;
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
	
	  public Person getPerson() {
		return person;
	  }
	
	  public void setPerson(Person person) {
		this.person = person;
	  }
		
	  public String getId() {
		return id;
	  }
		
	  public void setId(String id) {
		this.id = id;
	  }
		
	  public String getPassword() {
		return password;
	  }
		
	  public void setPassword(String password) {
		this.password = password;
	  }
		
	  public AccountStatus getStatus() {
		return status;
	  }
		
	  public void setStatus(AccountStatus status) {
		this.status = status;
	  }

	public String getTypeAccount() {
		return typeAccount;
	}

	public void setTypeAccount(String typeAccount) {
		this.typeAccount = typeAccount;
	}
	  
	  

  
}
