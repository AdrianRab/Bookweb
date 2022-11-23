package pl.arabowski.bookweb.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.model.UserDto;
import pl.arabowski.bookweb.model.UserRole;
import pl.arabowski.bookweb.model.enums.UserRoles;
import pl.arabowski.bookweb.repositories.UserRepository;
import pl.arabowski.bookweb.utils.RedirectUrlResolver;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    public static final String USER_REGISTER_FORM = "user/registerForm";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String USER_MY_PAGE = "user/my-page";
    private final UserRepository userRepository;
    private final RedirectUrlResolver redirectUrlResolver;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RedirectUrlResolver redirectUrlResolver) {
        this.userRepository = userRepository;
        this.redirectUrlResolver = redirectUrlResolver;
    }

    @Override
    public User getUser(UserDetails userDetails) {
        String username = userDetails.getUsername();
        log.info("Retrieving user with e-mail {}", username );
        return userRepository.findByEmailIgnoreCase(username);
    }

    @Override
    public User save(User user) {
        log.info("Updating user {}", user);
        return userRepository.saveAndFlush(user);
    }

    public ModelAndView register(UserDto user, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (isAnyParameterInvalid(mav, user.getPassword(), user.getPasswordConfirmed(), user)) {
            return mav;
        }
        if (!result.hasErrors()) {
            processRegistration(user, mav);
        } else {
            mav.setViewName(USER_REGISTER_FORM);
        }
        return mav;
    }

    private void processRegistration(UserDto userDto, ModelAndView mav) {
        User user = User.fromDto(userDto);
        UserRole userRole = UserRole.builder().user(user).role(String.valueOf(UserRoles.ROLE_USER)).build();
        user.setEnabled(true);
        user.setRole(userRole);
        userRepository.saveAndFlush(user);
        log.info("Registered user {} with role {}", user, userRole);
        mav.addObject("user", user);
        mav.setViewName(redirectUrlResolver.getRedirectView("/" + USER_MY_PAGE));
    }

    private boolean isAnyParameterInvalid(ModelAndView mav, String password, String passwordConfirmed, UserDto user) {
        if (passwordsDonNotMatch(password, passwordConfirmed)) {
            log.error("Provided passwords do not match.");
            mav.addObject(ERROR_MESSAGE, "Provided passwords do not match. Please try again.");
            mav.setViewName(USER_REGISTER_FORM);
            return true;
        }
        if (isEmailRegistered(user)) {
            log.error("Provided e-mail {} is already in use.", user.getEmail());
            mav.addObject(ERROR_MESSAGE, String.format("Provided e-mail %s is already in use.", user.getEmail()));
            mav.setViewName(USER_REGISTER_FORM);
            return true;
        }
        if (isUsernameRegistered(user)) {
            log.error("Username {} is already in use.", user.getUsername());
            mav.addObject(ERROR_MESSAGE, String.format("Username %s is already in use.", user.getUsername()));
            mav.setViewName(USER_REGISTER_FORM);
            return true;
        }
        return false;
    }

    private boolean isUsernameRegistered(UserDto user) {
        return userRepository.findFirstByUsername(user.getUsername()) != null;
    }

    private boolean isEmailRegistered(UserDto user) {
        return userRepository.findByEmailIgnoreCase(user.getEmail()) != null;
    }

    private boolean passwordsDonNotMatch(String password, String passwordConfirmed) {
        return !(password.equals(passwordConfirmed));
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
        log.info("Successfully deleted User with id {}", id);
    }
}
