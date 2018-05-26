package pl.arabowski.bookweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.arabowski.bookweb.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query("select a.books from Author a where a.lastName = ?1")
	List<Book> findAllBooksByAuthorLastName(String lastName);
	
	Book findById(long id);
	
}
