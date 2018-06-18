package pl.arabowski.Bookweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.arabowski.bookweb.service.booktest.BookServiceImplTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BookServiceImplTest.class})
public class BookwebApplicationTests {

	@Test
	public void contextLoads() {
	}

}
