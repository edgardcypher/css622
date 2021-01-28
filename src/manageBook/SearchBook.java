package manageBook;

import java.time.LocalDateTime;
import java.util.List;

// To represent an interface which hold some methods for searching books based on certain criteria
// each method will be implemented by classes which implements this interface

public interface SearchBook {
	
	public List<Book> searchByTitle(String title);// search a book based on the title
	public List<Book> searchByAuthor(String author);// search a book based on the author
	public List<Book> searchBySubject(String subject);// search a book based on the subject
	public List<Book> searchByPublicationDate(LocalDateTime publishDate); // search a book based on the publication date
}
