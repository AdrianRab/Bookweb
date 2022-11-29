package pl.arabowski.bookweb.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.repositories.PublisherRepository;
import pl.arabowski.bookweb.utils.RedirectUrlResolver;

@ExtendWith(MockitoExtension.class)
class PublisherServiceImplTest {

    public static final long SOME_ID = 1L;
    public static final String SOME_NAME = "Helion";
    public static final Publisher SOME_PUBLISHER = Publisher.builder().id(SOME_ID).name(SOME_NAME).build();
    public static final String REDIRECT_URL_BASE = "redirect:http://localhost:8090";
    @Mock
    private PublisherRepository repository;
    @Mock
    private RedirectUrlResolver urlResolver;
    private PublisherService cut;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cut = new PublisherServiceImpl(repository, urlResolver);
    }

    @Test
    void shouldGetPublisherById() {
        //given
        //when
        when(repository.findById(SOME_ID)).thenReturn(Optional.of(SOME_PUBLISHER));
        Publisher result = cut.findById(SOME_ID);

        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(SOME_PUBLISHER);
        verify(repository, times(1)).findById(SOME_ID);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldThrowExceptionWhenNoPublisherWithGivenId() {
        //given
        String msg = String.format("No publisher with id %s exists!", SOME_ID);

        //when
        when(repository.findById(SOME_ID)).thenReturn(Optional.empty());

        //then
        assertThatExceptionOfType(EmptyResultDataAccessException.class)
                .isThrownBy(() -> cut.findById(SOME_ID)).withMessage(msg);
        verify(repository, times(1)).findById(SOME_ID);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldFindPublisherByName() {
        //given
        //when
        when(repository.findByName(SOME_NAME)).thenReturn(Optional.of(SOME_PUBLISHER));
        Publisher result = cut.findByName(SOME_NAME);

        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(SOME_PUBLISHER);
        verify(repository, times(1)).findByName(SOME_NAME);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldThrowExceptionWhenNoPublisherWithGivenName() {
        //given
        String msg = String.format("No publisher with name %s exists!", SOME_NAME);

        //when
        when(repository.findByName(SOME_NAME)).thenReturn(Optional.empty());

        //then
        assertThatExceptionOfType(EmptyResultDataAccessException.class)
                .isThrownBy(() -> cut.findByName(SOME_NAME)).withMessage(msg);
        verify(repository, times(1)).findByName(SOME_NAME);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldAddNewPublisher() {
        //given
        String viewName = "/publisher/all";
        String expectedViewName = REDIRECT_URL_BASE + viewName;
        SOME_PUBLISHER.setId(null);

        //when
        when(repository.saveAndFlush(SOME_PUBLISHER)).thenReturn(SOME_PUBLISHER);
        when(urlResolver.getRedirectView(viewName)).thenReturn(expectedViewName);
        ModelAndView result = cut.addPublisher(SOME_NAME);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getViewName()).isEqualTo(expectedViewName);
        assertThat(result.getModel()).containsEntry("publisher", SOME_PUBLISHER);
        verify(repository, times(1)).saveAndFlush(SOME_PUBLISHER);
        verifyNoMoreInteractions(repository);
        verify(urlResolver, times(1)).getRedirectView(viewName);
        verifyNoMoreInteractions(urlResolver);
    }

    @Test
    void shouldNotAddNewPublisherWhenFormHasErrors() {
        //given
        String viewName = "publisher/addNew";
        String expectedErrorMsg = "Invalid name. Publisher name should be at least two characters long.";
        String someInvalidName = "a";

        //when
        ModelAndView result = cut.addPublisher(someInvalidName);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getViewName()).isEqualTo(viewName);
        assertThat(result.getModel()).containsEntry("errorMessage", expectedErrorMsg);
        verifyNoInteractions(repository);
        verifyNoInteractions(urlResolver);
    }

}
