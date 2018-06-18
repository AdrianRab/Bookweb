package pl.arabowski.bookweb.service.authortest;

import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pl.arabowski.bookweb.model.Author;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.service.author.AuthorServiceImpl;

public class AuthorServiceImplTest {
	
	AuthorServiceImpl authorService = new AuthorServiceImpl();
	
	//require database connection to test
	
	@Test
	public void shouldRemoveBookFromAuthor() {
		//given
		Author author = new Author();
		Book book1 = new Book();
		Book book2 = new Book();
		Book book3 = new Book();
		Set<Book> authorsBooks = new HashSet<>();
		authorsBooks.add(book1);
		authorsBooks.add(book2);
		authorsBooks.add(book3);
		author.setBooks(authorsBooks);	
		//when
		authorService.removeBookFromAuthor(book2, author);
		//then
		assertFalse(authorsBooks.contains(book2));
	}

}
