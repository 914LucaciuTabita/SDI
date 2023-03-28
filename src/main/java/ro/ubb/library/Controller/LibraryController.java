package ro.ubb.library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.library.DTO.BorrowingDTO;
import ro.ubb.library.DTO.LibraryDTO;
import ro.ubb.library.Model.Library;
import ro.ubb.library.Service.Convertors;
import ro.ubb.library.Service.ILibraryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class LibraryController {

    @Autowired
    private ILibraryService service;

    @Autowired
    private Convertors convertors;

    @GetMapping("/libraries")
    public ResponseEntity getLibraries() {
        return ok(service.findAll());
    }

    @PostMapping("/libraries")
    public ResponseEntity addLibrary(@RequestBody Library library) {
        return ok(service.addLibrary(library));
    }

    @GetMapping("/libraries/{id}")
    public ResponseEntity getLibraryById(@PathVariable Long id) {
        Optional<Library> library = service.findById(id);
        if (library.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Library lib = library.get();
        return ok(lib);
    }

    @DeleteMapping("/libraries/{id}")
    public ResponseEntity deleteLibrary(@PathVariable Long id) {
        if (service.deleteLibrary(id)) {
            return ResponseEntity.ok("Deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/libraries")
    public ResponseEntity updateLibraries(@RequestBody Library library) {
        Optional<Library> library1 = service.updateLibrary(library);
        if (library1.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ok(library1.get());
    }

    private ResponseEntity ok(Library library) {

        return ResponseEntity.ok(convertors.convertToLibraryWithBorrowingDTO(library));
    }

    private ResponseEntity ok(List<Library> libraryList) {
        return ResponseEntity.ok(convertors.convertToLibraryDTO(libraryList));
    }
}
