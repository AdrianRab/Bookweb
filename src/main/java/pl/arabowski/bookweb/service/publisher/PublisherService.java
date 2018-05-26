package pl.arabowski.bookweb.service.publisher;

import pl.arabowski.bookweb.model.Publisher;

public interface PublisherService {
	
	Publisher findByPublisherName(String name);
	
	Iterable<Publisher> listAllPublishers();
	
	Publisher findById(long id);
	
}
