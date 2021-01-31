package manageBook;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// To represent list of books sorted based on certain criteria
// its will implements an interface SearchBook
public class BookCatalog implements SearchBook {
	
	private List<Book> listBooks;

	public BookCatalog(List<Book> allBooks) {
		listBooks = allBooks;
	}
	@Override
	public List<Book> searchByTitle(String title) {
		// Postcondition: a list of books listBooks  which match the  title criteria
		List<Book> listBooks =  new ArrayList<>();
		for (Book book : listBooks) {
			if(book !=null && book.getTitle().equals(title)) {
				listBooks.add(book);
			}
		}
		return listBooks;
	}

	@Override
	public List<Book> searchByAuthor(String author) {
		// Postcondition: a list of books listBooks which match the author criteria
		List<Book> listBooks =  new ArrayList<>();
		for (Book book : listBooks) {
			if(book !=null && book.getAuthor().equals(author)) {
				listBooks.add(book);
			}
		}
		return listBooks;
	}

	@Override
	public List<Book> searchBySubject(String subject) {
		// Postcondition: a list of books listBooks which match the subject criteria
		List<Book> listBooks =  new ArrayList<>();
		for (Book book : listBooks) {
			if(book !=null && book.getSubject().equals(subject)) {
				listBooks.add(book);
			}
		}
		return listBooks;
	}

	@Override
	public List<Book> searchByPublicationDate(LocalDateTime publishDate) {
		// Postcondition: a list of books listBooks which match the subject criteria
		List<Book> listBooks =  new ArrayList<>();
		for (Book book : listBooks) {
			if(book !=null && book.getPublicationDate().equals(publishDate)) {
				listBooks.add(book);
			}
		}
		return listBooks;
	}
	
	public List<Book> getListBooks() {
		return listBooks;
	}
	public void setListBooks(List<Book> listBooks) {
		this.listBooks = listBooks;
	}
	
	

}
