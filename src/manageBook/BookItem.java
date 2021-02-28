package manageBook;

import java.time.LocalDateTime;

import manageBook.BookItem.BookStatus;

// represent a book which is borrowed

public class BookItem extends Book {
    private String bookId;
	private LocalDateTime dateOfPurchase;
	private int rackNumber;
	private String rackLocation;
	private String status;
	public enum BookStatus {
	    available,
		borrowed
	};

	

	/**
	 * 
	 */
	public BookItem() {
		super();
		this.setStatus(BookStatus.available.toString());
	}

	/**
	 * @param iSBN
	 * @param title
	 * @param author
	 * @param subject
	 * @param publisher
	 * @param language
	 * @param format
	 * @param price
	 * @param publicationDate
	 */
	public BookItem(long iSBN, String title, String author, String subject, String publisher, String language,int totalPages,
			String format, String price, LocalDateTime publicationDate,String bookId, LocalDateTime dateOfPurchase, 
			int rackNumber, String rackLocation) {
		super(iSBN, title, author, subject, publisher, language,totalPages,format, price, publicationDate);
		this.setBookId(bookId);
		this.setDateOfPurchase(dateOfPurchase);
		this.setRackNumber(rackNumber);
		this.setRackLocation(rackLocation);
		this.setStatus(BookStatus.available.toString());
		
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public LocalDateTime getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public int getRackNumber() {
		return rackNumber;
	}

	public void setRackNumber(int rackNumber) {
		this.rackNumber = rackNumber;
	}

	public String getRackLocation() {
		return rackLocation;
	}

	public void setRackLocation(String rackLocation) {
		this.rackLocation = rackLocation;
	}
	
	/* this method is used to get the status of a book
	 * so it can be called by multiple threads which run concurrently to read
	 * the value of local variable status. We synchronize the method to make the threads
	 * running concurrently safe. so each thread can read the right value of status
	 * */
	
	 public synchronized String getStatus() {
		return status;
	}

	/* this method is used to set the status of a book
	 * so it can be called by multiple threads which run concurrently to read
	 * the value of local variable status. We synchronize the method to make the threads
	 * running concurrently safe. so each thread can set the right value of status
	 * */
	
	 public synchronized void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "The bookid: "+this.getBookId()+" ISBN: "+this.getISBN()+" title: "+this.getTitle()+" author: "+this.getAuthor()
		+" subject: "+this.getSubject()+" publisher: "+this.getPublisher()+" language: "+this.getLanguage()+" price: "+this.getPrice()
		+" pages: "+this.getPages()+" rack number: "+this.getRackNumber()+" publication date: "+this.getPublicationDate()+" purchase date: "
		+this.getDateOfPurchase()+" format: "+this.getFormat()+" book location: "+this.getRackLocation()+" status: "+this.getStatus() ;
	}
	
}
