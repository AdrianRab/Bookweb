package pl.arabowski.bookweb.services;

import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import pl.arabowski.bookweb.model.Publisher;

public interface PublisherService {
	
	Publisher findByName(String name);
	
	List<Publisher> listAllPublishers();
	
	Publisher findById(long id);

	ModelAndView addPublisher(String name);

	ModelAndView editPublisher(Publisher publisher, BindingResult result);
	ModelAndView deletePublisher(long id);
}
