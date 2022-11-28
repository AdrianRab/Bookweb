package pl.arabowski.bookweb.services;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.arabowski.bookweb.repositories.AuthorRepository;
import pl.arabowski.bookweb.services.author.AuthorServiceImpl;

public class AuthorServiceImplTest {
	
	@Mock private AuthorRepository authorRepository;
	
	@Rule public MockitoRule rule = MockitoJUnit.rule();
	
	@BeforeEach
	void mockAuthorRepository() {
		
	}
	
	@Test
	@DisplayName("Check if AuthorRepository has been mocked")
	public void shouldVerifyMockigRepository() {
		MockitoAnnotations.initMocks(this);
		AuthorServiceImpl authorService = new AuthorServiceImpl(authorRepository);
		authorService.findByAuthorId(1L);
		Mockito.verify(authorRepository).findById(1L);
	}
	
	@Test
	@DisplayName("Check find by Id")
	public void shouldFIndAuthorById() {
		
	}
	
//	@Test
//	public void shouldRemoveBookFromAuthor() {
//		//given
//		Author author = new Author();
//		Book book1 = new Book();
//		Book book2 = new Book();
//		Book book3 = new Book();
//		Set<Book> authorsBooks = new HashSet<>();
//		authorsBooks.add(book1);
//		authorsBooks.add(book2);
//		authorsBooks.add(book3);
//		author.setBooks(authorsBooks);	
//		//when
//		authorService.removeBookFromAuthor(book2, author);
//		//then
//		assertFalse(authorsBooks.contains(book2));
//	}
	
}
