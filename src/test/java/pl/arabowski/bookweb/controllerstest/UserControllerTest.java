package pl.arabowski.bookweb.controllerstest;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import pl.arabowski.bookweb.controllers.UserController;
import pl.arabowski.bookweb.model.Book;
import pl.arabowski.bookweb.model.Publisher;
import pl.arabowski.bookweb.repositories.UserRepository;

class UserControllerTest {

//  testy nie przechodzą, sprawdzić i poprawić	
	//TODO
	
	private MockMvc mockMvc;
	private UserRepository mockUserRepository;
	private Set<Book> books;
	private UserController controller;
	
	@Nested
	@DisplayName("Tests for user HTTTP GET methods")
	class userHTTPGetMethodsTests {

		@BeforeEach
		void setup() {
			controller = new UserController(mockUserRepository);
			mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
			mockUserRepository = mock(UserRepository.class);
			books = getBooksSet();
			
		}

		@Test
		@DisplayName("Should return ownedBooks view")
		@Disabled
		void viewOwnedBooks() throws Exception {
			when(mockUserRepository.findOwnedByUserId(1)).thenReturn(books);
			mockMvc = MockMvcBuilders.standaloneSetup(controller).setSingleView(new InternalResourceView("/WEB-INF/jsp/user/ownedBooks.jsp")).build();
			mockMvc.perform(get("/owned")).andExpect(status().isOk());
		}

		@Test
		@DisplayName("Shoud return readBooks view")
		@Disabled
		void viewReadBoks() throws Exception {
			when(mockUserRepository.findReadByUserId(1)).thenReturn(books);
			mockMvc = MockMvcBuilders.standaloneSetup(controller).setSingleView(new InternalResourceView("/WEB-INF/jsp/user/readBooks.jsp")).build();
			
			mockMvc.perform(get("/read"))
			.andExpect(view().name("user/readBooks"))
			.andExpect(model().attributeExists("readBooks"))
			.andExpect(model().attribute("readBooks", hasItems(books.toArray())));
		}

	}
	
	private Set<Book> getBooksSet(){
		Set<Book> books = new HashSet<>();
		Book book1 = new Book();
		Book book2 = new Book();
		Publisher publisher1 = new Publisher();
		Publisher publisher2 = new Publisher();
		
		publisher1.setId(1);
		publisher2.setId(2);
		publisher1.setName("Bellona");
		publisher2.setName("Helion");

		
		book1.setId(1);
		book2.setId(2);
		book1.setPublisher(publisher1);
		book2.setPublisher(publisher2);
		book1.setTitle("Magnezja 190 p.n.e.");
		book2.setTitle("Czysty kod");
		
		books.add(book1);
		books.add(book2);
		
		return books;
	}

}
