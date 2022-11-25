package pl.arabowski.bookweb.services.book;

import java.util.List;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.enums.Genres;

public interface BookService {
	
	Iterable<Book> topTwentyBooks();
	
//	void rateBook(Book book, double rate);
	
	List<Book> findByGenre(Genres genre);
	
	Iterable<Book> findByAuthor(String authorLastName);
	
	Iterable<Book> findByTitle(String title);
	
	double calculateRating(Book book, double rate);

	List<Genres> bookGenre();

	Book getBook(Long bookId);

	void delete(Long id);

	Book save(Book book);
}
