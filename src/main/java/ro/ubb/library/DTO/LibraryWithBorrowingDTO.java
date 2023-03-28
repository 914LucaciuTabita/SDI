package ro.ubb.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryWithBorrowingDTO implements Serializable {
    private Long id;

    private String name;
    private String city;
    private String address;
    private String phoneNumber;
    private Integer capacity;

    private Set<BorrowingDTO> borrowings = new HashSet<>();
}
