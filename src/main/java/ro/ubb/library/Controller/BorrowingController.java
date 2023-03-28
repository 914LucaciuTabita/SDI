package ro.ubb.library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.library.DTO.BorrowingDTO;
import ro.ubb.library.Model.Borrowing;
import ro.ubb.library.Model.BorrowingId;
import ro.ubb.library.Service.Convertors;
import ro.ubb.library.Service.IBorrowingService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class BorrowingController {

    @Autowired
    private IBorrowingService service;

    @Autowired
    private Convertors convertors;

    @GetMapping("/borrowings")
    public ResponseEntity getBorrowings() {
        return ok(service.findAll());
    }

    @GetMapping("/borrowings/{bookId}&{libraryId}")
    public ResponseEntity getBorrowing(@PathVariable Long bookId, @PathVariable Long libraryId) {
        Optional<Borrowing> borrowing = service.findById(new BorrowingId(bookId, libraryId));
        if (borrowing.isPresent()) {
            return ok(borrowing.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/borrowings")
    public ResponseEntity addBorrowing(@RequestBody BorrowingDTO borrowingDTO) {
        Optional<Borrowing> borrowing = service.addBorrowing(borrowingDTO);
        if (borrowing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ok(borrowing.get());
    }

    @DeleteMapping("/borrowings/{bookId}&{libraryId}")
    public ResponseEntity deleteBorrowing(@PathVariable Long bookId, @PathVariable Long libraryId) {
        if (service.deleteBorrowing(new BorrowingId(bookId, libraryId))) {
            return ResponseEntity.ok("Deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/borrowings")
    public ResponseEntity updateBorrowings(@RequestBody BorrowingDTO borrowingDTO) {
        Optional<Borrowing> borrowing = service.updateBorrowing(borrowingDTO);
        if (borrowing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ok(borrowing.get());
    }

    @GetMapping("/borrowings/sorted")
    public ResponseEntity findLibrariesSortedByBorrowings(){
        return ResponseEntity.ok(service.findBorrowingsSortedByLibraryNumberOfBorrowings());
    }

    private ResponseEntity ok(Borrowing borrowing) {

        return ResponseEntity.ok(convertors.convertToBorrowingExtendedDTO(borrowing));
    }

    private ResponseEntity ok(List<Borrowing> borrowingList) {
        return ResponseEntity.ok(convertors.convertToBorrowingDTO(borrowingList));
    }
}
