package pl.arabowski.bookweb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.repositories.PublisherRepository;
import pl.arabowski.bookweb.service.publisher.PublisherServiceImpl;

@Controller
@RequestMapping("/publ")
public class PublisherController {
	
	@Autowired
	private PublisherRepository publisherRepo;
	
	@Autowired
	private PublisherServiceImpl publServImpl;
	
	@GetMapping("/add")
	public ModelAndView addPublisher() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("publisher", new Publisher());
		mav.setViewName("publ/addNew");
		return mav;
	}
	
	@PostMapping("/add")
	public ModelAndView addPublisher(@Valid Publisher publisher, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if (!result.hasErrors()) {
			publisherRepo.saveAndFlush(publisher);
			mav.addObject("publisher", publisher);
			mav.setViewName("redirect:http://localhost:8090/publ/all");
			return mav;
		} else {
			mav.setViewName("publ/addNew");
			return mav;
		}
	}
	
	@GetMapping("/all")
	public ModelAndView allPublishers() {
		ModelAndView mav = new ModelAndView();
		List<Publisher> publishers = (List<Publisher>) publServImpl.listAllPublishers();
		mav.addObject("publishersList", publishers);
		mav.setViewName("publ/all");
		return mav;
	}
}
