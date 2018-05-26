package pl.arabowski.bookweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.arabowski.bookweb.model.Author;

public interface AuthorRepository extends JpaRepository<Author,	Long>	{
	Author findByLastName(String lastName);
	
	@Query("select a from Author a where a.firstName like ?1%")
	List<Author> findByFirstNameStartsWith(String firstName);
	
	@Query("select a from Author a where a.lastName like ?1%")
	List<Author> findByLastNameStartsWith(String lastName);
	
	Author findById(long id);
}
