package ro.ubb.library.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.library.Model.Book;
import ro.ubb.library.Model.IBookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{

    @Autowired
    private IBookRepository repository;

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Boolean addBook(Book book) {
        if (book.getId() != null && repository.existsById(book.getId())) {
            return false;
        }
        repository.save(book);
        return true;
    }

    public void addBook(String title, String author, String genre, String type, String description) {
        Book book = Book.builder().title(title).author(author).genre(genre).type(type).description(description).build();
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
    public Boolean updateBook(Long id, String title, String author, String genre, String type, String description) {
        Optional<Book> bookOpt = repository.findById(id);
        if (bookOpt.isEmpty()) {
            return false;
        }
        Book book = bookOpt.get();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setType(type);
        book.setDescription(description);
        return true;
    }

    @Override
    public Boolean updateBook(Book book) {
        if (!repository.existsById(book.getId())) {
            return false;
        }
        repository.save(book);
        return true;
    }
}
