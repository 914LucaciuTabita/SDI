package ro.ubb.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingExtendedDTO implements Serializable {
    private BookDTO book;
    private LibraryDTO library;
    private String firstDate;
    private String lastDate;
}
