package pl.arabowski.bookweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {   
    
    @GetMapping(value = { "/", "/home", "/about" })
    public String homePage() {
    	return "home";
    }
    
    @GetMapping("/hello")
    public String greeting() {
    	return "testPage";
    }
    
    
    @GetMapping("/test")
    @ResponseBody
    public String test() {
    	return "działa kurwa";
    }
	
}
