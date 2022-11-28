package pl.arabowski.bookweb.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.CoverImage;
import pl.arabowski.bookweb.repositories.CoverImageRepository;
import pl.arabowski.bookweb.services.BookService;

@Controller
@RequestMapping("/cover/")
public class CoverImageController {
	@Autowired
	private CoverImageRepository coverImageRepo;

	@Autowired
	private BookService bookService;

	@GetMapping("/add/{id}")
	public ModelAndView addCover(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView();
		Book book = bookService.getBook(id);
		mav.addObject("book", book);
		mav.setViewName("cover/addCover");
		return mav;
	}

	@PostMapping("/add/{id}")
	public ModelAndView addCover(MultipartHttpServletRequest request, @PathVariable("id") long id,
			@RequestParam("fileUpload") MultipartFile fileUpload) {
		ModelAndView mav = new ModelAndView();
		CoverImage coverImg = new CoverImage();
		if (fileUpload != null) {
			try {
				coverImg.setName(fileUpload.getOriginalFilename());
				coverImg.setPic(fileUpload.getBytes());
				coverImageRepo.saveAndFlush(coverImg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Book book = bookService.getBook(id);
		book.setCover(coverImg);
		bookService.save(book);
		mav.addObject("book", book);
		mav.setViewName("redirect:http://localhost:8090/book/details/" + book.getId());
		return mav;
	}

	@GetMapping("/image-display/{id}")
	public void showImage(@PathVariable long id, HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {
		Book book = bookService.getBook(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(book.getCover().getPic());

		response.getOutputStream().close();
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView deleteImage(@PathVariable long id) {
		Book book = bookService.getBook(id);
		CoverImage cover = coverImageRepo.findById(book.getCover().getId());
		if(cover!=null) {
			book.setCover(null);
			coverImageRepo.delete(cover);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:http://localhost:8090/book/details/" + book.getId());
		return mav;
	}
}
