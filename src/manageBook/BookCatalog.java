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
		List<Book> listBooks =  new ArrayList<>();
		for (Book book : listBooks) {
			if(book !=null && book.getPublicationDate().equals(publishDate)) {
				listBooks.add(book);
			}
		}
		return listBooks;
	}

}
