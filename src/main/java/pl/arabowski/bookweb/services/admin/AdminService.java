package pl.arabowski.bookweb.services.admin;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Author;
import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.model.User;

public interface AdminService {
	
	ModelAndView deleteBook(long id);
	ModelAndView editAuthor(Author author, BindingResult result);
	ModelAndView deleteAuthor(long id);
//	ModelAndView editPublisher(Publisher publisher, BindingResult result);
//	ModelAndView deletePublisher(long id);
	void addAdminRights(long id);
	void removeAdminRights(long id);
	ModelAndView editUser(User user, BindingResult result);
	ModelAndView deleteUser(long id);
	
}
