package pl.arabowski.bookweb.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.arabowski.bookweb.model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>{
	Optional<Publisher> findByName(String name);
}
