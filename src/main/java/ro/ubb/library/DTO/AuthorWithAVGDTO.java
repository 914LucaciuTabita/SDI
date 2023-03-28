package ro.ubb.library.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthorWithAVGDTO extends AuthorDTO implements Serializable {

    private Double averagePages;

    public AuthorWithAVGDTO(Long id, String name, String email, String biography, String nationality, Integer birthYear, Double averagePages) {
        super(id, name, email, biography, nationality, birthYear);
        this.averagePages = averagePages;
    }
}
