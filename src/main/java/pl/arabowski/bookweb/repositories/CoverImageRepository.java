package pl.arabowski.bookweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.arabowski.bookweb.model.CoverImage;

@Repository
public interface CoverImageRepository extends JpaRepository<CoverImage, Long>{
	CoverImage findById (long id);
}
