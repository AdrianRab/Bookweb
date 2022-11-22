package pl.arabowski.bookweb.controllers;

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
import pl.arabowski.bookweb.service.book.BookService;
import pl.arabowski.bookweb.service.user.UserService;
import pl.arabowski.bookweb.utils.RedirectUrlResolver;

@Controller
@RequestMapping("/user")
public class UserController {

    public static final String BOOK_DETAILS_REDIRECT = "/book/details/";
    public static final String HOME_REDIRECT = "/home";
    public static final String USER_PROFILE = "user/profile";
    public static final String USER_EDIT = "user/edit";
    public static final String USER_CONFIRMATION = "user/confirmation";
    public static final String USER_OWNED_BOOKS = "user/ownedBooks";
    public static final String USER_READ_BOOKS = "user/readBooks";
    public static final String USER_READING_BOOKS = "user/readingBooks";
    public static final String USER_TO_READ = "user/toRead";
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired RedirectUrlResolver redirectUrlResolver;
    @GetMapping("/owned")
    public ModelAndView userOwnedBooks(@AuthenticationPrincipal UserDetails currentUser) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        Set<Book> owned = user.getOwned();
        mav.addObject("user", user);
        mav.addObject("ownedBooks", owned);
        mav.setViewName(USER_OWNED_BOOKS);
        return mav;
    }

    @GetMapping("/read")
    public ModelAndView userReadBooks(@AuthenticationPrincipal UserDetails currentUser) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        Set<Book> read = user.getRead();
        mav.addObject("user", user);
        mav.addObject("readBooks", read);
        mav.setViewName(USER_READ_BOOKS);
        return mav;
    }

    @GetMapping("/reading")
    public ModelAndView userReadingBooks(@AuthenticationPrincipal UserDetails currentUser) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        Set<Book> reading = user.getReading();
        mav.addObject("user", user);
        mav.addObject("readingBooks", reading);
        mav.setViewName(USER_READING_BOOKS);
        return mav;
    }

    @GetMapping("/to-read")
    public ModelAndView userToReadBooks(@AuthenticationPrincipal UserDetails currentUser) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        Set<Book> wannaRead = user.getWannaRead();
        mav.addObject("user", user);
        mav.addObject("wannaReadBooks", wannaRead);
        mav.setViewName(USER_TO_READ);
        return mav;
    }

    @GetMapping("/edit")
    public ModelAndView editUser(@AuthenticationPrincipal UserDetails currentUser) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        mav.addObject("user", user);
        mav.setViewName(USER_EDIT);
        return mav;
    }

    @PostMapping("/edit")
    public ModelAndView editUser(@Valid User user, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (!result.hasErrors()) {
            userService.save(user);
            mav.addObject("user", user);
            mav.setViewName(USER_PROFILE);
            return mav;
        } else {
            mav.setViewName(USER_EDIT);
            return mav;
        }
    }

    @GetMapping("/delete-account")
    public ModelAndView removeUser(@AuthenticationPrincipal UserDetails currentUser) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        mav.addObject("user", user);
        mav.setViewName(USER_CONFIRMATION);
        return mav;
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
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        mav.addObject("readingBooks", user.getReading());
        mav.addObject("user", user);
        mav.setViewName(USER_PROFILE);
        return mav;
    }

    @GetMapping("/add-to-owned/{bookId}")
    public ModelAndView addToOwnedBooks(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        Book book = bookService.getBook(bookId);
        userService.addBookToOwned(user, book);
        mav.addObject("user", user);
        mav.addObject("book", book);
        mav.setViewName(redirectUrlResolver.getRedirectView(BOOK_DETAILS_REDIRECT + book.getId()));
        return mav;
    }

    @GetMapping("/remove-from-owned/{bookId}")
    public ModelAndView removeFromOwnedBooks(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        Book book = bookService.getBook(bookId);
        userService.removeBookFromOwned(user, book);
        mav.addObject("user", user);
        mav.addObject("book", book);
        mav.setViewName(redirectUrlResolver.getRedirectView(BOOK_DETAILS_REDIRECT + book.getId()));
        return mav;
    }

    @GetMapping("/add-to-reading/{bookId}")
    public ModelAndView addToReadingBooks(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        Book book = bookService.getBook(bookId);
        userService.addBookToReading(user, book);
        mav.addObject("user", user);
        mav.addObject("book", book);
        mav.setViewName(redirectUrlResolver.getRedirectView(BOOK_DETAILS_REDIRECT + book.getId()));
        return mav;
    }

    @GetMapping("/remove-from-reading/{bookId}")
    public ModelAndView removeFromReadingBooks(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        Book book = bookService.getBook(bookId);
        userService.removeBookFromReading(user, book);
        mav.addObject("user", user);
        mav.addObject("book", book);
        mav.setViewName(redirectUrlResolver.getRedirectView(BOOK_DETAILS_REDIRECT + book.getId()));
        return mav;
    }

    @GetMapping("/add-read/{bookId}")
    public ModelAndView addToRead(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        Book book = bookService.getBook(bookId);
        userService.addBookToRead(user, book);
        mav.addObject("user", user);
        mav.addObject("book", book);
        mav.setViewName(redirectUrlResolver.getRedirectView(BOOK_DETAILS_REDIRECT + book.getId()));
        return mav;
    }

    @GetMapping("/remove-from-read/{bookId}")
    public ModelAndView removeFromRead(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        Book book = bookService.getBook(bookId);
        userService.removeBookFromRead(user, book);
        mav.addObject("user", user);
        mav.addObject("book", book);
        mav.setViewName(redirectUrlResolver.getRedirectView(BOOK_DETAILS_REDIRECT + book.getId()));
        return mav;
    }

    @GetMapping("/add-to-read/{bookId}")
    public ModelAndView addBookToRead(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        Book book = bookService.getBook(bookId);
        userService.addBookToWannaRead(user, book);
        mav.addObject("user", user);
        mav.addObject("book", book);
        mav.setViewName(redirectUrlResolver.getRedirectView(BOOK_DETAILS_REDIRECT + book.getId()));
        return mav;
    }

    @GetMapping("/remove-from-to-read/{bookId}")
    public ModelAndView removeFromToRead(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(currentUser);
        Book book = bookService.getBook(bookId);
        userService.removeBookFromWannaRead(user, book);
        mav.addObject("user", user);
        mav.addObject("book", book);
        mav.setViewName(redirectUrlResolver.getRedirectView(BOOK_DETAILS_REDIRECT + book.getId()));
        return mav;
    }

}
