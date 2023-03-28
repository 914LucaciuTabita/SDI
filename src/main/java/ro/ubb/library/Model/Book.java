package ro.ubb.library.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private String type;
    private String description;
    private Integer pages;

    public Book(Long id, String title, String genre, String type, String description, Integer pages, Author author) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.type = type;
        this.description = description;
        this.pages = pages;
        this.author = author;
    }

    public Book(String title, String genre, String type, String description, Integer pages) {
        this.title = title;
        this.genre = genre;
        this.type = type;
        this.description = description;
        this.pages = pages;
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "book")
    private Set<Borrowing> borrowing = new HashSet<>();

}
