package pl.arabowski.bookweb.service.cover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import pl.arabowski.bookweb.model.CoverImage;
import pl.arabowski.bookweb.repositories.CoverImageRepository;

@Service
public class CoverServiceImp implements CoverService{

	@Autowired
	private CoverImageRepository coverImageRepo;
	
	@Override
	public void savePictureToDB(ClassPathResource picturePath, String name) throws Exception {
		byte[]arrayPiC = new byte[(int) picturePath.contentLength()];
		picturePath.getInputStream().read(arrayPiC);
		CoverImage DaVinciCover= new CoverImage(name, arrayPiC);
		coverImageRepo.save(DaVinciCover);
	}

	@Override
	public CoverImage retrievePictureFromDB(long id) {
		Object image = coverImageRepo.findById(id);
		return (CoverImage)image;
	}

}
