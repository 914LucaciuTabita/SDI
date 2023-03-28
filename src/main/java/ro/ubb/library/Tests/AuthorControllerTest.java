package ro.ubb.library.Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.ubb.library.DTO.AuthorWithAVGDTO;
import ro.ubb.library.DTO.AuthorWithBooksDTO;
import ro.ubb.library.DTO.IAuthorWithAVG;
import ro.ubb.library.Model.Author;
import ro.ubb.library.Repository.IAuthorRepository;
import ro.ubb.library.Service.AuthorService;
import ro.ubb.library.Service.Convertors;
import ro.ubb.library.Service.IAuthorService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorControllerTest {

    @Mock
    private IAuthorRepository repositoryMock;

    @InjectMocks
    private AuthorService authorService;

    @Test
    public void testGetAuthorsBornAfter() {
        //sample data
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author(1L, "Author 1", "author1@test.com", "Bio 1", "Nationality 1", 1980, new ArrayList<>());
        Author author2 = new Author(2L, "Author 2", "author2@test.com", "Bio 2", "Nationality 2", 1990, new ArrayList<>());
        authors.add(author1);
        authors.add(author2);

        when(repositoryMock.findAuthorsByBirthYearGreaterThan(1985)).thenReturn(List.of(author2));

        // act
        List<Author> result = authorService.getAuthorsBornAfter(1985);

        // assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(author2.getId());
    }

    @Test
    public void testGetAuthorsOrderedByAverageNumberOfPages() {
        // arrange
        List<IAuthorWithAVG> authorDTOS = new ArrayList<>();
        Author author1 = new Author(1L, "Author 1", "author1@test.com", "Bio 1", "Nationality 1", 1980, new ArrayList<>());
        Author author2 = new Author(2L, "Author 2", "author2@test.com", "Bio 2", "Nationality 2", 1990, new ArrayList<>());
        IAuthorWithAVG authorWithAVG1 = new IAuthorWithAVG() {
            @Override
            public Author getAuthor() {
                return author1;
            }

            @Override
            public Double getAveragePages() {
                return 100.0;
            }
        };
        IAuthorWithAVG authorWithAVG2 = new IAuthorWithAVG() {
            @Override
            public Author getAuthor() {
                return author2;
            }

            @Override
            public Double getAveragePages() {
                return 50.0;
            }
        };
        authorDTOS.add(authorWithAVG1);
        authorDTOS.add(authorWithAVG2);
        when(repositoryMock.findAuthorWithAverage()).thenReturn(authorDTOS);

        // act
        List<AuthorWithAVGDTO> result = authorService.getAuthorsOrderedByAverageNumberOfPages();

        // assert
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(author1.getId());
        assertThat(result.get(0).getAveragePages()).isEqualTo(authorWithAVG1.getAveragePages());
        assertThat(result.get(1).getId()).isEqualTo(author2.getId());
        assertThat(result.get(1).getAveragePages()).isEqualTo(authorWithAVG2.getAveragePages());
    }
}
