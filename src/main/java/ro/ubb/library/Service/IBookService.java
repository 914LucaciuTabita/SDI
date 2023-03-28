package ro.ubb.library.Service;

import ro.ubb.library.DTO.BookDTO;
import ro.ubb.library.Model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getAllBooks();

    Optional<Book> getBook(Long id);

    Boolean addBook(Book book);
    void addBook(String title, String genre, String type, String description, Integer pages);

    Boolean deleteBook(Long id);
    Boolean updateBook(Long id, String title, String genre, String type, String description, Integer pages);

    Optional<Book> updateBook(Book book);

    List<Book> findByAuthorId(Long id);

    List<Book> findBooksByPagesGreaterThan(Integer pages);

    List<Book> findBooksSortedByAuthorNumberOfBooks();
    }
