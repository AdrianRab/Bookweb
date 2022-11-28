package pl.arabowski.bookweb.services;

import java.util.List;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.enums.Genres;

public interface BookService {

	List<Book> getTopTwentyBooks();

	List<Book> findByGenre(Genres genre);
	
	List<Book> findByAuthorLastName(String authorLastName);

	List<Book> findByTitle(String title);
	
	double calculateRating(Book book, double rate);

	Book getBook(Long bookId);

	void delete(Long id);

	Book save(Book book);
}
