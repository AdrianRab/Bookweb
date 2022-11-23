package pl.arabowski.bookweb.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.services.publisher.PublisherService;
import pl.arabowski.bookweb.utils.RedirectUrlResolver;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

    public static final String PUBLISHER_ADD_NEW = "publisher/addNew";
    public static final String PUBLISHER_ALL = "publisher/all";
    @Autowired
    private PublisherService publisherService;

    @Autowired RedirectUrlResolver redirectUrlResolver;

    @GetMapping("/add")
    public ModelAndView addPublisher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("publisher", new Publisher());
        mav.setViewName(PUBLISHER_ADD_NEW);
        return mav;
    }

    @PostMapping("/add")
    public ModelAndView addPublisher(@Valid Publisher publisher, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (!result.hasErrors()) {
            publisherService.save(publisher);
            mav.addObject("publisher", publisher);
            mav.setViewName(redirectUrlResolver.getRedirectView("/" + PUBLISHER_ALL));
            return mav;
        } else {
            mav.setViewName(PUBLISHER_ADD_NEW);
            return mav;
        }
    }

    @GetMapping("/all")
    public ModelAndView allPublishers() {
        ModelAndView mav = new ModelAndView();
        List<Publisher> publishers = (List<Publisher>) publisherService.listAllPublishers();
        mav.addObject("publishersList", publishers);
        mav.setViewName(PUBLISHER_ALL);
        return mav;
    }

    //add page for one publisher with list of its books
}
