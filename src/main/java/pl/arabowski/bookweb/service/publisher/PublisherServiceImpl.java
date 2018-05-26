package pl.arabowski.bookweb.service.publisher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.repositories.PublisherRepository;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private PublisherRepository publisherRepo;

	@Override
	public Publisher findByPublisherName(String name) {
		Publisher publ = publisherRepo.findByName(name);
		return publ;
	}

	@Override
	public Iterable<Publisher> listAllPublishers() {
		List<Publisher> publishers = publisherRepo.findAll();
		return publishers;
	}

	@Override
	public Publisher findById(long id) {
		Publisher publisher = publisherRepo.findById(id);
		return publisher;
	}

}
