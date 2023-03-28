package ro.ubb.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDTO implements Serializable{

    private Long id;
    private String title;
    private String genre;
    private String type;
    private String description;
    private Integer pages;
    private Long authorId;
}