package pl.arabowski.bookweb.service.user;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.model.UserRole;
import pl.arabowski.bookweb.model.enums.UserRoles;
import pl.arabowski.bookweb.repositories.UserRepository;
import pl.arabowski.bookweb.repositories.UserRoleRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public ModelAndView register(String password, String passwordConfirmed, User user, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if (!(password.equals(passwordConfirmed))) {
			mav.addObject("errorMessage", "Provided passwords does not match. Please try again.");
			mav.setViewName("user/registerForm");
			System.out.println("wrong password");
			return mav;
		} else if (userRepository.findByEmailIgnoreCase(user.getEmail()) != null) {
			mav.addObject("errorMessage", "Provided e-mail is aleady in use.");
			mav.setViewName("user/registerForm");
			System.out.println("email exists");
			return mav;
		} else if (userRepository.findFirstByUsername(user.getUsername()) != null) {
			mav.addObject("errorMessage", "Username is already in use.");
			mav.setViewName("user/registerForm");
			System.out.println("username exists");
			return mav;
		} else {
			if (!result.hasErrors()) {
				System.out.println("entered no errors");
				UserRole userRole = new UserRole();
				userRole.setUser(user);
				userRole.setUserRole(String.valueOf(UserRoles.ROLE_USER));
				user.setEnabled(true);
				userRepository.saveAndFlush(user);
				userRoleRepository.saveAndFlush(userRole);
				mav.addObject("user", user);
				mav.setViewName("redirect:http://localhost:8090/user/my-page/"+user.getId());
				return mav;
			} else {
				System.out.println("Have errors in form");
				mav.setViewName("user/registerForm");
				return mav;
			}
		}
	}

	@Override
	public void addBookToOwned(User user,Book book) {
		Set<Book> owned = userRepository.findOwnedByUserId(user.getId());
		if(!owned.contains(book)) {
			owned.add(book);
		}
		user.setOwned(owned);
		userRepository.save(user);
	}

	@Override
	public void removeBookFromOwned(User user,Book book) {
		Set<Book> owned = userRepository.findOwnedByUserId(user.getId());
		Iterator<Book> iterator = owned.iterator();
		while(iterator.hasNext()) {
			if(owned.contains(book)) {
				iterator.remove();
			}
		}
		user.setOwned(owned);
		userRepository.save(user);
	}

	@Override
	public void addBookToRead(User user,Book book) {
		Set<Book> read = userRepository.findReadByUserId(user.getId());
		if(!read.contains(book)) {
			read.add(book);
		}
		user.setRead(read);
		userRepository.save(user);
		
	}

	@Override
	public void removeBookFromRead(User user,Book book) {
		Set<Book> read = userRepository.findReadByUserId(user.getId());
		Iterator<Book> iterator = read.iterator();
		while(iterator.hasNext()) {
			if(read.contains(book)) {
				iterator.remove();
			}
		}
		user.setRead(read);
		userRepository.save(user);
		
	}

	@Override
	public void addBookToWannaRead(User user,Book book) {
		Set<Book> wannaRead = userRepository.findWannaReadByUserId(user.getId());
		if(wannaRead.contains(book)) {
			wannaRead.add(book);
		}
		user.setWannaRead(wannaRead);
		userRepository.save(user);
		
	}

	@Override
	public void removeBookFromWannaRead(User user,Book book) {
		Set<Book> wannaRead = userRepository.findWannaReadByUserId(user.getId());
		Iterator<Book> iterator = wannaRead.iterator();
		while(iterator.hasNext()) {
			if(wannaRead.contains(book)) {
				iterator.remove();
			}
		}
		user.setWannaRead(wannaRead);
		userRepository.save(user);
		
	}

	@Override
	public void addBookToReading(User user,Book book) {
		Set<Book> reading = userRepository.findReadingByUserId(user.getId());
		if(reading.contains(book)) {
			reading.add(book);
		}
		user.setReading(reading);
		userRepository.save(user);
		
	}

	@Override
	public void removeBookFromReading(User user,Book book) {
		Set<Book> reading = userRepository.findReadingByUserId(user.getId());
		Iterator<Book> iterator = reading.iterator();
		while(iterator.hasNext()) {
			if(reading.contains(book)) {
				iterator.remove();
			}
		}
		user.setReading(reading);
		userRepository.save(user);
		
	}

	@Override
	public int getRating(User user,long bookId) {
		Map<Long, Integer> ratings = user.getRating();
		if(ratings.containsKey(bookId)) {
			int rate = ratings.get(bookId);
			return rate;
		}else {
			return 0;
		}

	}

	@Override
	public void addRating(User user,long bookId, int rate) {
		Map<Long, Integer> ratings = new HashMap<>();
		ratings.put(bookId, rate);
		user.setRating(ratings);
		userRepository.save(user);
	}

}
