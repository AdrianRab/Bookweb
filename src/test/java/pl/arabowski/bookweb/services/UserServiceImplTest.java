package pl.arabowski.bookweb.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.model.UserDto;
import pl.arabowski.bookweb.repositories.UserRepository;
import pl.arabowski.bookweb.utils.RedirectUrlResolver;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    public static final String SOME_EMAIL = "some@email.com";
    public static final long SOME_ID = 1;
    public static final String SOME_USERNAME = "someUsername";
    public static final String SOME_PASSWORD = "somePassword";
    public static final String SOME_OTHER_PASSWORD = "other_password";
    public static final String ERROR_MESSAGE = "errorMessage";
    @Mock
    private UserRepository repository;
    @Mock
    private UserDetails someUserDetails;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private RedirectUrlResolver redirectUrlResolver;
    private UserService cut;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
        cut = new UserServiceImpl(repository, redirectUrlResolver);
    }

    @Test
    @DisplayName("Should return registered user by e-mail")
    void shouldReturnUserIfRegistered() {
        //given
        User expected = User.builder().id(SOME_ID).enabled(true).email(SOME_EMAIL).username(SOME_USERNAME).password(SOME_PASSWORD).build();

        //when
        when(someUserDetails.getUsername()).thenReturn(SOME_EMAIL);
        when(repository.findByEmailIgnoreCase(SOME_EMAIL)).thenReturn(expected);
        User result = cut.getUser(someUserDetails).orElseThrow(RuntimeException::new);

        //then
        verify(someUserDetails, times(1)).getUsername();
        verifyNoMoreInteractions(someUserDetails);
        verify(repository, times(1)).findByEmailIgnoreCase(SOME_EMAIL);
        verifyNoMoreInteractions(repository);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(SOME_ID);
        assertThat(result.getUsername()).isEqualTo(SOME_USERNAME);
        assertThat(result.getPassword()).isEqualTo(SOME_PASSWORD);
        assertThat(result.getEmail()).isEqualTo(SOME_EMAIL);
    }

    @Test
    @DisplayName("Should return empty Optional if UserDetails is null")
    void shouldReturnEmptyOptionalWhenUserDetailsIsEmpty() {
        //given
        UserDetails userDetails = null;

        //when
        Optional<User> result = cut.getUser(someUserDetails);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should return error message and registerForm view when passwords do not match")
    void shouldNotRegisterUserWhenPasswordsDoNotMatch() {
        //given
        UserDto someUser = UserDto.builder().email(SOME_EMAIL).username(SOME_USERNAME).password(SOME_PASSWORD).passwordConfirmed(SOME_OTHER_PASSWORD).build();
        String expectedViewName = "user/registerForm";
        String expectedErrorMsg = "Provided passwords do not match. Please try again.";

        //when
        ModelAndView result = cut.register(someUser, bindingResult);

        //then
        assertThat(result.getViewName()).isEqualTo(expectedViewName);
        assertThat(result.getModel()).containsEntry(ERROR_MESSAGE, expectedErrorMsg);
        verifyNoInteractions(repository);
    }

    @Test
    @DisplayName("Should return error message and registerForm view when e-mail already registered")
    void shouldNotRegisterUserWhenEmailAlreadyInUse() {
        //given
        UserDto someUser = UserDto.builder().email(SOME_EMAIL).username(SOME_USERNAME).password(SOME_PASSWORD).passwordConfirmed(SOME_PASSWORD).build();
        String expectedViewName = "user/registerForm";
        String expectedErrorMsg = String.format("Provided e-mail %s is already in use.", someUser.getEmail());

        //when
        when(repository.findByEmailIgnoreCase(SOME_EMAIL)).thenReturn(User.builder().email(SOME_EMAIL).build());
        ModelAndView result = cut.register(someUser, bindingResult);

        //then
        verify(repository, times(1)).findByEmailIgnoreCase(SOME_EMAIL);
        verifyNoMoreInteractions(repository);
        assertThat(result.getViewName()).isEqualTo(expectedViewName);
        assertThat(result.getModel()).containsEntry(ERROR_MESSAGE, expectedErrorMsg);
    }

    @Test
    @DisplayName("Should return error message and registerForm view when username already registered")
    void shouldNotRegisterUserWhenUsernameAlreadyInUse() {
        //given
        UserDto someUser = UserDto.builder().email(SOME_EMAIL).username(SOME_USERNAME).password(SOME_PASSWORD).passwordConfirmed(SOME_PASSWORD).build();
        String expectedViewName = "user/registerForm";
        String expectedErrorMsg = String.format("Username %s is already in use.", someUser.getUsername());

        //when
        when(repository.findByEmailIgnoreCase(SOME_EMAIL)).thenReturn(null);
        when(repository.findFirstByUsername(SOME_USERNAME)).thenReturn(User.builder().username(SOME_USERNAME).build());
        ModelAndView result = cut.register(someUser, bindingResult);

        //then
        verify(repository, times(1)).findByEmailIgnoreCase(SOME_EMAIL);
        verify(repository, times(1)).findFirstByUsername(SOME_USERNAME);
        verifyNoMoreInteractions(repository);
        assertThat(result.getViewName()).isEqualTo(expectedViewName);
        assertThat(result.getModel()).containsEntry(ERROR_MESSAGE, expectedErrorMsg);
    }

    @Test
    @DisplayName("Should register new user")
    void shouldRegisterNewUser() {
        UserDto someUser = UserDto.builder().email(SOME_EMAIL).username(SOME_USERNAME).password(SOME_PASSWORD).passwordConfirmed(SOME_PASSWORD).build();
        String viewName = "/user/my-page";
        String expectedViewName = String.format("redirect:http://localhost:8090%s", viewName);
        String expectedResultKey = "user";

        //when
        when(repository.findByEmailIgnoreCase(SOME_EMAIL)).thenReturn(null);
        when(repository.findFirstByUsername(SOME_USERNAME)).thenReturn(null);
        when(repository.saveAndFlush(any())).thenReturn(null);
        when(redirectUrlResolver.getRedirectView(viewName)).thenReturn(expectedViewName);
        ModelAndView result = cut.register(someUser, bindingResult);

        //then
        verify(repository, times(1)).findByEmailIgnoreCase(SOME_EMAIL);
        verify(repository, times(1)).findFirstByUsername(SOME_USERNAME);
        verify(repository, times(1)).saveAndFlush(any());
        verify(redirectUrlResolver, times(1)).getRedirectView(viewName);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(redirectUrlResolver);
        assertThat(result.getViewName()).isEqualTo(expectedViewName);
        assertThat(result.getModel()).containsKey(expectedResultKey);
        User registeredUser = (User) result.getModel().get(expectedResultKey);
        assertThat(registeredUser.getEmail()).isEqualTo(SOME_EMAIL);
        assertThat(registeredUser.getUsername()).isEqualTo(SOME_USERNAME);
        assertThat(new BCryptPasswordEncoder().matches(SOME_PASSWORD, registeredUser.getPassword())).isTrue();
        assertThat(registeredUser.isEnabled()).isTrue();
    }

    @Test
    @DisplayName("Should edit user")
    void shouldEditUser() {
        //given
        User someUser = User.builder()
                .username(SOME_USERNAME)
                .email(SOME_EMAIL)
                .password(new BCryptPasswordEncoder().encode(SOME_PASSWORD))
                .enabled(true)
                .build();

        //when
        when(repository.saveAndFlush(someUser)).thenReturn(someUser);
        User result = cut.save(someUser);

        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(someUser);
        verify(repository, times(1)).saveAndFlush(someUser);
        verifyNoMoreInteractions(repository);
    }


    @Test
    @DisplayName("Should delete user by id")
    void shouldDeleteUser() {
        //given

        //when
        doNothing().when(repository).deleteById((long) SOME_ID);
        cut.delete(SOME_ID);

        //then
        verify(repository, times(1)).deleteById(SOME_ID);
        verifyNoMoreInteractions(repository);
    }

}
