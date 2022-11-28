package pl.arabowski.bookweb.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.services.BookService;

@Component
public class BookConverter implements Converter<String, Book> {
	
	@Autowired
	BookService bookService;
	
	@Override
	public Book convert(String id) {
		return bookService.getBook(Long.parseLong(id));
	}

}
