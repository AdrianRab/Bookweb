package pl.arabowski.bookweb.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Author;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.repositories.AuthorRepository;
import pl.arabowski.bookweb.repositories.BookRepository;
import pl.arabowski.bookweb.service.book.BookServiceImpl;
import pl.arabowski.bookweb.service.publisher.PublisherServiceImpl;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private BookServiceImpl bookService;

	@Autowired
	private PublisherServiceImpl publService;
	
	@Autowired
	private AuthorRepository authorRepo;

	@GetMapping("/add")
	public ModelAndView addBook() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("book", new Book());
		mav.setViewName("book/addNew");
		return mav;
	}

	@PostMapping("/add")
	public ModelAndView addBook(@Valid Book book, BindingResult result, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if (!result.hasErrors()) {
			bookService.countRating(book);
			bookRepo.saveAndFlush(book);
			session.setAttribute("book", book);
			mav.addObject("book", book);
			mav.setViewName("redirect:http://localhost:8090/book/details/" + book.getId());
			return mav;
		} else {
			mav.setViewName("book/addNew");
			return mav;
		}
	}

	@GetMapping("/all")
	public ModelAndView allBooks() {
		ModelAndView mav = new ModelAndView();
		List<Book> books = bookRepo.findAll();
		mav.addObject("booksList", books);
		mav.setViewName("book/all");
		return mav;
	}

	@GetMapping("/top-rated")
	public ModelAndView topRatedBooks() {
		ModelAndView mav = new ModelAndView();
		List<Book> topBooks = bookService.topTwentyBooks();
		mav.setViewName("book/topRated");
		mav.addObject("topRatedBooks", topBooks);
		return mav;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView editBook(@PathVariable long id) {
		ModelAndView mav = new ModelAndView();
		Book book = bookRepo.findById(id);
		mav.addObject("book", book);
		mav.setViewName("book/edit");
		return mav;
	}

	@PostMapping("/edit/{id}")
	public ModelAndView editBook(@Valid Book book, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if (!result.hasErrors()) {
			bookRepo.saveAndFlush(book);
			mav.setViewName("redirect:http://localhost:8090/book/details/" + book.getId());
			return mav;
		} else {
			mav.setViewName("book/edit");
			return mav;
		}
	}

	@GetMapping("/details/{id}")
	public ModelAndView bookDetails(@PathVariable long id) {
		ModelAndView mav = new ModelAndView();
		Book book = bookRepo.findById(id);
		Date createdDate = book.getCreated();
		book.setCreated(createdDate);
		mav.addObject("book", book);
		mav.setViewName("book/details");
		return mav;
	}

	@GetMapping("/details/{id}/rating")
	public ModelAndView rateBook(@PathVariable long id, @RequestParam String rateParam) {
		ModelAndView mav = new ModelAndView();
		System.out.println(rateParam);
		Book book = bookRepo.findById(id);
		bookService.rateBook(book, Double.valueOf(rateParam));
		mav.addObject("confirmation", "Book has been succesfully rated.");
		mav.addObject("book", book);
		mav.setViewName("redirect:http://localhost:8090/book/details/" + book.getId());
		return mav;
	}

	@ModelAttribute("listOfGenres")
	List<String> genres() {
		List<String> genres = (List<String>) bookService.bookGenre();
		return genres;
	}

	@ModelAttribute("listOfRates")
	List<Double> rates() {
		List<Double> rates = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			rates.add((double) i);
		}
		return rates;
	}

	@ModelAttribute("listOfPublishers")
	List<Publisher> allPublishers() {
		return (List<Publisher>) publService.listAllPublishers();
	}
	
	@ModelAttribute("listOfAuthors")
	List<Author> allAuthors() {
		return authorRepo.findAll();
	}
}
