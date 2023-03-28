package ro.ubb.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.library.Model.Book;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByAuthorId(Long authorId);
    List<Book> findBooksByPagesGreaterThan(Integer pages);

    @Query("select b from Book b inner join Author a on b.author = a order by size(a.books) desc")
    List<Book> findBooksSortedByAuthorNumOfBooks();
}
