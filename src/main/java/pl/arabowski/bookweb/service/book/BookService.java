package pl.arabowski.bookweb.service.book;

import pl.arabowski.bookweb.model.Book;

public interface BookService {
	
	public Iterable<Book> topTwentyBooks();
	
	public void rateBook(Book book, double rate);
	
	public Iterable<Book> findByGenre(String genre);
	
	public Iterable<Book> findByAuthor(String authorLastName);
	
	public Iterable<Book> findByTitle(String title);
	
	public double countRating(Book book);
;
	public Iterable<String> bookGenre();
}
