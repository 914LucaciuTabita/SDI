package ro.ubb.library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.library.Model.Author;
import ro.ubb.library.Model.Book;
import ro.ubb.library.Service.Convertors;
import ro.ubb.library.Service.IAuthorService;
import ro.ubb.library.Service.IBookService;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private IBookService service;

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private Convertors convertors;

    @GetMapping("/books")
    public ResponseEntity getAll() {
        return ok(service.getAllBooks());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<Book> book = service.getBook(id);
        if (book.isPresent()) {
            return ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/books/{author_id}")
    public ResponseEntity addBook(@RequestBody Book book, @PathVariable Long author_id) {
        Optional<Author> author = authorService.findAuthorById(author_id);
        if (author.isEmpty()) {
            return ResponseEntity.badRequest().body("Author not found!");
        }
        Author author1 = author.get();
        if (service.addBook(new Book(null, book.getTitle(), book.getGenre(), book.getType(), book.getDescription(), book.getPages(), author1))) {
            return new ResponseEntity("Book successfully added", HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().body("Id already exists!");
    }

    @PutMapping("/books")
    public ResponseEntity updateBook(@RequestBody Book book) {
        Optional<Book> updatedBook = service.updateBook(book);
        if (updatedBook.isPresent()) {
            return ok(updatedBook.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) {
        if (service.deleteBook(id)) {
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/books/author/{id}")
    public ResponseEntity getByAuthor(@PathVariable Long id) {
        return ok(service.findByAuthorId(id));
    }

    @GetMapping("/books/pages/{pages}")
    public ResponseEntity getByPagesGreaterThan(@PathVariable Integer pages) {
        return ok(service.findBooksByPagesGreaterThan(pages));
    }

    @GetMapping("/books/sorted")
    public ResponseEntity findBooksSortedByAuthorNumBooks() {
        return ok(service.findBooksSortedByAuthorNumberOfBooks());
    }

    public ResponseEntity ok(Book book) {
        return ResponseEntity.ok(convertors.convertToBEDTO(book));
    }

    public ResponseEntity ok(List<Book> books) {
        return ResponseEntity.ok(convertors.convertToBookDTO(books));
    }
}
