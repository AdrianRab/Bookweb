package pl.arabowski.bookweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.arabowski.bookweb.model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>{
	Publisher findByName(String name);
	Publisher findById(long id);
}
