package pl.arabowski.bookweb.services;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.repositories.PublisherRepository;
import pl.arabowski.bookweb.utils.RedirectUrlResolver;

@Service
@Slf4j
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final RedirectUrlResolver redirectUrlResolver;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository, RedirectUrlResolver redirectUrlResolver) {
        this.publisherRepository = publisherRepository;
        this.redirectUrlResolver = redirectUrlResolver;
    }

    @Override
    public Publisher findByName(String name) {
        return publisherRepository.findByName(name)
                .orElseThrow(() -> new EmptyResultDataAccessException(String.format("No publisher with name %s exists!", name), 1));
    }

    @Override
    public List<Publisher> listAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher findById(long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(String.format("No publisher with id %s exists!", id), 1));
    }

    @Override
    public ModelAndView addPublisher(String name ) {
        ModelAndView mav = new ModelAndView();

        if (isPublisherNameInvalid(name)) {
            log.error("Invalid publisher name: {}", name);
            mav.setViewName("publisher/addNew");
            mav.addObject("errorMessage", "Invalid name. Publisher name should be at least two characters long.");
            return mav;
        }

        Publisher publisherToPersist = Publisher.builder().name(name).build();
        Publisher persistedPublisher = publisherRepository.saveAndFlush(publisherToPersist);
        log.info("Successfully added {}", persistedPublisher);
        mav.addObject("publisher", persistedPublisher);
        mav.setViewName(redirectUrlResolver.getRedirectView("/publisher/all"));
        return mav;
    }

    private boolean isPublisherNameInvalid(String name) {
        return name.isBlank() || name.length() < 2;
    }

    @Override
    public ModelAndView editPublisher(Publisher publisher, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            mav.setViewName("/admin/editPublisher");
            return mav;
        }
        log.info("Saving changes in publisher {}", publisher);
        publisherRepository.saveAndFlush(publisher);
        mav.setViewName(redirectUrlResolver.getRedirectView("/publisher/details/" + publisher.getId()));
        return mav;
    }

    @Override
    public ModelAndView deletePublisher(long id) {
        publisherRepository.deleteById(id);
        log.info("Successfully deleted publisher with id {}", id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(redirectUrlResolver.getRedirectView("/admin/panel"));
        return mav;
    }

}
