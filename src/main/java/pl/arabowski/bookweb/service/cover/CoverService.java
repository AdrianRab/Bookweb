package pl.arabowski.bookweb.service.cover;

import org.springframework.core.io.ClassPathResource;

import pl.arabowski.bookweb.model.CoverImage;

public interface CoverService {
	
	public void savePictureToDB(ClassPathResource picturePath, String name) throws Exception;
	
	public CoverImage retrievePictureFromDB(long id);
}
