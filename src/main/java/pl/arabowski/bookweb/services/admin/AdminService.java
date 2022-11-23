package pl.arabowski.bookweb.services.admin;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Author;
import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.model.User;

public interface AdminService {
	
	public ModelAndView deleteBook(long id);
	public ModelAndView editAuthor(Author author, BindingResult result);
	public ModelAndView deleteAuthor(long id);
	public ModelAndView editPublisher(Publisher publisher, BindingResult result);
	public ModelAndView deletePublisher(long id);
	public void addAdminRights(long id);
	public void removeAdminRights(long id);
	public ModelAndView editUser(User user, BindingResult result);
	public ModelAndView deleteUser(long id);
	
}
