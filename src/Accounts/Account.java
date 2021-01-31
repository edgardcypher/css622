package Accounts;

import java.util.List;

import Constants.AccountStatus;

/*This class represent a parent abstract class and it has 
 * two children class Member and Liberian.This class has an
 * abstract method which will be implemented by both class
 * Member and Liberian
 * */

public abstract class Account {
  private Person person;
  private String id;
  private String username;
  private String password;
  private AccountStatus status;
  private String typeAccount;
  
  
	public Account(Person person, String id, String password, String username, AccountStatus status, String typeAcc) {
		/*this constructor will help to instantiate child classes*/
		super();
		this.person = person;
		this.id = id;
		this.password = password;
		this.status = status;
		this.typeAccount = typeAcc;
		this.username = username;
	}
		
	  public boolean resetPassword() {
		  System.out.println("password reset successfully");
		  return true;
	   }
	  
	  public boolean closeAccount(){
		  System.out.println("account close successfully");
		  return true;
	  }
	  	
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	protected boolean isUsernameExist(List<Account> listAccounts, String  username) {
		//Postcondition: return true if the account username already exists in the list unless it returns false
		boolean foundAnAccount = false;
			for (Account account : listAccounts) {
				if(account.getUsername().equals(username)) {
					foundAnAccount = true;
					return foundAnAccount;
				}else {
					continue;
				}
			}
			return foundAnAccount;
	}
	
}
