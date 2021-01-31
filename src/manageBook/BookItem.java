package manageBook;

import java.time.LocalDateTime;

// represent a book which is borrowed

public class BookItem extends Book {
  private String bookId;
	private LocalDateTime dateOfPurchase;
	private int rackNumber;
	private String rackLocation;
	private String status;
  
	public BookItem() {}
	


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
	public BookItem(String iSBN, String title, String author, String subject, String publisher, String language,
			String format, String price, LocalDateTime publicationDate) {
		super(iSBN, title, author, subject, publisher, language, format, price, publicationDate);
		// TODO Auto-generated constructor stub
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
