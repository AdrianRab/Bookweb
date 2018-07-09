package pl.arabowski.bookweb.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Author;
import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.model.User;

@Service
public class AdminServiceImpl implements AdminService{

	@Override
	public ModelAndView deleteBook(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView editAuthor(Author author, BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView deleteAuthor(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView editPublisher(Publisher publisher, BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView deletePublisher(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAdminRights(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAdminRights(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ModelAndView editUser(User user, BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView deleteUser(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
