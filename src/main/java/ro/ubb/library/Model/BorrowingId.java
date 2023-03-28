package ro.ubb.library.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BorrowingId implements Serializable {

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "library_id")
    private Long libraryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowingId that = (BorrowingId) o;
        return bookId.equals(that.bookId) && libraryId.equals(that.libraryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, libraryId);
    }
}
