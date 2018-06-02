package pl.arabowski.bookweb.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.repositories.BookRepository;
import pl.arabowski.bookweb.repositories.UserRepository;
import pl.arabowski.bookweb.service.user.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BookRepository bookRepo;
	
	@GetMapping("/owned")
	public ModelAndView userOwnedBooks(@AuthenticationPrincipal UserDetails currentUser) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		Set<Book>owned = user.getOwned();
		mav.addObject("user",user);
		mav.addObject("ownedBooks", owned);
		mav.setViewName("user/ownedBooks");
		return mav;
	}
	
	@GetMapping("/read")
	public ModelAndView userReadBooks(@AuthenticationPrincipal UserDetails currentUser) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		Set<Book>read = user.getOwned();
		mav.addObject("user",user);
		mav.addObject("readBooks", read);
		mav.setViewName("user/readBooks");
		return mav;
	}
	
	@GetMapping("/reading")
	public ModelAndView userReadingBooks(@AuthenticationPrincipal UserDetails currentUser) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		Set<Book>reading = user.getOwned();
		mav.addObject("user",user);
		mav.addObject("readingBooks", reading);
		mav.setViewName("user/readingBooks");
		return mav;
	}
	
	@GetMapping("/to-read")
	public ModelAndView userToReadBooks(@AuthenticationPrincipal UserDetails currentUser) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		Set<Book>wannaRead = user.getOwned();
		mav.addObject("user",user);
		mav.addObject("wannaReadBooks", wannaRead);
		mav.setViewName("user/toRead");
		return mav;
	}
	
	@GetMapping("/edit")
	public ModelAndView editUser(@AuthenticationPrincipal UserDetails currentUser) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		mav.addObject("user", user);
		mav.setViewName("user/edit");
		return mav;
	}

	@PostMapping("/edit")
	public ModelAndView editUser(@Valid User user, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if (!result.hasErrors()) {
			userRepo.saveAndFlush(user);
			mav.addObject("user", user);
			mav.setViewName("user/profile");
			return mav;
		} else {
			mav.setViewName("user/edit");
			return mav;
		}
	}

	@GetMapping("/detele-account")
	public ModelAndView removeUser(@AuthenticationPrincipal UserDetails currentUser) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		mav.addObject("user", user);
		mav.setViewName("user/confirmation");
		return mav;
	}
	
	@GetMapping("/confirmation/{id}")
	public ModelAndView removeUserConfirmation(@PathVariable long id) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findById(id);
		userRepo.delete(user);
		mav.setViewName("redirect:http://localhost:8090/home");
		return mav;
	}
	
	@GetMapping("/my-page")
	public ModelAndView userProfile(@AuthenticationPrincipal UserDetails currentUser) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		mav.addObject("readingBooks", user.getReading());
		mav.addObject("user", user);
		mav.setViewName("user/profile");
		return mav;
	}
	
	@GetMapping("/add-to-owned/{bookId}")
	public ModelAndView addToOwnedBooks(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		Book book = bookRepo.findById(bookId);
		System.out.println("remove from owned controller entered");
		userServiceImpl.addBookToOwned(user, book);
		mav.addObject("user", user);
		mav.addObject("book", book);
		mav.setViewName("redirect:http://localhost:8090/book/details/" + book.getId());
		return mav;
	}
	
	@GetMapping("/remove-from-owned/{bookId}")
	public ModelAndView removeFromOwnedBooks(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		Book book = bookRepo.findById(bookId);
		userServiceImpl.removeBookFromOwned(user, book);
		mav.addObject("user", user);
		mav.addObject("book", book);
		mav.setViewName("redirect:http://localhost:8090/book/details/" + book.getId());
		return mav;
	}
	
	@GetMapping("/add-to-reading/{bookId}")
	public ModelAndView addToReadingBooks(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		Book book = bookRepo.findById(bookId);
		userServiceImpl.addBookToReading(user, book);
		mav.addObject("user", user);
		mav.addObject("book", book);
		mav.setViewName("redirect:http://localhost:8090/book/details/" + book.getId());
		return mav;
	}
	
	@GetMapping("/remove-from-reading/{bookId}")
	public ModelAndView removeFromReadingBooks(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		Book book = bookRepo.findById(bookId);
		userServiceImpl.removeBookFromReading(user, book);
		mav.addObject("user", user);
		mav.addObject("book", book);
		mav.setViewName("redirect:http://localhost:8090/book/details/" + book.getId());
		return mav;
	}
	
	@GetMapping("/add-read/{bookId}")
	public ModelAndView addToRead(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		Book book = bookRepo.findById(bookId);
		userServiceImpl.addBookToRead(user, book);
		mav.addObject("user", user);
		mav.addObject("book", book);
		mav.setViewName("redirect:http://localhost:8090/book/details/" + book.getId());
		return mav;
	}
	
	@GetMapping("/remove-from-read/{bookId}")
	public ModelAndView removeFromRead(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		Book book = bookRepo.findById(bookId);
		userServiceImpl.removeBookFromRead(user, book);
		mav.addObject("user", user);
		mav.addObject("book", book);
		mav.setViewName("redirect:http://localhost:8090/book/details/" + book.getId());
		return mav;
	}
	
	@GetMapping("/add-to-read/{bookId}")
	public ModelAndView addBookToRead(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		Book book = bookRepo.findById(bookId);
		userServiceImpl.addBookToWannaRead(user, book);
		mav.addObject("user", user);
		mav.addObject("book", book);
		mav.setViewName("redirect:http://localhost:8090/book/details/" + book.getId());
		return mav;
	}
	
	@GetMapping("/remove-from-to-read/{bookId}")
	public ModelAndView removeFromToRead(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(currentUser.getUsername());
		Book book = bookRepo.findById(bookId);
		userServiceImpl.removeBookFromWannaRead(user, book);
		mav.addObject("user", user);
		mav.addObject("book", book);
		mav.setViewName("redirect:http://localhost:8090/book/details/" + book.getId());
		return mav;
	}

}
