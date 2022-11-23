package pl.arabowski.bookweb.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.model.UserDto;
import pl.arabowski.bookweb.repositories.UserRepository;
import pl.arabowski.bookweb.services.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private final UserRepository userRepo;
	
	@Autowired
	public HomeController(UserRepository userRepository, UserService userService) {
		this.userRepo = userRepository;
		this.userService = userService;
	}

	@GetMapping(value = { "/", "/home", "/about" })
	public String homePage() {
		return "home";
	}

	@GetMapping("/register")
	public ModelAndView addUser() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("userDto", new UserDto());
		mav.setViewName("user/registerForm");
		return mav;
	}

	@PostMapping("/register")
	public ModelAndView addUser(@Valid UserDto user, BindingResult result) {
		return userService.register(user, result);
	}

	@GetMapping("/login")
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginPage");
		return mav;
	}

	@PostMapping("/login")
	public ModelAndView loggedUser(@RequestParam String email) {
		ModelAndView mav = new ModelAndView();
		User user = userRepo.findByEmailIgnoreCase(email);
		if (user != null) {
			mav.addObject("user", user);
			mav.setViewName("home");
			return mav;
		}
		mav.setViewName("loginPage");
		return mav;
	}
}
