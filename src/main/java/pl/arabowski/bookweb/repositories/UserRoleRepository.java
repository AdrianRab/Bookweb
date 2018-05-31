package pl.arabowski.bookweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.arabowski.bookweb.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
	UserRole findById(long id);
	
	UserRole findByuserRole(String userRole);
}
