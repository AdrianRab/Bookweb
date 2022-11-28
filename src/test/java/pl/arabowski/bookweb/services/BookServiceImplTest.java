package pl.arabowski.bookweb.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static pl.arabowski.bookweb.model.enums.Genres.HISTORICAL_FICTION;
import static pl.arabowski.bookweb.model.enums.Genres.PROGRAMMING;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import pl.arabowski.bookweb.model.Author;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.repositories.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {


    public static final String SOME_TITLE = "Java Effective Programming";
    private static final Long SOME_ID = 1L;
    public static final Book SOME_BOOK = Book.builder()
            .id(SOME_ID)
            .title(SOME_TITLE)
            .genre(Set.of(PROGRAMMING))
            .build();
    @Mock
    private BookRepository repository;

    private BookService cut;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cut = new BookServiceImpl(repository);
    }

    @Test
    void shouldCalculateRatingWhenNewRateHasBeenAdded() {
        //given
        List<Double> rating = new ArrayList<>(List.of(5.0, 4.0));
        Book book = Book.builder().rating(rating).build();
        double rate = 6.0;

        //when
        when(repository.saveAndFlush(book)).thenReturn(book);
        double result = cut.calculateRating(book, rate);

        //then
        assertThat(result).isEqualTo(5.0);
        verify(repository, times(1)).saveAndFlush(book);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldFindByGenre() {
        //given
        Book someBook = Book.builder()
                .title("Under the Eagle")
                .genre(Set.of((HISTORICAL_FICTION)))
                .build();
        List<Book> resultFromDb = Collections.singletonList(someBook);

        //when
        when(repository.findAllByGenre(any())).thenReturn(resultFromDb);
        List<Book> result = cut.findByGenre(HISTORICAL_FICTION);

        //then
        verify(repository, times(1)).findAllByGenre(any());
        verifyNoMoreInteractions(repository);
        assertThat(result)
                .isNotEmpty()
                .containsExactlyInAnyOrder(someBook);
    }

    @Test
    void shouldFindByAuthorLastName() {
        //given
        Author author = Author.builder()
                .firstName("Joshua")
                .lastName("Bloch")
                .id(SOME_ID).build();
        Book book = Book.builder()
                .id(SOME_ID)
                .title(SOME_TITLE)
                .genre(Set.of(PROGRAMMING))
                .authors(Set.of(author))
                .build();

        //when
        when(repository.findAllBooksByAuthorLastNameOrderByTitleAsc(author.getLastName())).thenReturn(List.of(book));
        List<Book> result = cut.findByAuthorLastName(author.getLastName());

        //then
        assertThat(result).isNotNull()
                .isNotEmpty()
                .containsExactly(book);
        verify(repository, times(1)).findAllBooksByAuthorLastNameOrderByTitleAsc(author.getLastName());
        verifyNoMoreInteractions(repository);
    }


    @Test
    void shouldFindByTitle() {
        //given
        //when
        when(repository.findByTitleOrderByTitleAsc(SOME_TITLE)).thenReturn(List.of(SOME_BOOK));
        List<Book> result = cut.findByTitle(SOME_TITLE);

        //then
        assertThat(result).isNotNull()
                .isNotEmpty()
                .containsExactly(SOME_BOOK);
        verify(repository, times(1)).findByTitleOrderByTitleAsc(SOME_TITLE);
        verifyNoMoreInteractions(repository);
    }


    @Test
    void shouldFindBookById() {
        //given
        //when
        when(repository.findById(SOME_ID)).thenReturn(Optional.of(SOME_BOOK));
        Book result = cut.getBook(SOME_ID);

        //then
        assertThat(result).isNotNull().isEqualTo(SOME_BOOK);
        verify(repository, times(1)).findById(SOME_ID);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldThrowExceptionWhenBookIdNotFound() {
        //given
        String msg = "No Book with id 1 exists!";

        //when
        when(repository.findById(SOME_ID)).thenReturn(Optional.empty());

        //then
        assertThatExceptionOfType(EmptyResultDataAccessException.class)
                .isThrownBy(() -> cut.getBook(SOME_ID)).withMessage(msg);
        verify(repository, times(1)).findById(SOME_ID);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldDeleteBookById() {
        //given
        //when
        doNothing().when(repository).deleteById(SOME_ID);
        cut.delete(SOME_ID);

        //then
        verify(repository, times(1)).deleteById(SOME_ID);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldUpdateBook(){
        //given
        //when
        when(repository.save(SOME_BOOK)).thenReturn(SOME_BOOK);
        Book result = cut.save(SOME_BOOK);
        //then
        verify(repository, times(1)).save(SOME_BOOK);
        verifyNoMoreInteractions(repository);
        assertThat(result)
                .isNotNull()
                .isEqualTo(SOME_BOOK);
    }

    @Test
    void shouldListTop20Books() {
        //given
        List<Book> top20Books = prepareBooks(20);

        //when
        when(repository.findTop20ByOrderByRateDesc()).thenReturn(top20Books);
        List<Book> result = cut.getTopTwentyBooks();

        //then
        assertThat(result).isNotEmpty()
                .hasSize(20)
                .containsAll(top20Books);
        verify(repository, times(1)).findTop20ByOrderByRateDesc();
        verifyNoMoreInteractions(repository);
    }

    private List<Book> prepareBooks(int countBooks) {
        List<Book> books = new ArrayList<>(countBooks);
        for (int i = 0; i < countBooks; i++) {
            Book book = Book.builder()
                    .title(SOME_TITLE + i)
                    .id((long) i)
                    .rating(List.of((double) i))
                    .build();
            books.add(book);
        }
        return books;
    }
}
