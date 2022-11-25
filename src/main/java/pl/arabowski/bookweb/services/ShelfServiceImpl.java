package pl.arabowski.bookweb.services;

import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.services.book.BookService;

@Service
@Slf4j
public class ShelfServiceImpl implements ShelfService {

    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public ShelfServiceImpl(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public void addBookToOwned(User user, Book book) {
        Set<Book> owned = user.getOwned();
        owned.add(book);
        user.setOwned(owned);
        userService.save(user);
    }

    @Override
    public void removeBookFromOwned(User user, Book book) {
        Set<Book> owned = user.getOwned();
        owned.removeIf(book::equals);
        user.setOwned(owned);
        userService.save(user);
    }

    @Override
    public void addBookToRead(User user, Book book) {
        Set<Book> read = user.getRead();
        read.add(book);
        user.setRead(read);
        userService.save(user);
    }

    @Override
    public void removeBookFromRead(User user, Book book) {
        Set<Book> read = user.getRead();
        read.removeIf(book::equals);
        user.setRead(read);
        userService.save(user);
    }

    @Override
    public void addBookToWannaRead(User user, Book book) {
        Set<Book> wannaRead = user.getWannaRead();
        wannaRead.add(book);
        user.setWannaRead(wannaRead);
        userService.save(user);
    }

    @Override
    public void removeBookFromWannaRead(User user, Book book) {
        Set<Book> wannaRead = user.getWannaRead();
        wannaRead.removeIf(book::equals);
        user.setWannaRead(wannaRead);
        userService.save(user);
    }

    @Override
    public void addBookToReading(User user, Book book) {
        Set<Book> reading = user.getReading();
        reading.add(book);
        user.setReading(reading);
        userService.save(user);
    }

    @Override
    public void removeBookFromReading(User user, Book book) {
        Set<Book> reading = user.getReading();
        reading.removeIf(book::equals);
        user.setReading(reading);
        userService.save(user);
    }

    @Override
    public double getUserRating(User user, long bookId) {
        Map<Long, Double> ratings = user.getRating();
        if (ratings.containsKey(bookId)) {
            return ratings.get(bookId);
        } else {
            log.info("Book with id {} has not been rated yet", bookId);
            return 0;
        }
    }

    @Override
    public void rateBook(User user, Book book, double rate) {
        Map<Long, Double> ratings = user.getRating();
        ratings.put(book.getId(), rate);
        user.setRating(ratings);
        log.info("User {} rate for book {}. {} is {}", user.getUsername(), book.getId(), book.getTitle(), rate);
        bookService.calculateRating(book, rate);
        userService.save(user);
    }
}
