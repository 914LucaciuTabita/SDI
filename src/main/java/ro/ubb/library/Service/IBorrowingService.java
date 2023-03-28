package ro.ubb.library.Service;

import ro.ubb.library.DTO.BorrowingDTO;
import ro.ubb.library.DTO.BorrowingWithCountDTO;
import ro.ubb.library.Model.Borrowing;
import ro.ubb.library.Model.BorrowingId;

import java.util.List;
import java.util.Optional;

public interface IBorrowingService {
    List<Borrowing> findAll();

    Optional<Borrowing> findById(BorrowingId borrowingId);

    Optional<Borrowing> addBorrowing(BorrowingDTO borrowing);

    Boolean deleteBorrowing(BorrowingId borrowingId);

    Optional<Borrowing> updateBorrowing(BorrowingDTO borrowingDTO);

    List<BorrowingWithCountDTO> findBorrowingsSortedByLibraryNumberOfBorrowings();
}
