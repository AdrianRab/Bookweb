package pl.arabowski.bookweb.service.author;

import pl.arabowski.bookweb.model.Author;
import pl.arabowski.bookweb.model.Book;

public interface AuthorService {
	
	Author findByAuthorId(long id);
	
	Iterable <Author> findAll();
	
	Author findByAuthorByLastName(String lastName);
	
	void addBookToAuthor(Book book, Author author);
	
	void removeBookFromAuthor(Book book, Author author);
	
	Iterable <Book> findAuthorBooks(long id);
}

