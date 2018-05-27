package pl.arabowski.bookweb.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.arabowski.bookweb.model.Author;
import pl.arabowski.bookweb.repositories.AuthorRepository;

@Component
public class AuthorConverter implements Converter<String, Author> {

	@Autowired
	AuthorRepository authorRepo;

	public Author convert(String id) {
		System.out.println("bylem tu - Author converter");
		return authorRepo.findById(Long.parseLong(id));
	}

}
