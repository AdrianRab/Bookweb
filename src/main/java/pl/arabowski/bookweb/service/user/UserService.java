package pl.arabowski.bookweb.service.user;

import java.util.Set;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.User;

public interface UserService {

	public ModelAndView register(String password, String passwordConfirmed, User user, BindingResult result);
	
	public void addBookToOwned(User user, Book book);
	
	public void removeBookFromOwned(User user, Book book);
	
	public void addBookToRead(User user,Book book);
	
	public void removeBookFromRead(User user,Book book);
	
	public void addBookToWannaRead(User user,Book book);
	
	public void removeBookFromWannaRead(User user,Book book);
	
	public void addBookToReading(User user,Book book);
	
	public void removeBookFromReading(User user,Book book);
	
	public void addRating(User user, long bookId, double rate);
	
	public double getRating(User user,long bookId);
	
	public String checkIfOwned(Set<Book> owned, Book book);
	
	public String checkIfRead(Set<Book> read,Book book);
	
	public String checkIfReading(Set<Book> reading,Book book);
	
	public String checkIfWannaRead(Set<Book> owned, Book book);
}
