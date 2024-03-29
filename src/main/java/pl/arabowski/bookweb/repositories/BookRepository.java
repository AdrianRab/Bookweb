package pl.arabowski.bookweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.arabowski.bookweb.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select a.books from Author a where a.lastName = ?1")
    List<Book> findAllBooksByAuthorLastNameOrderByTitleAsc(String lastName);

    List<Book> findByTitleOrderByTitleAsc(String title);

    List<Book> findTop20ByOrderByRateDesc();

    @Query("select b from Book b where lower(b.title) like lower(concat('%', :searchWord,'%'))")
    List<Book> findBooksWhichContainsWordInTitle(@Param("searchWord") String searchWord);

    List<Book> findAllByGenre(String genre);
}
