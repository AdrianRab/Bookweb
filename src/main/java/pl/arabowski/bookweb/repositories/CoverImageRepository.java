package pl.arabowski.bookweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.arabowski.bookweb.model.CoverImage;

public interface CoverImageRepository extends JpaRepository<CoverImage, Long>{
	CoverImage findById (long id);
}
