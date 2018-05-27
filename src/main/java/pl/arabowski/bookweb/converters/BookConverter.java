package pl.arabowski.bookweb.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.repositories.BookRepository;

@Component
public class BookConverter implements Converter<String, Book> {
	
	@Autowired
	BookRepository bookRepo;
	
	@Override
	public Book convert(String id) {
		System.out.println("been here - book converter");
		return bookRepo.findById(Long.parseLong(id));
	}

}
