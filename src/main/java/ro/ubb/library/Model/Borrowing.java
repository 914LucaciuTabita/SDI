package ro.ubb.library.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Borrowing implements Serializable {
    @EmbeddedId
    private BorrowingId borrowingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("libraryId")
    @JoinColumn(name = "library_id")
    private Library library;

    private String firstDate;
    private String lastDate;

    public Borrowing(BorrowingId borrowingId, String firstDate, String lastDate) {
        this.borrowingId = borrowingId;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }
}
