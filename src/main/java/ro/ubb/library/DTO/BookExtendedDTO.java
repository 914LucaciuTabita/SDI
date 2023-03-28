package ro.ubb.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.ubb.library.Model.Author;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookExtendedDTO implements Serializable {
    private Long id;
    private String title;
    private String genre;
    private String type;
    private String description;
    private Integer pages;
    private AuthorDTO author;
    private Set<BorrowingDTO> borrowings;
}
