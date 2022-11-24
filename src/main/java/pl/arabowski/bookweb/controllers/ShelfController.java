package pl.arabowski.bookweb.controllers;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.services.ShelfService;
import pl.arabowski.bookweb.services.UserService;
import pl.arabowski.bookweb.services.book.BookService;
import pl.arabowski.bookweb.utils.RedirectUrlResolver;

@Controller
@RequestMapping("/shelf/{userId}")
public class ShelfController {
    public static final String USER_OWNED_BOOKS = "user/ownedBooks";
    public static final String USER_READ_BOOKS = "user/readBooks";
    public static final String USER_READING_BOOKS = "user/readingBooks";
    public static final String USER_TO_READ = "user/toRead";
    public static final String USER = "user";
    @Autowired
    private UserService userService;

    @Autowired
    private ShelfService shelfService;

    @Autowired
    private BookService bookService;

    @Autowired
    RedirectUrlResolver redirectUrlResolver;

    @GetMapping("/owned")
    public ModelAndView userOwnedBooks(@AuthenticationPrincipal UserDetails currentUser) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        Set<Book> owned = user.getOwned();
        return getModelAndView(user, "ownedBooks", owned, USER_OWNED_BOOKS);
    }

    @GetMapping("/read")
    public ModelAndView userReadBooks(@AuthenticationPrincipal UserDetails currentUser) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        Set<Book> read = user.getRead();
        return getModelAndView(userOptional.get(), "readBooks", read, USER_READ_BOOKS);
    }

    @GetMapping("/reading")
    public ModelAndView userReadingBooks(@AuthenticationPrincipal UserDetails currentUser) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        Set<Book> reading = user.getReading();
        return getModelAndView(user, "readingBooks", reading, USER_READING_BOOKS);
    }

    @GetMapping("/to-read")
    public ModelAndView userToReadBooks(@AuthenticationPrincipal UserDetails currentUser) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        Set<Book> wannaRead = user.getWannaRead();
        return getModelAndView(user, "wannaReadBooks", wannaRead, USER_TO_READ);
    }

    @GetMapping("/add-to-owned/{bookId}")
    public ModelAndView addToOwnedBooks(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        Book book = bookService.getBook(bookId);
        shelfService.addBookToOwned(user, book);
        return redirectToBookDetailsView(user, book);
    }

    @GetMapping("/remove-from-owned/{bookId}")
    public ModelAndView removeFromOwnedBooks(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        Book book = bookService.getBook(bookId);
        shelfService.removeBookFromOwned(user, book);
        return redirectToBookDetailsView(user, book);
    }

    @GetMapping("/add-to-reading/{bookId}")
    public ModelAndView addToReadingBooks(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        Book book = bookService.getBook(bookId);
        shelfService.addBookToReading(user, book);
        return redirectToBookDetailsView(user, book);
    }

    @GetMapping("/remove-from-reading/{bookId}")
    public ModelAndView removeFromReadingBooks(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        Book book = bookService.getBook(bookId);
        shelfService.removeBookFromReading(user, book);
        return redirectToBookDetailsView(user, book);
    }

    @GetMapping("/add-read/{bookId}")
    public ModelAndView addToRead(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        Book book = bookService.getBook(bookId);
        shelfService.addBookToRead(user, book);
        return redirectToBookDetailsView(user, book);
    }

    @GetMapping("/remove-from-read/{bookId}")
    public ModelAndView removeFromRead(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        Book book = bookService.getBook(bookId);
        shelfService.removeBookFromRead(user, book);
        return redirectToBookDetailsView(user, book);
    }

    @GetMapping("/add-to-read/{bookId}")
    public ModelAndView addBookToRead(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        Book book = bookService.getBook(bookId);
        shelfService.addBookToWannaRead(user, book);
        return redirectToBookDetailsView(user, book);
    }

    @GetMapping("/remove-from-to-read/{bookId}")
    public ModelAndView removeFromToRead(@AuthenticationPrincipal UserDetails currentUser, @PathVariable long bookId) {
        Optional<User> userOptional = userService.getUser(currentUser);
        if (userOptional.isEmpty()) {
            return redirectToLoginPage();
        }
        User user = userOptional.get();
        Book book = bookService.getBook(bookId);
        shelfService.removeBookFromWannaRead(user, book);
        return redirectToBookDetailsView(user, book);
    }

    private ModelAndView getModelAndView(User user, String attributeName, Set<Book> attributeValue, String viewName) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(USER, user);
        mav.addObject(attributeName, attributeValue);
        mav.setViewName(viewName);
        return mav;
    }

    private ModelAndView redirectToLoginPage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "You have to be logged in to view this page");
        mav.setViewName("loginPage");
        return mav;
    }

    private ModelAndView redirectToBookDetailsView(User user, Book book) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(USER, user);
        mav.addObject("book", book);
        mav.setViewName(redirectUrlResolver.getRedirectView("/book/details/" + book.getId()));
        return mav;
    }
}
