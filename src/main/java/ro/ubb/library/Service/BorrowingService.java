package ro.ubb.library.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.library.DTO.BorrowingDTO;
import ro.ubb.library.DTO.BorrowingWithCountDTO;
import ro.ubb.library.DTO.IBorrowingWithCount;
import ro.ubb.library.Model.Book;
import ro.ubb.library.Model.Borrowing;
import ro.ubb.library.Model.BorrowingId;
import ro.ubb.library.Model.Library;
import ro.ubb.library.Repository.IBookRepository;
import ro.ubb.library.Repository.IBorrowingRepository;
import ro.ubb.library.Repository.ILibraryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowingService implements IBorrowingService{

    @Autowired
    private IBorrowingRepository repository;

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private ILibraryRepository libraryRepository;

    @Override
    public List<Borrowing> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Borrowing> findById(BorrowingId borrowingId) {
        return repository.findById(borrowingId);
    }

    @Override
    public Optional<Borrowing> addBorrowing(BorrowingDTO borrowing) {
        Optional<Book> book = bookRepository.findById(borrowing.getBookId());
        Optional<Library> library = libraryRepository.findById(borrowing.getLibraryId());
        if (book.isEmpty() || library.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(repository.save(new Borrowing(new BorrowingId(borrowing.getBookId(), borrowing.getLibraryId()),book.get(), library.get(),borrowing.getFirstDate(), borrowing.getLastDate())));
    }

    @Override
    public Boolean deleteBorrowing(BorrowingId borrowingId) {
        if (!repository.existsById(borrowingId)) {
            return false;
        }
        repository.deleteById(borrowingId);
        return true;
    }

    @Override
    public Optional<Borrowing> updateBorrowing(BorrowingDTO borrowingDTO) {
        Optional<Borrowing> borrowing = repository.findById(new BorrowingId(borrowingDTO.getBookId(), borrowingDTO.getLibraryId()));
        if (borrowing.isEmpty()) {
            return borrowing;
        }
        Borrowing borrowing1 = borrowing.get();
        borrowing1.setFirstDate(borrowing1.getFirstDate());
        borrowing1.setLastDate(borrowing1.getLastDate());
        repository.save(borrowing1);
        return borrowing;
    }

    @Override
    public List<BorrowingWithCountDTO> findBorrowingsSortedByLibraryNumberOfBorrowings() {
        List<IBorrowingWithCount> borrowingDTOS = repository.findBorrowingsSortedByLibraryNumberOfBorrowings();
        return borrowingDTOS.stream().map(dto -> new BorrowingWithCountDTO(dto.getBorrowing().getBorrowingId().getBookId(), dto.getBorrowing().getBorrowingId().getLibraryId(), dto.getBorrowing().getFirstDate(), dto.getBorrowing().getLastDate(), dto.getCount())).collect(Collectors.toList());
    }
}
