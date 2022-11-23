package pl.arabowski.bookweb.services;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.arabowski.bookweb.model.enums.Genres.HISTORICAL_FICTION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.enums.Genres;
import pl.arabowski.bookweb.repositories.BookRepository;
import pl.arabowski.bookweb.services.book.BookServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {


    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookServiceImpl bookService = new BookServiceImpl(repository);

    @Test
    public void shouldCountRating() {
        //given
        Book book = new Book();
        List<Double> rating = new ArrayList<>();
        rating.add(5.0);
        rating.add(4.0);
        double rate = 6.0;
        book.setRating(rating);
        //when
        rating.add(rate);
        double result = bookService.calculateRating(book);
        //then
        assertEquals(5.0, result, 0.1);
    }

    @Test
    public void shouldNotCountRating() {
        //given
        Book book = new Book();
        //when
        double result = bookService.calculateRating(book);
        //then
        assertEquals(0, result, 0.1);
    }

    @Test
    public void shouldFindByGenre() {
        //given
        Book someBook = Book.builder().title("Under the Eagle").genre(Sets.set(HISTORICAL_FICTION)).build();
        List<Book> resultFromDb = Collections.singletonList(someBook);
        //when
        when(repository.findAllByGenre(any())).thenReturn(resultFromDb);
        List<Book> result = bookService.findByGenre(HISTORICAL_FICTION);
        //then
        verify(repository, times(1)).findAllByGenre(any());
        assertFalse(result.isEmpty());
        assertEquals(someBook.getTitle(), result.get(0).getTitle());
        assertEquals(someBook.getGenre(), result.get(0).getGenre());
    }

    @Test
    public void shouldLoadListOfGenres() {
        //given
        List<Genres> expectedResult =  Arrays.asList(Genres.values());
        //when
        List<Genres> result = bookService.bookGenre();
        //then
        assertEquals(expectedResult, result);
    }

}
