package pl.arabowski.bookweb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Author;
import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.repositories.AuthorRepository;
import pl.arabowski.bookweb.repositories.UserRepository;
import pl.arabowski.bookweb.services.admin.AdminServiceImpl;
import pl.arabowski.bookweb.services.PublisherServiceImpl;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private PublisherServiceImpl publisherService;
	
	@GetMapping("/add-admin/{id}")
	public ModelAndView addAdminRights(@PathVariable long id) {
		 adminService.addAdminRights(id);
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("redirect:http://localhost:8090/admin/panel");
		 return mav;
	}
	
	@GetMapping("/remove-admin/{id}")
	public ModelAndView removeAdminRights(@PathVariable long id) {
		 adminService.removeAdminRights(id);
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("redirect:http://localhost:8090/admin/panel");
		 return mav;
	}
	
	@GetMapping("/panel")
	public ModelAndView mainAdminPage(@AuthenticationPrincipal UserDetails currentUser) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("admin", userRepo.findByEmailIgnoreCase(currentUser.getUsername()));
		mav.setViewName("admin/adminPanel");
		return mav;
	}
	
	@GetMapping("/my-page/{id}")
	public ModelAndView userProfile(@PathVariable long id) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findById(id);
		mav.addObject("readingBooks", user.getReading());
		mav.addObject("user", user);
		mav.addObject("adminMode", true);
		mav.setViewName("user/profile");
		return mav;
	}
	
	@GetMapping("/delete-book/{id}")
	public ModelAndView deleteBook(@PathVariable long id) {
		return adminService.deleteBook(id);
	}
	
	@GetMapping("/edit-author/{id}")
	public ModelAndView editAuthor(@PathVariable long id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/editAuthor");
		return mav;
	}
	
	@PostMapping("edit-author/{id}")
	public ModelAndView editAuthor(@Valid Author author, BindingResult result) {
		return adminService.editAuthor(author, result);
	}
	
	@GetMapping("/delete-author/{id}")
	public ModelAndView deleteAuthor(@PathVariable long id) {
		return adminService.deleteAuthor(id);
	}


	@PostMapping("edit-publisher")
	public ModelAndView editPublisher(@Valid Publisher publisher, BindingResult result) {
		return publisherService.editPublisher(publisher, result);
	}

	@GetMapping("/delete-publisher/{id}")
	public ModelAndView deletePublisher(@PathVariable long id) {
		return publisherService.deletePublisher(id);
	}
	
	@GetMapping("/edit-user/{id}")
	public ModelAndView editUser(@PathVariable long id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/editUser");
		return mav;
	}
	
	@PostMapping("/edit-user/{id}")
	public ModelAndView editUser(@Valid User user, BindingResult result) {
		return adminService.editUser(user, result);
	}
	
	@GetMapping("/delete-user/{id}")
	public ModelAndView deleteUser(@PathVariable long id) {
		return adminService.deleteUser(id);
	}
	
	@GetMapping("/all-users")
	public ModelAndView allUsersList(@AuthenticationPrincipal UserDetails currentUser) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/allUsers");
		mav.addObject("allUsers", userRepo.findAll());
		mav.addObject("admin", userRepo.findByEmailIgnoreCase(currentUser.getUsername()));
		return mav;
	}
	
	@ModelAttribute("listOfPublishers")
	List<Publisher> allPublishers() {
		return publisherService.listAllPublishers();
	}

	@ModelAttribute("listOfAuthors")
	List<Author> allAuthors() {
		return authorRepo.findAll();
	}
}
