package ro.ubb.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.library.DTO.IBorrowingWithCount;
import ro.ubb.library.Model.Borrowing;
import ro.ubb.library.Model.BorrowingId;

import java.util.List;

@Repository
public interface IBorrowingRepository extends JpaRepository<Borrowing, BorrowingId> {
    @Query("select b as borrowing, size(l.borrowings) as count from Borrowing b inner join Library l on b.library = l order by size(l.borrowings) desc")
    List<IBorrowingWithCount> findBorrowingsSortedByLibraryNumberOfBorrowings();
}
