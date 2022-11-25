package pl.arabowski.bookweb.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.enums.Genres;
import pl.arabowski.bookweb.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepo;

    @Autowired
    public BookServiceImpl(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> findByGenre(Genres genre) {
        return bookRepo.findAllByGenre(genre.name());
    }

    @Override
    public List<Book> findByAuthorLastName(String authorLastName) {
        return bookRepo.findAllBooksByAuthorLastNameOrderByTitleAsc(authorLastName);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepo.findByTitleOrderByTitleAsc(title);
    }

    @Override
    public double calculateRating(Book book, double rating) {
        List<Double> ratings = book.getRating();
        ratings.add(rating);
        double sum = 0;
        for (Double rate : ratings) {
            sum += rate;
        }
        double averageRating = sum / ratings.size();
        book.setRate(averageRating);
        bookRepo.saveAndFlush(book);
        return averageRating;
    }

    @Override
    public List<Book> getTopTwentyBooks() {
        return bookRepo.findTop20ByOrderByRateDesc();
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
