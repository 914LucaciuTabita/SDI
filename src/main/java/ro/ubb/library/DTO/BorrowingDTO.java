package ro.ubb.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingDTO implements Serializable {
    private Long bookId;
    private Long libraryId;
    private String firstDate;
    private String lastDate;
}
