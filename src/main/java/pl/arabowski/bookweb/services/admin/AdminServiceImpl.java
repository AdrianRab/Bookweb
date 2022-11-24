package pl.arabowski.bookweb.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Author;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.model.UserRole;
import pl.arabowski.bookweb.repositories.AuthorRepository;
import pl.arabowski.bookweb.repositories.PublisherRepository;
import pl.arabowski.bookweb.repositories.UserRepository;
import pl.arabowski.bookweb.repositories.UserRoleRepository;
import pl.arabowski.bookweb.services.book.BookService;


@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired 
	private AuthorRepository authorRepository;

	@Autowired
	private BookService bookService;
	
	@Override
	public ModelAndView deleteBook(long id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:http://localhost:8090/admin/panel");
		Book book = bookService.getBook(id);
		bookService.delete(book.getId());
		return mav;
	}

	@Override
	public ModelAndView editAuthor(Author author, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if (!result.hasErrors()) {
			authorRepository.saveAndFlush(author);
			mav.setViewName("redirect:http://localhost:8090/author/all-books/"+author.getId());
			return mav;
		} else {
			mav.setViewName("/admin/editAuthor");
			return mav;
		}
	}

	@Override
	public ModelAndView deleteAuthor(long id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:http://localhost:8090/admin/panel");
		Author author = authorRepository.findById(id);
		authorRepository.delete(author);
		return mav;
	}

	@Override
	public ModelAndView editPublisher(Publisher publisher, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if (!result.hasErrors()) {
			publisherRepository.saveAndFlush(publisher);
			mav.setViewName("redirect:http://localhost:8090/publisher/details/"+publisher.getId());
			return mav;
		} else {
			mav.setViewName("/admin/editPublisher");
			return mav;
		}
	}

	@Override
	public ModelAndView deletePublisher(long id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:http://localhost:8090/admin/panel");
		Publisher  publisher = publisherRepository.findById(id);
		publisherRepository.delete(publisher);
		return mav;
	}

	@Override
	public void addAdminRights(long id) {
		User user = userRepository.findById(id);
		UserRole role = userRoleRepository.findById(user.getRole().getId()).get();
		role.setRole("ROLE_ADMIN");
		userRoleRepository.saveAndFlush(role);
		user.setRole(role);
		userRepository.saveAndFlush(user);	
	}

	@Override
	public void removeAdminRights(long id) {
		User user = userRepository.findById(id);
		UserRole role = userRoleRepository.findById(user.getRole().getId()).get();
		role.setRole("ROLE_USER");
		userRoleRepository.saveAndFlush(role);
		user.setRole(role);
		userRepository.saveAndFlush(user);	
	}

	@Override
	public ModelAndView editUser(User user, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if (!result.hasErrors()) {
			userRepository.saveAndFlush(user);
			mav.setViewName("redirect:http://localhost:8090/admin/my-page/"+user.getId());
			return mav;
		} else {
			mav.setViewName("/admin/editUser");
			return mav;
		}
	}

	@Override
	public ModelAndView deleteUser(long id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:http://localhost:8090/admin/panel");
		User  user = userRepository.findById(id);
		userRepository.delete(user);
		return mav;
	}
	
}
