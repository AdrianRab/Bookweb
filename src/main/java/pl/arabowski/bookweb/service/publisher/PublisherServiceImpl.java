package pl.arabowski.bookweb.service.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.repositories.PublisherRepository;

@Service
@Slf4j
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private PublisherRepository publisherRepo;

	@Override
	public Publisher findByPublisherName(String name) {
		return publisherRepo.findByName(name);
	}

	@Override
	public Iterable<Publisher> listAllPublishers() {
		return publisherRepo.findAll();
	}

	@Override
	public Publisher findById(long id) {
		return publisherRepo.findById(id);
	}

	@Override
	public void save(Publisher publisher) {
		log.info("Persisting publisher {}", publisher);
		publisherRepo.saveAndFlush(publisher);
	}

}
