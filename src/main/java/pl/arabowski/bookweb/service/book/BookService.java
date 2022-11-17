package pl.arabowski.bookweb.service.book;

import java.util.List;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.enums.Genres;

public interface BookService {
	
	Iterable<Book> topTwentyBooks();
	
	void rateBook(Book book, double rate);
	
	List<Book> findByGenre(Genres genre);
	
	Iterable<Book> findByAuthor(String authorLastName);
	
	Iterable<Book> findByTitle(String title);
	
	double countRating(Book book);

	List<Genres> bookGenre();

	Book getBook(long bookId);

	void delete(long id);

	Book save(Book book);
}
