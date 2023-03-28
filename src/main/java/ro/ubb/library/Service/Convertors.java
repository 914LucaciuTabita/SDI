package ro.ubb.library.Service;


import org.springframework.stereotype.Component;
import ro.ubb.library.DTO.*;
import ro.ubb.library.Model.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Convertors {

    public AuthorWithBooksDTO convertToAuthorWithBooksDTO(Author author) {
        return new AuthorWithBooksDTO(author.getId(), author.getName(), author.getEmail(), author.getBiography(), author.getNationality(), author.getBirthYear(), author.getBooks().stream().map(this::convertToBookDTO).collect(Collectors.toList()));
    }

    public AuthorDTO convertToAuthorDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getName(), author.getEmail(), author.getBiography(), author.getNationality(), author.getBirthYear());
    }

    public List<AuthorDTO> convertToAuthorDTO(List<Author> authors) {
        return authors.stream().map(this::convertToAuthorDTO).collect(Collectors.toList());
    }

    public BookExtendedDTO convertToBEDTO(Book book) {
        return new BookExtendedDTO(book.getId(), book.getTitle(), book.getGenre(), book.getType(), book.getDescription(), book.getPages(),convertToAuthorDTO(book.getAuthor()), book.getBorrowing().stream().map(this::convertToBorrowingDTO).collect(Collectors.toSet()));
    }

    public List<BookExtendedDTO> convertToBEDTO(List<Book> books) {
        return books.stream().map(this::convertToBEDTO).collect(Collectors.toList());
    }

    public BookDTO convertToBookDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getGenre(), book.getType(), book.getDescription(), book.getPages(), book.getAuthor().getId());
    }

    public List<BookDTO> convertToBookDTO(List<Book> books) {
        return books.stream().map(this::convertToBookDTO).collect(Collectors.toList());
    }

    public BorrowingDTO convertToBorrowingDTO(Borrowing borrowing) {
        return new BorrowingDTO(borrowing.getBorrowingId().getBookId(), borrowing.getBorrowingId().getLibraryId(), borrowing.getFirstDate(), borrowing.getLastDate());
    }

    public LibraryDTO convertToLibraryDTO(Library lib) {
        return new LibraryDTO(lib.getId(), lib.getName(), lib.getCity(), lib.getAddress(), lib.getPhoneNumber(), lib.getCapacity());

    }

    public List<LibraryDTO> convertToLibraryDTO(List<Library> libraryList) {
        return libraryList.stream().map(this::convertToLibraryDTO).collect(Collectors.toList());
    }

    public LibraryWithBorrowingDTO convertToLibraryWithBorrowingDTO(Library lib) {
        return new LibraryWithBorrowingDTO(lib.getId(), lib.getName(), lib.getCity(), lib.getAddress(), lib.getPhoneNumber(), lib.getCapacity(), lib.getBorrowings().stream().map(borrowing -> new BorrowingDTO(borrowing.getBorrowingId().getBookId(), borrowing.getBorrowingId().getLibraryId(), borrowing.getFirstDate(), borrowing.getLastDate())).collect(Collectors.toSet()));
    }

    public Borrowing convertToBorrowing(BorrowingDTO borrowingDTO) {
        BorrowingId borrowingId = new BorrowingId(borrowingDTO.getBookId(), borrowingDTO.getLibraryId());
        return new Borrowing(borrowingId, borrowingDTO.getFirstDate(), borrowingDTO.getLastDate());
    }

    public List<BorrowingDTO> convertToBorrowingDTO(List<Borrowing> borrowingList) {
        return borrowingList.stream().map(this::convertToBorrowingDTO).collect(Collectors.toList());
    }

    public BorrowingExtendedDTO convertToBorrowingExtendedDTO(Borrowing borrowing) {
        return new BorrowingExtendedDTO(convertToBookDTO(borrowing.getBook()), convertToLibraryDTO(borrowing.getLibrary()), borrowing.getFirstDate(), borrowing.getLastDate());
    }
}
