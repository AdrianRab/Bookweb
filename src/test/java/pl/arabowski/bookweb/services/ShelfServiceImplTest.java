package pl.arabowski.bookweb.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.services.book.BookService;

@ExtendWith(MockitoExtension.class)
class ShelfServiceImplTest {

    public static final Book SOME_BOOK = Book.builder().id(1L).title("Some title").build();
    public static final double SOME_RATE = 6.0;
    @Mock
    private UserService userService;
    @Mock
    private BookService bookService;

    private ShelfService cut;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cut = new ShelfServiceImpl(bookService, userService);
    }

    @Test
    void shouldAddBookToOwned() {
        //given
        User user = User.builder().owned(new HashSet<>()).build();
        User expectedUser = User.builder().owned(Set.of(SOME_BOOK)).build();

        //when
        when(userService.save(user)).thenReturn(expectedUser);
        cut.addBookToOwned(user, SOME_BOOK);

        //then
        verify(userService, times(1)).save(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void shouldRemoveBookFromOwned() {
        //given
        User user = User.builder().owned(new HashSet<>(Set.of(SOME_BOOK))).build();
        User expectedUser = User.builder().owned(new HashSet<>()).build();

        //when
        when(userService.save(user)).thenReturn(expectedUser);
        cut.removeBookFromOwned(user, SOME_BOOK);

        //then
        verify(userService, times(1)).save(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void shouldAddBookToRead() {
        //given
        User user = User.builder().read(new HashSet<>(Set.of(SOME_BOOK))).build();
        User expectedUser = User.builder().read(new HashSet<>()).build();

        //when
        when(userService.save(user)).thenReturn(expectedUser);
        cut.addBookToRead(user, SOME_BOOK);

        //then
        verify(userService, times(1)).save(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void shouldRemoveBookFromRead() {
        //given
        User user = User.builder().read(new HashSet<>(Set.of(SOME_BOOK))).build();
        User expectedUser = User.builder().read(new HashSet<>()).build();

        //when
        when(userService.save(user)).thenReturn(expectedUser);
        cut.removeBookFromRead(user, SOME_BOOK);

        //then
        verify(userService, times(1)).save(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void shouldAddBookToReading() {
        //given
        User user = User.builder().reading(new HashSet<>(Set.of(SOME_BOOK))).build();
        User expectedUser = User.builder().reading(new HashSet<>()).build();

        //when
        when(userService.save(user)).thenReturn(expectedUser);
        cut.addBookToReading(user, SOME_BOOK);

        //then
        verify(userService, times(1)).save(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void shouldRemoveBookFromReading() {
        //given
        User user = User.builder().reading(new HashSet<>(Set.of(SOME_BOOK))).build();
        User expectedUser = User.builder().reading(new HashSet<>()).build();

        //when
        when(userService.save(user)).thenReturn(expectedUser);
        cut.removeBookFromReading(user, SOME_BOOK);

        //then
        verify(userService, times(1)).save(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void shouldAddBookToWannaRead() {
        //given
        User user = User.builder().wannaRead(new HashSet<>(Set.of(SOME_BOOK))).build();
        User expectedUser = User.builder().wannaRead(new HashSet<>()).build();

        //when
        when(userService.save(user)).thenReturn(expectedUser);
        cut.addBookToWannaRead(user, SOME_BOOK);

        //then
        verify(userService, times(1)).save(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void shouldRemoveBookFromWantToRead() {
        //given
        User user = User.builder().wannaRead(new HashSet<>(Set.of(SOME_BOOK))).build();
        User expectedUser = User.builder().wannaRead(new HashSet<>()).build();

        //when
        when(userService.save(user)).thenReturn(expectedUser);
        cut.removeBookFromWannaRead(user, SOME_BOOK);

        //then
        verify(userService, times(1)).save(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void shouldRateBook() {
        //given
        User user = User.builder().username("some_username").rating(new HashMap<>()).build();
        User expectedUser = User.builder().rating(Map.of(SOME_BOOK.getId(), SOME_RATE)).build();
        double averageRate = 5.5;

        //when
        when(bookService.calculateRating(SOME_BOOK, SOME_RATE)).thenReturn(averageRate);
        when(userService.save(user)).thenReturn(expectedUser);
        cut.rateBook(user, SOME_BOOK, SOME_RATE);

        //then
        verify(bookService, times(1)).calculateRating(SOME_BOOK, SOME_RATE);
        verify(userService, times(1)).save(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void shouldReturnBookRating() {
        //given
        User user = User.builder().rating(Map.of(SOME_BOOK.getId(), SOME_RATE)).build();

        //when
        double result = cut.getUserRating(user, SOME_BOOK.getId());

        //then
        assertThat(result).isEqualTo(SOME_RATE);
    }

    @Test
    void shouldReturnZeroWhenBookNotRated() {
        //given
        Long differentBookId = 2L;
        User user = User.builder().rating(Map.of(differentBookId, SOME_RATE)).build();

        //when
        double result = cut.getUserRating(user, SOME_BOOK.getId());

        //then
        assertThat(result).isZero();
    }

}
