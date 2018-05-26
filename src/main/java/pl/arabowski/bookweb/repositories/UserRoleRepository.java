package pl.arabowski.bookweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.arabowski.bookweb.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
	UserRole findById(long id);
}
