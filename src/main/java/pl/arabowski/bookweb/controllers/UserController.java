package pl.arabowski.bookweb.controllers;

import java.util.AbstractMap;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.services.UserService;
import pl.arabowski.bookweb.utils.RedirectUrlResolver;

@Controller
@RequestMapping("/user")
public class UserController {

    public static final String HOME_REDIRECT = "/home";
    public static final String USER_PROFILE = "user/profile";
    public static final String USER_EDIT = "user/edit";
    public static final String USER_CONFIRMATION = "user/confirmation";

    @Autowired
    private UserService userService;

    @Autowired
    RedirectUrlResolver redirectUrlResolver;

    @GetMapping("/edit")
    public ModelAndView editUser(@AuthenticationPrincipal UserDetails currentUser) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        return getModelAndView(user, Optional.empty(), USER_EDIT);
    }

    @PostMapping("/edit")
    public ModelAndView editUser(@Valid User user, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (!result.hasErrors()) {
            userService.save(user);
            return getModelAndView(user, Optional.empty(), USER_PROFILE);
        } else {
            mav.setViewName(USER_EDIT);
            return mav;
        }
    }

    @GetMapping("/delete-account")
    public ModelAndView removeUser(@AuthenticationPrincipal UserDetails currentUser) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        return getModelAndView(user, Optional.empty(), USER_CONFIRMATION);
    }

    @GetMapping("/confirmation/{id}")
    public ModelAndView removeUserConfirmation(@PathVariable long id) {
        ModelAndView mav = new ModelAndView();
        userService.delete(id);
        mav.setViewName(redirectUrlResolver.getRedirectView(HOME_REDIRECT));
        return mav;
    }

    @GetMapping("/my-page")
    public ModelAndView userProfile(@AuthenticationPrincipal UserDetails currentUser) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        AbstractMap.SimpleEntry<String, Set<Book>> attributes
                = new AbstractMap.SimpleEntry<>("readingBooks", user.getReading());
        return getModelAndView(user, Optional.of(attributes), USER_PROFILE);
    }

    private ModelAndView redirectToLoginPage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "You have to be logged in to view this page");
        mav.setViewName("loginPage");
        return mav;
    }

    private ModelAndView getModelAndView(User user, Optional<AbstractMap.SimpleEntry<String, Set<Book>>> additionalAttributes, String viewName) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", user);
        if (additionalAttributes.isPresent()) {
            AbstractMap.SimpleEntry<String, Set<Book>> attributes = additionalAttributes.get();
            mav.addObject(attributes.getKey(), attributes.getValue());
        }
        mav.setViewName(viewName);
        return mav;
    }
}
