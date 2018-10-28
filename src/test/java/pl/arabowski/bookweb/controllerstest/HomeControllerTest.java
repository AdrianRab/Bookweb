package pl.arabowski.bookweb.controllerstest;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pl.arabowski.bookweb.controllers.HomeController;
import pl.arabowski.bookweb.model.User;
import pl.arabowski.bookweb.repositories.UserRepository;

class HomeControllerTest {

	private HomeController controller;
	private MockMvc mockMvc;
	private UserRepository mockUserRepository;
	
	@BeforeEach
	void setup() {
		mockUserRepository = mock(UserRepository.class);
		controller = new HomeController(mockUserRepository);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Nested
	@DisplayName("Tests HTTP GET methods for HomeController")
	class homeControllerHTTPGetMethodsTests {

		@Test
		@DisplayName("Should return login view")
		void viewLoginPage() throws Exception {
			mockMvc.perform(get("/login"))
			.andExpect(view().name("loginPage"))
			.andExpect(status().isOk());
		}

		@Test
		@DisplayName("Shoud return register view")
		void viewRegisterPage() throws Exception {
			mockMvc.perform(get("/register"))
			.andExpect(view().name("user/registerForm"))
			.andExpect(status().isOk());
		}

		@Test
		@DisplayName("Shoud return home view")
		void viewHomePage() throws Exception {
			mockMvc.perform(get("/"))
			.andExpect(view().name("home"))
			.andExpect(status().isOk());
		}

	}
	
	@Nested
	@DisplayName("Tests HTTP POST Methods for HomeController")
	class homeControllerHTTPPostMethodsTests{
		//TODO poniższe testy nie działają. Poprawić
		
		@Test
		@DisplayName("Should return home view after succesfull login")
		void viewLoginPage() throws Exception {
			User loggedIn = new User();
			loggedIn.setEmail("test@test");
			loggedIn.setPassword("password1");
			loggedIn.setUsername("Ziomek");
			when(mockUserRepository.findByEmailIgnoreCase("test@test")).thenReturn(loggedIn);
			
			RequestBuilder requestBuilder = formLogin().user("test@test").password("password1");
			mockMvc.perform(requestBuilder)
			.andExpect(model().attributeExists("user"))
			.andExpect(view().name("home"))
			.andExpect(status().isOk());
			
//			mockMvc.perform(post("/login")
//					.param("email",  "test@test")
//					.param("password", "password1"))
//			.andExpect(model().attributeExists("user"))
//			.andExpect(view().name("home"))
//			.andExpect(status().isOk());
		}
		
		@Test
		@DisplayName("Should register new user")
		void shouldRegisterNewUser() throws Exception {
			User unregistered = new User();
			unregistered.setEmail("test@test.pl");
			unregistered.setUsername("JonnyB");
			unregistered.setPassword("password");
			unregistered.setPasswordConfirmed("password");
			User registered = new User();
			registered.setId(1);
			registered.setEmail("test@test.pl");
			registered.setUsername("JonnyB");
			registered.setPassword("password");
			registered.setPasswordConfirmed("password");
			
			when(mockUserRepository.saveAndFlush(unregistered)).thenReturn(registered);
		
			mockMvc.perform(post("/register")
					.param("email", "test@test.pl")
					.param("username", "JonnyB")
					.param("password", "password")
					.param("passwordConfirmed", "password"))
			.andExpect(redirectedUrl("/user/my-page"));
			
			verify(mockUserRepository, atLeastOnce()).saveAndFlush(unregistered);
		}
		
	}
}
