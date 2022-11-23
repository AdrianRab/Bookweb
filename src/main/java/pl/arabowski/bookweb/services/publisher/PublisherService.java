package pl.arabowski.bookweb.services.publisher;

import pl.arabowski.bookweb.model.Publisher;

public interface PublisherService {
	
	Publisher findByPublisherName(String name);
	
	Iterable<Publisher> listAllPublishers();
	
	Publisher findById(long id);

	void save(Publisher publisher);
}
