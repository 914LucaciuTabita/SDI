package ro.ubb.library.Service;

import ro.ubb.library.Model.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();

    Boolean addBook(Book book);
    void addBook(String title, String author, String genre, String type, String description);

    Boolean deleteBook(Long id);
    Boolean updateBook(Long id, String title, String author, String genre, String type, String description);

    Boolean updateBook(Book book);
    }
