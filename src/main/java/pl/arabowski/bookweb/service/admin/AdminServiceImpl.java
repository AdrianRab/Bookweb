package pl.arabowski.bookweb.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Author;
import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.model.UserRole;
import pl.arabowski.bookweb.repositories.UserRepository;
import pl.arabowski.bookweb.repositories.UserRoleRepository;


@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired UserRoleRepository userRoleRepository;
	
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
		User user = userRepo.findById(id);
		UserRole role = userRoleRepository.findById(user.getRole().getId());
		role.setUserRole("ROLE_ADMIN");
		userRoleRepository.saveAndFlush(role);
		user.setRole(role);
		userRepo.saveAndFlush(user);
		
	}

	@Override
	public void removeAdminRights(long id) {
		User user = userRepo.findById(id);
		UserRole role = userRoleRepository.findById(user.getRole().getId());
		role.setUserRole("ROLE_USER");
		userRoleRepository.saveAndFlush(role);
		user.setRole(role);
		userRepo.saveAndFlush(user);
		
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
