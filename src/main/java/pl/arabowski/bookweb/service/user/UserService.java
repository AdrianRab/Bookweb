package pl.arabowski.bookweb.service.user;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.User;

public interface UserService {

	User getUser(UserDetails userDetails);

	User save (User user);

	void delete(long id);
	ModelAndView register(String password, String passwordConfirmed, User user, BindingResult result);
	
	void addBookToOwned(User user, Book book);
	
	void removeBookFromOwned(User user, Book book);
	
	void addBookToRead(User user,Book book);
	
	void removeBookFromRead(User user,Book book);
	
	void addBookToWannaRead(User user,Book book);
	
	void removeBookFromWannaRead(User user,Book book);
	
	void addBookToReading(User user,Book book);
	
	void removeBookFromReading(User user,Book book);
	
	void addRating(User user, long bookId, double rate);
	
	double getRating(User user,long bookId);
	
	String checkIfOwned(Set<Book> owned, Book book);
	
	String checkIfRead(Set<Book> read,Book book);
	
	String checkIfReading(Set<Book> reading,Book book);
	
	String checkIfWannaRead(Set<Book> owned, Book book);
}
