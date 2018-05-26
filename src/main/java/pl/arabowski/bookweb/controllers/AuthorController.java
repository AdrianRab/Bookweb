package pl.arabowski.bookweb.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Author;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.repositories.AuthorRepository;
import pl.arabowski.bookweb.service.author.AuthorServiceImpl;

@Controller
@RequestMapping("/author")
public class AuthorController {
	
	@Autowired 
	private AuthorRepository authorRepo;
	
	@Autowired
	private AuthorServiceImpl authorServImpl;
	
	@GetMapping("/add")
	public ModelAndView addAuthor() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("author", new Author());
		mav.setViewName("author/addNew");
		return mav;
	}
	
	@PostMapping("/add")
	public ModelAndView addAuthor(@Valid Author author, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if (!result.hasErrors()) {
			authorRepo.saveAndFlush(author);
			mav.addObject("author", authorRepo);
			mav.setViewName("redirect:http://localhost:8090/author/all");
			return mav;
		} else {
			mav.setViewName("author/addNew");
			return mav;
		}
	}
	
	@GetMapping("/all")
	public ModelAndView allAuthors() {
		ModelAndView mav = new ModelAndView();
		List<Author> authors = (List<Author>) authorServImpl.findAll();
		mav.addObject("authorsList", authors);
		mav.setViewName("author/all");
		return mav;
	}
	
	@GetMapping("/all-books/{id}")
	public ModelAndView allAuthors(@PathVariable long id) {
		ModelAndView mav = new ModelAndView();
		Author author = authorRepo.findById(id);
		Set<Book> books = authorServImpl.findAuthorBooks(id);
		mav.addObject("author", author);
		mav.addObject("authorBooks", books);
		mav.setViewName("author/all-books");
		return mav;
	}
	
//	@GetMapping("/add-new-book/{id{")
//	public ModelAndView addBookToAuthor(@PathVariable long id) {
//		ModelAndView mav = new ModelAndView();
//		Author author = authorRepo.findById(id);
//
//		mav.addObject("author", author);
//
//		mav.setViewName("redirect:http://localhost:8090/author/all-books");
//		return mav;
//	}
}
