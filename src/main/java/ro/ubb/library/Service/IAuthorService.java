package ro.ubb.library.Service;

import ro.ubb.library.DTO.AuthorWithAVGDTO;
import ro.ubb.library.Model.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {
    List<Author> getAuthors();

    Author addAuthor(Author author);
    Optional<Author> findAuthorById(Long id);

    Boolean deleteAuthor(Long id);

    Optional<Author> updateAuthor(Author author);

    List<Author> getAuthorsBornAfter(Integer year);

    List<AuthorWithAVGDTO> getAuthorsOrderedByAverageNumberOfPages();
}
