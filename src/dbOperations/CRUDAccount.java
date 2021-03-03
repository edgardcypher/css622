package dbOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Accounts.Account;
import Accounts.Address;
import Accounts.Member;
import Accounts.Person;

public class CRUDAccount {
	private Connection connection = null;
	
	public CRUDAccount (Connection con) {
		this.connection = con;
	}
	
	/*Create Address table with all its columns*/
	public void createPersonAddressTable () {
		try(Statement statement = this.connection.createStatement()){
			String sqlCreateTableAddress = "CREATE TABLE IF NOT EXISTS Address ("
					+ "address_id INTEGER PRIMARY KEY, "
					+ "city TEXT NOT NULL,"
					+ "street_name TEXT NOT NULL,"
					+ "zip_code TEXT NOT NULL,"
					+ "country TEXT NOT NULL,"
					+ "street_number INT NOT NULL,"
					+ "state TEXT NOT NULL)";
			statement.executeUpdate(sqlCreateTableAddress);
			System.out.println("Table address created successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*Create Person table with all its columns*/
   public void createPersonTable () {
		try(Statement statement = this.connection.createStatement()){
			String sqlCreateTablePerson = "CREATE TABLE IF NOT EXISTS Person ("
					+ "person_id INTEGER PRIMARY KEY, "
					+ "name TEXT NOT NULL,"
					+ "email TEXT NOT NULL,"
					+ "phone_number TEXT NOT NULL,"
					+ "address_id INTEGER NOT NULL,"
					+ "FOREIGN KEY (address_id) REFERENCES Address(address_id) ON DELETE CASCADE)";// address_id is a foreign key on person table
			statement.executeUpdate(sqlCreateTablePerson);
			System.out.println("Table person created successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
   
 
   /*Create account table with all its columns*/
	public void createAccountTable() {
		try(Statement statement = this.connection.createStatement()){
			String sqlCreateTableAccount = "CREATE TABLE IF NOT EXISTS Account ("
					+ "account_id TEXT PRIMARY KEY, "
					+ "username TEXT NOT NULL,"
					+ "password TEXT NOT NULL,"
					+ "status TEXT NOT NULL,"
					+ "type_account TEXT NOT NULL,"
					+ "date_of_accountCreation TEXT NOT NULL,"
					+ "person_id INTEGER NOT NULL,"
					+ "FOREIGN KEY (person_id) REFERENCES Person(person_id) ON DELETE CASCADE"//person_id is a foreign key on person table
					+ ")";
			statement.executeUpdate(sqlCreateTableAccount);
			System.out.println("Table account created successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*Insert rows values into tables Account,Person,Address table with all its columns*/
	public void insertAddressPersonAccountRow(Account account) {
		String sqlAddressInsert =  "Insert Into Address(city,street_name,street_number,zip_code,country,state) VALUES (?, ?, ?, ?, ?, ?)"; // insert into Address table query
		String sqlPersonInsert =  "Insert Into Person(name,email,phone_number,address_id) VALUES (?, ?, ?, ?)"; // insert into Person table query
		String sqlAccountInsert =  "Insert Into Account(account_id,username,password,status,type_account,date_of_accountCreation,person_id) VALUES (?, ?, ?, ?, ?, ?, ?)"; // insert into Account table query
		
		try(
			PreparedStatement pstmt = this.connection.prepareStatement(sqlAddressInsert, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement pstmt2 = this.connection.prepareStatement(sqlPersonInsert, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement pstmt3 = this.connection.prepareStatement(sqlAccountInsert, Statement.RETURN_GENERATED_KEYS);
		) {
			
			Address address = account.getPerson().getAddress();
			
			//below we insert values into different Address table columns 
			pstmt.setString(1, address.getCity());
			pstmt.setString(2, address.getStreet());
			pstmt.setString(3, address.getZipCode());
			pstmt.setString(4, address.getCountry());
			pstmt.setInt(5, address.getStreetNumber());
			pstmt.setString(6, address.getState());
			
			int rowCount = pstmt.executeUpdate();
			
			int generatedKey = 0;
			if (rowCount == 0) {
	            throw new SQLException("Creating address failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	generatedKey = generatedKeys.getInt(1);// grap the rowid of the newly inserted row.It will be used as foreign key in Person table
	            }
	            else {
	                throw new SQLException("Creating address failed, no ID obtained.");
	            }
	        }
			
			Person person = account.getPerson();
			
			//below we insert values into different Person table columns
			pstmt2.setString(1, person.getName());
			pstmt2.setString(2, person.getEmail());
			pstmt2.setString(3, person.getPhoneNumber());
			pstmt2.setInt(4, generatedKey);
			int rowCount2 = pstmt2.executeUpdate();
			
			if (rowCount2 == 0) {
	            throw new SQLException("Creating person failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = pstmt2.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	generatedKey = generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("Creating person failed, no ID obtained.");
	            }
	        }
			
	        //below we insert values into different Account table columns
			pstmt3.setString(1, "member"+generatedKey );
			pstmt3.setString(2, account.getUsername());
			pstmt3.setString(3, account.getPassword());
			pstmt3.setString(4, account.getStatus());
			pstmt3.setString(5, account.getTypeAccount());
			pstmt3.setString(6, account.getDateOfAccountCreation());
			pstmt3.setInt(7, generatedKey);
			pstmt3.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*Find an all the accounts from account table and add them to the list*/
	public void listAllAccount (List<Account> accounts) {
		String sqlSelectAllAcccounts =  "SELECT * FROM Account ORDER BY account_id asc;"; // Select all accounts data from table query
		
		try(Statement stmt = this.connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlSelectAllAcccounts)){
			while(rs.next()) {
				Account member = new Member();
				member.setId(rs.getString(1));
				member.setUsername(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setStatus(rs.getString(4));
				member.setTypeAccount(rs.getString(5));
				member.setDateOfAccountCreation(rs.getString(6));
				member.setPerson(findPersonById(rs.getInt(7)));
				accounts.add(member);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*Find an person saved person address table */
	private Person findPersonById(int id ) {
		//Postcondition:Return a person object
		String sqlSelectPersonById =  "SELECT * FROM Person where person_id ="+id+";"; // Select a person from Person query
		Person person = new Person();
		try(Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelectPersonById)){
			while(rs.next()) {
				
				person.setName(rs.getString(2));
				person.setEmail(rs.getString(3));
				person.setPhoneNumber(rs.getString(4));
				person.setAddress(findAddressById(rs.getInt(5)));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;
	}
	/*Find an address saved into address table */
	private Address findAddressById(int id ) {
		//Postconditon: will return an address object
		String sqlSelectPersonById =  "SELECT * FROM Address where address_id ="+id+";"; // Select a person from Person query
		Address address = new Address();
		try(Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelectPersonById)){
			while(rs.next()) {
				address.setCity(rs.getString(2));
				address.setStreet(rs.getString(3));
				address.setZipCode(rs.getString(4));
				address.setCountry(rs.getString(5));
				address.setStreetNumber(rs.getInt(6));
				address.setState(rs.getString(7));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return address;
	}
}
