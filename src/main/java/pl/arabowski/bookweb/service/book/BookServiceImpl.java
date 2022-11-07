package pl.arabowski.bookweb.service.book;

import static pl.arabowski.bookweb.model.enums.Genres.ADVENTURE;
import static pl.arabowski.bookweb.model.enums.Genres.BIOGRAPHY;
import static pl.arabowski.bookweb.model.enums.Genres.CLASSIC;
import static pl.arabowski.bookweb.model.enums.Genres.CRIME;
import static pl.arabowski.bookweb.model.enums.Genres.FABLE;
import static pl.arabowski.bookweb.model.enums.Genres.FANTASY;
import static pl.arabowski.bookweb.model.enums.Genres.HISTORICAL_FICTION;
import static pl.arabowski.bookweb.model.enums.Genres.HISTORY;
import static pl.arabowski.bookweb.model.enums.Genres.HORROR;
import static pl.arabowski.bookweb.model.enums.Genres.POETRY;
import static pl.arabowski.bookweb.model.enums.Genres.PROGRAMMING;
import static pl.arabowski.bookweb.model.enums.Genres.SCI_FI;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void rateBook(Book book, double rate) {
		List<Double> rates = book.getRating();
		rates.add(rate);
		countRating(book);
		bookRepo.saveAndFlush(book);
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
		return bookRepo.findTop20ByOrderByRateDesc();
	}

	@Override
	public List<Genres> bookGenre() {
		return Arrays.asList(Genres.values());
	}

}
