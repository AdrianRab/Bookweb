package pl.arabowski.bookweb.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.services.PublisherService;
import pl.arabowski.bookweb.utils.RedirectUrlResolver;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

    public static final String PUBLISHER_ADD_NEW = "publisher/addNew";
    public static final String PUBLISHER_ALL = "publisher/all";
    private final PublisherService publisherService;

    RedirectUrlResolver redirectUrlResolver;

    @Autowired
    public PublisherController(PublisherService publisherService, RedirectUrlResolver redirectUrlResolver) {
        this.publisherService = publisherService;
        this.redirectUrlResolver = redirectUrlResolver;
    }

    @GetMapping("/add")
    public ModelAndView addPublisher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("publisher", new Publisher());
        mav.setViewName(PUBLISHER_ADD_NEW);
        return mav;
    }

    @PostMapping("/add")
    public ModelAndView addPublisher(@Valid Publisher publisher) {
        return publisherService.addPublisher(publisher.getName());
    }

    @GetMapping("/all")
    public ModelAndView allPublishers() {
        ModelAndView mav = new ModelAndView();
        List<Publisher> publishers = publisherService.listAllPublishers();
        mav.addObject("publishersList", publishers);
        mav.setViewName(PUBLISHER_ALL);
        return mav;
    }

    //add page for one publisher with list of its books
}
