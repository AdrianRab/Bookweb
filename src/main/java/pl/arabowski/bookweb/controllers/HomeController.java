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
import pl.arabowski.bookweb.service.user.UserServiceImpl;

@Controller
public class HomeController {   
    
	@Autowired
	private UserServiceImpl userServiceImpl;
	
    @GetMapping(value = { "/", "/home", "/about" })
    public String homePage() {
    	return "home";
    }
    
    @GetMapping("/hello")
    public String greeting() {
    	return "testPage";
    }
	
	@GetMapping("/register")
	public ModelAndView addUser() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", new User());
		mav.setViewName("user/registerForm");
		return mav;
	}

	@PostMapping("/register")
	public ModelAndView addUser(@RequestParam String password, @RequestParam String passwordConfirmed,  @Valid User user, BindingResult result) {
		return userServiceImpl.register(password, passwordConfirmed, user, result);
	}
}
