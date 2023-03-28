package ro.ubb.library.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.ubb.library.Model.Borrowing;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryDTO implements Serializable {
    private Long id;

    private String name;
    private String city;
    private String address;
    private String phoneNumber;
    private Integer capacity;

}
