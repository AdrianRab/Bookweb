package pl.arabowski.bookweb.services.book;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.enums.Genres;
import pl.arabowski.bookweb.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepo;

    @Autowired
    public BookServiceImpl(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> findByGenre(Genres genre) {
        return bookRepo.findAllByGenre(genre.name());
    }

    @Override
    public Iterable<Book> findByAuthor(String authorLastName) {
        return bookRepo.findAllBooksByAuthorLastName(authorLastName);
    }

    @Override
    public Iterable<Book> findByTitle(String title) {
        return bookRepo.findByTitleOrderByTitleAsc(title);
    }

    @Override
    public double calculateRating(Book book, double rate) {
        List<Double> ratings = book.getRating();
        ratings.add(rate);
        double sum = 0;
        for (Double aDouble : ratings) {
            sum += aDouble;
        }
        double rating = sum / ratings.size();
        book.setRate(rating);
        bookRepo.saveAndFlush(book);
        return rating;
    }

    @Override
    public List<Book> topTwentyBooks() {
        return bookRepo.findTop20ByOrderByRateDesc();
    }

    @Override
    public List<Genres> bookGenre() {
        return Arrays.asList(Genres.values());
    }

    @Override
    public Book getBook(Long bookId) {
        return bookRepo.findById(bookId).orElseThrow(() -> new EmptyResultDataAccessException(String.format("No Book with id %s exists!", bookId), 1));
    }

    @Override
    public void delete(Long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepo.save(book);
    }
}
