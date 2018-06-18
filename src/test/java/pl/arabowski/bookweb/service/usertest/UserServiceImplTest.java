package pl.arabowski.bookweb.service.usertest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.service.user.UserServiceImpl;

public class UserServiceImplTest {
	
	UserServiceImpl userService = new UserServiceImpl();
	
	@Test
	public void shouldShowRating() {
		//given
		User user = new User();
		Map<Long, Double> rating = new HashMap<>();
		Long one = (long) 1;
		Long two =(long) 2;
		Long three =(long) 3;
		rating.put(one, 6.0);
		rating.put(two, 7.0);
		rating.put(three, 4.0);
		user.setRating(rating);
		//when
		double result = userService.getRating(user, two);
		//then
		assertEquals(7.0, result, 0.1);
	}
	
	@Test
	public void shouldNotShowRating() {
		//given
		User user = new User();
		Long two =(long) 2;
		Map<Long, Double> rating = new HashMap<>();
		user.setRating(rating);
		//when
		double result = userService.getRating(user, two);
		//then 
		assertEquals(0.0, result, 0.1);
	}

}
