package ro.ubb.library.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.ubb.library.DTO.BookDTO;
import ro.ubb.library.DTO.BorrowingDTO;
import ro.ubb.library.Model.Book;
import ro.ubb.library.Repository.IBookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService{

    @Autowired
    private IBookRepository repository;

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Optional<Book> getBook(Long id) {
        return repository.findById(id);
    }

    @Override
    public Boolean addBook(Book book) {
        if (book.getId() != null && repository.existsById(book.getId())) {
            return false;
        }
        repository.save(book);
        return true;
    }

    public void addBook(String title, String genre, String type, String description, Integer pages) {
        //Book book = Book.builder().title(title).genre(genre).type(type).description(description).pages(pages).build();
        Book book = new Book(title, genre, type, description, pages);
        repository.save(book);
    }

    public Boolean deleteBook(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Transactional
    public Boolean updateBook(Long id, String title, String genre, String type, String description, Integer pages) {
        Optional<Book> bookOpt = repository.findById(id);
        if (bookOpt.isEmpty()) {
            return false;
        }
        Book book = bookOpt.get();
        book.setTitle(title);
        book.setGenre(genre);
        book.setType(type);
        book.setDescription(description);
        book.setPages(pages);
        return true;
    }

    @Override
    public Optional<Book> updateBook(Book book) {
        Optional<Book> oldBook = repository.findById(book.getId());
        if (oldBook.isEmpty()) {
            return oldBook;
        }
        Book oldBook1 = oldBook.get();
        oldBook1.setTitle(book.getTitle());
        oldBook1.setType(book.getType());
        oldBook1.setGenre(book.getGenre());
        oldBook1.setDescription(book.getDescription());
        oldBook1.setPages(book.getPages());

        repository.save(oldBook1);
        return oldBook;
    }

    @Override
    public List<Book> findByAuthorId(Long id) {
        return repository.findBooksByAuthorId(id);
    }

    @Override
    public List<Book> findBooksByPagesGreaterThan(Integer pages) {
        return repository.findBooksByPagesGreaterThan(pages);
    }

    @Override
    public List<Book> findBooksSortedByAuthorNumberOfBooks() {
        return repository.findBooksSortedByAuthorNumOfBooks();
    }


}
