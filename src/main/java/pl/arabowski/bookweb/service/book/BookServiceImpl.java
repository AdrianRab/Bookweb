package pl.arabowski.bookweb.service.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	private BookRepository bookRepo;

	@Autowired
	public BookServiceImpl(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}


	@Override
	public void rateBook(Book book, double rate) {
		List<Double> rates = book.getRating();
		rates.add(rate);
		countRating(book);
		bookRepo.saveAndFlush(book);
	}

	@Override
	public Iterable<Book> findByGenre(String genre) {
		List<Book> books = bookRepo.findAll();
		List<Book> foundBooks = new ArrayList<>();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getGenre().contains(genre)) {
				foundBooks.add(books.get(i));
			}
		}
		return foundBooks;
	}

	@Override
	public Iterable<Book> findByAuthor(String authorLastName) {
		List<Book> books = bookRepo.findAllBooksByAuthorLastName(authorLastName);
		return books;
	}

	@Override
	public Iterable<Book> findByTitle(String title) {
		List<Book> books = bookRepo.findByTitleOrderByTitleAsc(title);
		return books;
	}

	@Override
	public double countRating(Book book) {
		List<Double> ratings = book.getRating();
		if (ratings.size() > 0) {
			double rating = 0;
			double sum = 0;
			for (int i = 0; i < ratings.size(); i++) {
				sum += ratings.get(i);
			}
			rating = sum/ratings.size();
			book.setRate(rating);
			return rating;
		}
		book.setRate(0);
		return 0;
	}
 
	@Override
	public List<Book> topTwentyBooks() {
		List<Book> top20Books = bookRepo.findTop20ByOrderByRateDesc();
		return top20Books;
	}

	@Override
	public Iterable<String> bookGenre() {
		List<String> genres = new ArrayList<>();
		genres.add("biography");
		genres.add("fantasy");
		genres.add("history");
		genres.add("horror");
		genres.add("classic");
		genres.add("programming");
		genres.add("adventure");
		genres.add("crime fiction");
		genres.add("poetry");
		genres.add("historical fiction");
		genres.add("fable");
		genres.add("science fiction");
		return genres;
	}

}
