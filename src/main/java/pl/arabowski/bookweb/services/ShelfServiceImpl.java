package pl.arabowski.bookweb.services;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.repositories.BookRepository;
import pl.arabowski.bookweb.repositories.UserRepository;
import pl.arabowski.bookweb.services.book.BookService;

@Service
public class ShelfServiceImpl implements ShelfService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Override
    public void addBookToOwned(User user, Book book) {
        Set<Book> owned = userRepository.findOwnedByUserId(user.getId());
        owned.add(book);
        user.setOwned(owned);
        userRepository.save(user);
    }

    @Override
    public void removeBookFromOwned(User user, Book book) {
        Set<Book> owned = userRepository.findOwnedByUserId(user.getId());
        owned.removeIf(book::equals);
        user.setOwned(owned);
        userRepository.save(user);
    }

    @Override
    public void addBookToRead(User user, Book book) {
        Set<Book> read = userRepository.findReadByUserId(user.getId());
        read.add(book);
        user.setRead(read);
        userRepository.save(user);

    }

    @Override
    public void removeBookFromRead(User user, Book book) {
        Set<Book> read = userRepository.findReadByUserId(user.getId());
        read.removeIf(book::equals);
        user.setRead(read);
        userRepository.save(user);

    }

    @Override
    public void addBookToWannaRead(User user, Book book) {
        Set<Book> wannaRead = userRepository.findWannaReadByUserId(user.getId());
        wannaRead.add(book);
        user.setWannaRead(wannaRead);
        userRepository.save(user);

    }

    @Override
    public void removeBookFromWannaRead(User user, Book book) {
        Set<Book> wannaRead = userRepository.findWannaReadByUserId(user.getId());
        wannaRead.removeIf(book::equals);
        user.setWannaRead(wannaRead);
        userRepository.save(user);

    }

    @Override
    public void addBookToReading(User user, Book book) {
        Set<Book> reading = userRepository.findReadingByUserId(user.getId());
        reading.add(book);
        user.setReading(reading);
        userRepository.save(user);
    }

    @Override
    public void removeBookFromReading(User user, Book book) {
        Set<Book> reading = userRepository.findReadingByUserId(user.getId());
        reading.removeIf(book::equals);
        user.setReading(reading);
        userRepository.save(user);

    }

    @Override
    public double getRating(User user, long bookId) {
        Map<Long, Double> ratings = user.getRating();
        if (ratings.containsKey(bookId)) {
            return ratings.get(bookId);
        } else {
            return 0;
        }
    }

    @Override
    public void rateBook(User user, Book book, double rate) {
        Map<Long, Double> ratings = user.getRating();
        ratings.put(book.getId(), rate);
        user.setRating(ratings);
        List<Double> rates = book.getRating();
        rates.add(rate);
        bookService.calculateRating(book);
        bookRepository.saveAndFlush(book);
        userRepository.save(user);
    }
}
