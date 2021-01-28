package manageBook;

import java.time.LocalDateTime;
// represent a book
public class Book {
	
	private String bookId;
	private String ISBN;
	private String title;
	private String Author;
	private String subject;
	private String publisher;
	private String language;
	private String format;
	private String price;
	private String status;
	private int numberOfCopy;
	private LocalDateTime dateOfPurchase; 
	private LocalDateTime publicationDate;
	private int rackNumber;
	private String rackLocation;
	public String getBookId() {
		return bookId;
	}
	
	public Book() {}
	
	
	
	/**
	 * @param bookId
	 * @param iSBN
	 * @param title
	 * @param subject
	 * @param publisher
	 * @param language
	 * @param format
	 * @param price
	 * @param status
	 * @param numberOfCopy
	 * @param dateOfPurchase
	 * @param publicationDate
	 * @param rackNumber
	 * @param rackLocation
	 */
	public Book(String bookId, String iSBN, String title, String subject, String publisher, String language,
			String format, String price, String status, int numberOfCopy, LocalDateTime dateOfPurchase,
			LocalDateTime publicationDate, int rackNumber, String rackLocation) {
		super();
		this.bookId = bookId;
		ISBN = iSBN;
		this.title = title;
		this.subject = subject;
		this.publisher = publisher;
		this.language = language;
		this.format = format;
		this.price = price;
		this.status = status;
		this.numberOfCopy = numberOfCopy;
		this.dateOfPurchase = dateOfPurchase;
		this.publicationDate = publicationDate;
		this.rackNumber = rackNumber;
		this.rackLocation = rackLocation;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getNumberOfCopy() {
		return numberOfCopy;
	}
	public void setNumberOfCopy(int numberOfCopy) {
		this.numberOfCopy = numberOfCopy;
	}
	public LocalDateTime getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public LocalDateTime getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(LocalDateTime publicationDate) {
		this.publicationDate = publicationDate;
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

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}
	

	
}
