package ro.ubb.library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.library.Model.Book;
import ro.ubb.library.Service.IBookService;

@RestController
public class BookController {

    @Autowired
    private IBookService service;

    @GetMapping("/books")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(service.getAllBooks());
    }

    @PostMapping("/books")
    public ResponseEntity addBook(@RequestBody Book book) {
        if (service.addBook(book)){
            return new ResponseEntity("Book successfully added" ,HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().body("Id already exists!");
    }

    @PutMapping("/books")
    public ResponseEntity updateBook(@RequestBody Book book) {
        if (service.updateBook(book)){
            return ResponseEntity.ok("Updated");
        }
        return ResponseEntity.badRequest().body("Id not found!");
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) {
        if (service.deleteBook(id)){
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.badRequest().body("Id not found!");
    }

}
