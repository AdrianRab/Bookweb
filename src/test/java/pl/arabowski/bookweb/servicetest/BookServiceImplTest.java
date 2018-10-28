package pl.arabowski.bookweb.servicetest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.service.book.BookServiceImpl;

public class BookServiceImplTest {
	
	BookServiceImpl bookService = new BookServiceImpl();
	
	@Test
	public void shouldCountRating() {
		//given
		Book book = new Book();
		List<Double >rating = new ArrayList<>();
		rating.add(5.0);
		rating.add(4.0);
		double rate = 6.0;
		book.setRating(rating);
		//when
		rating.add(rate);
		double result = bookService.countRating(book);
		//then
		assertEquals(5.0, result, 0.1);
	}
	
	@Test
	public void shouldNotCountRating() {
		//given
		Book book = new Book();
		//when
		double result = bookService.countRating(book);
		//then
		assertEquals(0, result, 0.1);
	}

	//require database connection to test
	@Test
	public void shouldFindByGenre() {
		//given
		Book book1 = new Book();
		Book book2 = new Book();
		Book book3 = new Book();
		book1.setTitle("Clean code");
		book2.setTitle("Kanny 216 p.n.e.");
		book3.setTitle("Under the Eagle");
		Set<String>genre1 = new HashSet<>();
		genre1.add("programming");
		Set<String>genre2 = new HashSet<>();
		genre1.add("history");
		Set<String>genre3 = new HashSet<>();
		genre1.add("historical fiction");
		book1.setGenre(genre1);
		book2.setGenre(genre2);
		book3.setGenre(genre3);
		String genreToSeachBy = "historical fiction";
		//when
		List<Book> result = (List<Book>) bookService.findByGenre(genreToSeachBy);
		//then
		assertEquals(book3.getTitle(),result.get(0).getTitle());
	}
	
	@Test
	public void shouldLoadListofGenres() {
		//given
		String[] arrayOfGenres= {"biography", "fantasy", "history", "horror", "classic", "programming", "adventure", "crime fiction", "poetry", "historical fiction", "fable", "science fiction"};
		//when
		List<String> result = (List<String>) bookService.bookGenre();
		//then
		assertArrayEquals( arrayOfGenres, result.toArray());
	}

}
