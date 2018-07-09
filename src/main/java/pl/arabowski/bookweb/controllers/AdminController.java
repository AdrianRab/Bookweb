package pl.arabowski.bookweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Author;
import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.repositories.AuthorRepository;
import pl.arabowski.bookweb.repositories.UserRepository;
import pl.arabowski.bookweb.service.publisher.PublisherServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private PublisherServiceImpl publService;
	
	@GetMapping("/add-admin/{id}")
	public ModelAndView addAdminRights(@PathVariable long id) {
		return adminService.addAdminRights(id);
	}
	
	@GetMapping("/remove-admin/{id}")
	public ModelAndView removeAdminRights(@PathVariable long id) {
		return adminService.removeAdminRights(id);
	}
	
	@GetMapping("/panel")
	public ModelAndView mainAdminPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("adminPanel");
		return mav;
	}
	
	@GetMapping("/delete-book/{id}")
	public ModelAndView deleteBook(@PathVariable long id) {
		return adminService.deleteBook(id);
	}
	
	@GetMapping("/edit-author/{id}")
	public ModelAndView editAuthor(@PathVariable long id) {
		return adminService.editAuthor(id);
	}
	
	@GetMapping("/delete-author/{id}")
	public ModelAndView deleteAuthor(@PathVariable long id) {
		return adminService.deleteAuthor(id);
	}
	
	@GetMapping("/edit-publisher/{id}")
	public ModelAndView editPublisher(@PathVariable long id) {
		return adminService.editPublisher(id);
	}
	
	@GetMapping("/delete-publisher/{id}")
	public ModelAndView deletePublisher(@PathVariable long id) {
		return adminService.deletePublisher(id);
	}
	
	@GetMapping("/edit-user/{id}")
	public ModelAndView editUser(@PathVariable long id) {
		return adminService.editUser(id);
	}
	
	@GetMapping("/delete-user/{id}")
	public ModelAndView deleteUser(@PathVariable long id) {
		return adminService.deleteUser(id);
	}
	
	@ModelAttribute("listOfPublishers")
	List<Publisher> allPublishers() {
		return (List<Publisher>) publService.listAllPublishers();
	}

	@ModelAttribute("listOfAuthors")
	List<Author> allAuthors() {
		return authorRepo.findAll();
	}
	
	@ModelAttribute("allUsers")
	List<User>allUsers(){
		return userRepo.findAll();
	}
}
