package pl.arabowski.bookweb.services;

import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.User;

public interface ShelfService {
    void addBookToOwned(User user, Book book);

    void removeBookFromOwned(User user, Book book);

    void addBookToRead(User user, Book book);

    void removeBookFromRead(User user, Book book);

    void addBookToWannaRead(User user, Book book);

    void removeBookFromWannaRead(User user, Book book);

    void addBookToReading(User user, Book book);

    void removeBookFromReading(User user, Book book);

    void rateBook(User user, Book book, double rate);

    double getUserRating(User user, long bookId);
}
