package manageBook;

import java.time.LocalDateTime;
// represent a book
public class Book {
	private String ISBN;
	private String title;
	private String Author;
	private String subject;
	private String publisher;
	private String language;
	private String format;
	private LocalDateTime publicationDate;
	private String price;
	
	
	public Book() {}
	
	/**
	 * @param iSBN
	 * @param title
	 * @param author
	 * @param subject
	 * @param publisher
	 * @param language
	 * @param format
	 * @param price
	 */
	public Book(String iSBN, String title, String author, String subject, String publisher, String language,
			String format, String price, LocalDateTime publicationDate) {
		super();
		ISBN = iSBN;
		this.title = title;
		Author = author;
		this.subject = subject;
		this.publisher = publisher;
		this.language = language;
		this.format = format;
		this.price = price;
		this.publicationDate = publicationDate;
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


	public String getAuthor() {
		return Author;
	}


	public void setAuthor(String author) {
		Author = author;
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




	public LocalDateTime getPublicationDate() {
		return publicationDate;
	}


	public void setPublicationDate(LocalDateTime publicationDate) {
		this.publicationDate = publicationDate;
	}
	

	
}
