package pl.arabowski.bookweb.services;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.model.UserDto;

public interface UserService {

    Optional<User> getUser(UserDetails userDetails);

    User save(User user);

    void delete(long id);

    ModelAndView register(UserDto user, BindingResult result);
}
