package dbOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import Accounts.Account;
import manageBook.BookItem;

public class CRUDBooks {
	
	private Connection connection = null;
	
	public CRUDBooks (Connection con) {
		this.connection = con;
	}
	
	/*Create Book table with different columns*/
	public void createBookTable () {
		try(Statement statement = this.connection.createStatement()){
			String sqlCreateTableBook = "CREATE TABLE IF NOT EXISTS Book ("
					+ "book_id TEXT PRIMARY KEY, "
					+ "ISBN TEXT NOT NULL,"
					+ "title TEXT NOT NULL,"
					+ "Author TEXT NOT NULL,"
					+ "subject TEXT NOT NULL,"
					+ "publisher TEXT NOT NULL,"
					+ "language TEXT NOT NULL,"
					+ "publication_date TEXT NOT NULL,"
					+ "price TEXT NOT NULL,"
					+ "date_of_purchase TEXT NOT NULL,"
					+ "rack_number INT NOT NULL,"
					+ "rack_location TEXT NOT NULL,"
					+ "book_status TEXT NOT NULL,"
					+ "pages INT NOT NULL,"
					+ "format TEXT NOT NULL)";
			statement.executeUpdate(sqlCreateTableBook);
			System.out.println("Table book created successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*Function which use a sql query to insert a new row data into Book table */
	public void insertBookDataIntoTable (BookItem book) {
		//Precondition: a BookItem object
		
		String sqlBookDataInsert =  "Insert Into Book(book_id,ISBN,title,Author,subject,publisher,language,publication_date,price,date_of_purchase,rack_number,rack_location,book_status,pages,format) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?)"; // insert into Book table query
		
		try(
				PreparedStatement pstmt = this.connection.prepareStatement(sqlBookDataInsert);
			) {
			
			//below we insert values into different Book table columns 
			pstmt.setString(1, book.getBookId());
			pstmt.setString(2, Long.toString(book.getISBN()));
			pstmt.setString(3, book.getTitle());
			pstmt.setString(4, book.getAuthor());
			pstmt.setString(5, book.getSubject());
			pstmt.setString(6, book.getPublisher());
			pstmt.setString(7, book.getLanguage());
			pstmt.setString(8, book.getPublicationDate().toString());
			pstmt.setString(9, book.getPrice());
			pstmt.setString(10, book.getDateOfPurchase().toString());
			pstmt.setInt(11, book.getRackNumber());
			pstmt.setString(12, book.getRackLocation());
			pstmt.setString(13, book.getStatus());
			pstmt.setInt(14, book.getPages());
			pstmt.setString(15, book.getFormat());
			
			int rowCount = pstmt.executeUpdate();
			
			if (rowCount == 0) {
	            throw new SQLException("Inserting Book data failed, no rows affected.");
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*Retrieve all the books information from book table */
	public void listBooks (List<BookItem> allBooks) {
		String sqlSelectAllBooks =  "SELECT * FROM Book;"; // Select all Books data from table query
		try(Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelectAllBooks)){
			while(rs.next()) {
				BookItem bookItem = new BookItem();
				bookItem.setBookId(rs.getString(1));
				bookItem.setISBN(Long.parseLong(rs.getString(2)));
				bookItem.setTitle(rs.getString(3));
				bookItem.setAuthor(rs.getString(4));
				bookItem.setSubject(rs.getString(5));
				bookItem.setPublisher(rs.getString(6));
				bookItem.setLanguage(rs.getString(7));
				bookItem.setPublicationDate(LocalDateTime.parse(rs.getString(8)));
				bookItem.setPrice(rs.getString(9));
				bookItem.setDateOfPurchase(LocalDateTime.parse(rs.getString(10)));
				bookItem.setRackNumber(rs.getInt(11));
				bookItem.setRackLocation(rs.getString(12));
				bookItem.setStatus(rs.getString(13));
				bookItem.setPages(rs.getInt(14));
				bookItem.setFormat(rs.getString(15));
				allBooks.add(bookItem);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*Create IssuedBook table with different columns. This table will save the id of the book which is borrowed and also
	 * the member id of the member who borrowed the book*/
	public void createTrackIssuedBookTable () {
		try(Statement statement = this.connection.createStatement()){
			String sqlCreateTableBook = "CREATE TABLE IF NOT EXISTS IssuedBook ("
					+ "id INTEGER PRIMARY KEY, "
					+ "borrowed_date TEXT ,"
					+ "return_book_date TEXT,"
					+ "account_id TEXT NOT NULL,"
					+ "book_id TEXT NOT NULL,"
					+ "FOREIGN KEY (book_id) REFERENCES Book(book_id) ON DELETE CASCADE,"
					+ "FOREIGN KEY (account_id) REFERENCES Account(account_id) ON DELETE CASCADE)";
					
			statement.executeUpdate(sqlCreateTableBook);
			System.out.println("Table IssuedBook created successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*Function which use a sql query to insert a new row data into IssuedBook table */
	public void insertIssuedBookDataIntoTable (BookItem book, Account account, LocalDateTime issuedDate,LocalDateTime returnDate) {
		//Precondition: a BookItem object
		
		String sqlBookDataInsert =  "Insert Into IssuedBook(borrowed_date,return_book_date,account_id,book_id) VALUES (?, ?, ?, ?)"; // insert into IssuedBook table query
		
		try(
				PreparedStatement pstmt = this.connection.prepareStatement(sqlBookDataInsert);
			) {
			pstmt.setString(1, issuedDate.toString());
			pstmt.setString(2, returnDate.toString());
			pstmt.setString(4, account.getId());
			pstmt.setString(5, book.getBookId());
			pstmt.executeUpdate();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/*Find and build a string representing a member who borrowed a book*/
	public void findInformationMemberWhoBorrowed(String memberid) {
		
		//Postcondition: a string representing the book borrowed and the member who borrowed the book
		String msg = "";
		try {
			Statement stmt = this.connection.createStatement();
			
			String sql = "SELECT acc.username,bk.title,IssuedBook.borrowed_date,IssuedBook.return_book_date "+
				    " FROM IssuedBook " +
					" JOIN Account acc ON acc.account_id = IssuedBook.account_id" + 
					" JOIN Book bk ON IssuedBook.book_id = bk.book_id" + 
					" WHERE IssuedBook.account_id = '"+memberid+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) {
				String usrname = rs.getString("username");
				String title = rs.getString("title");
				String issuedDate = rs.getString("borrowed_date");
				String returnBookDate = rs.getString("return_book_date");
				msg = "the member with username "+usrname+" has borrowed the book whose the title "+title+" on "+issuedDate+" and the book will be returned on "+returnBookDate;
			}
			 

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(msg);
	}
	
	/*Function which return the total number of books whose pages have greater or equal to 100*/
	public void nbreBookWithPagesGreaterOrEqualThan100() {
		int totalBooks = 0;
		String sql = "select COUNT(*) from Book where pages >= 100";
		try {
			Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			totalBooks = rs.getInt(1);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("There are "+totalBooks+" book with pages number equal or greater than 100");
	}

}
