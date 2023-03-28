package ro.ubb.library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.library.Model.Author;
import ro.ubb.library.Service.Convertors;
import ro.ubb.library.Service.IAuthorService;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private Convertors convertors;

    @PostMapping("/authors")
    public ResponseEntity addAuthor(@RequestBody Author author) {
        return ok(authorService.addAuthor(author));
    }

    @GetMapping("/authors")
    public ResponseEntity getAuthors() {
        return ok(authorService.getAuthors());
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.findAuthorById(id);
        if (author.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ok(author.get());
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity deleteAuthor(@PathVariable Long id) {
        if (authorService.deleteAuthor(id)) {
            return ResponseEntity.ok("Deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/authors")
    public ResponseEntity updateAuthor(@RequestBody Author author) {
        Optional<Author> author1 = authorService.updateAuthor(author);
        if (author1.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ok(author1.get());
    }

    @GetMapping("/authors/year/{year}")
    public ResponseEntity getAuthorsBornAfter(@PathVariable Integer year) {
        return ok(authorService.getAuthorsBornAfter(year));
    }

    @GetMapping("/authors/avg")
    public ResponseEntity getAuthorsByAvg() {
        return ResponseEntity.ok(authorService.getAuthorsOrderedByAverageNumberOfPages());
    }

    private ResponseEntity ok(Author author) {
        return ResponseEntity.ok(convertors.convertToAuthorWithBooksDTO(author));
    }

    private ResponseEntity ok(List<Author> authors) {
        return ResponseEntity.ok(convertors.convertToAuthorDTO(authors));
    }
}
