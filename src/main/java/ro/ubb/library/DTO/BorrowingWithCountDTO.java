package ro.ubb.library.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BorrowingWithCountDTO extends BorrowingDTO implements Serializable {
    private Integer countBorrowings;

    public BorrowingWithCountDTO(Long bookId, Long libraryId, String firstDate, String lastDate, Integer countBorrowings){
        super(bookId, libraryId, firstDate, lastDate);
        this.countBorrowings = countBorrowings;
    }
}
