package pl.arabowski.bookweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.arabowski.bookweb.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	User findByUsername(String username);

	@Query("select u from User u where u.username like ?1%")
	List<User> findByFirstNameStartsWith(String username);

	@Query("select u from User u where u.username like %?1")
	List<User> findByFirstnameEndsWith(String username);
}
