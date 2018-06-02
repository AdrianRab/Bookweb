package pl.arabowski.bookweb.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailIgnoreCase(String email);

	User findFirstByUsername(String username);

	@Query("select u from User u where u.username like ?1%")
	List<User> findByFirstNameStartsWith(String username);

	@Query("select u from User u where u.username like %?1")
	List<User> findByFirstnameEndsWith(String username);

	User findById(long id);
	
	@Query("select u.owned from User u where u.id like ?1")
	Set<Book> findOwnedByUserId(long id);
	
	@Query("select u.read from User u where u.id like ?1")
	Set<Book> findReadByUserId(long id);
	
	@Query("select u.wannaRead from User u where u.id like ?1")
	Set<Book> findWannaReadByUserId(long id);
	
	@Query("select u.reading from User u where u.id like ?1")
	Set<Book> findReadingByUserId(long id);
}
