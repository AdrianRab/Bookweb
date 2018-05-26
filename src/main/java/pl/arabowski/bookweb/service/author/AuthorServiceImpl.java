package pl.arabowski.bookweb.service.author;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.arabowski.bookweb.model.Author;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.repositories.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	private AuthorRepository authorRepo;
	
	@Override
	public Author findByAuthorId(long id) {
		Author author = authorRepo.findById(id);
		return author;
	}

	@Override
	public Iterable<Author> findAll() {
		List<Author> authors = authorRepo.findAll();
		return authors;
	}

	@Override
	public Author findByAuthorByLastName(String lastName) {
		Author author = authorRepo.findByLastName(lastName);
		return author;
	}

	@Override
	public void addBookToAuthor(Book book, Author author) {
		Set<Book> books = author.getBooks();
		books.add(book);
		authorRepo.saveAndFlush(author);
	}

	@Override
	public void removeBookFromAuthor(Book book, Author author) {
		Set<Book> books = author.getBooks();
		Iterator<Book> iterator = books.iterator();
		while (iterator.hasNext()) {
			if(iterator.next().getId() == book.getId()) {
				iterator.remove();
			}
		}
	}

	public Set<Book> findAuthorBooks(long id) {
		Author author = findByAuthorId(id);
		Set<Book> books=  author.getBooks();
		return books;
	}

}
