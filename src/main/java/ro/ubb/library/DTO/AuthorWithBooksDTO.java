package ro.ubb.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorWithBooksDTO implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String biography;
    private String nationality;
    private Integer birthYear;
    private List<BookDTO> books;
}
