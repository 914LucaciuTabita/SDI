package ro.ubb.library.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.ubb.library.DTO.AuthorWithAVGDTO;
import ro.ubb.library.DTO.IAuthorWithAVG;
import ro.ubb.library.Model.Author;
import ro.ubb.library.Repository.IAuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private IAuthorRepository repository;

    @Override
    public List<Author> getAuthors() {
        return repository.findAll();
    }

    @Override
    public Optional<Author> findAuthorById(Long id) {
        return repository.findById(id);
    }

    public Author addAuthor(Author author) {
        return repository.save(new Author(null, author.getName(), author.getEmail(), author.getBiography(), author.getNationality(), author.getBirthYear(), new ArrayList<>()));
    }

    @Override
    public Boolean deleteAuthor(Long id) {
        Optional<Author> author = repository.findById(id);
        if (author.isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Author> updateAuthor(Author author) {
        Optional<Author> oldAuthor = repository.findById(author.getId());
        if (oldAuthor.isEmpty()) {
            return oldAuthor;
        }
        Author oldAuthor1 = oldAuthor.get();
        oldAuthor1.setBiography(author.getBiography());
        oldAuthor1.setName(author.getName());
        oldAuthor1.setEmail(author.getEmail());
        oldAuthor1.setBirthYear(author.getBirthYear());
        oldAuthor1.setNationality(author.getNationality());
        repository.save(oldAuthor1);
        return oldAuthor;
    }

    @Override
    public List<Author> getAuthorsBornAfter(Integer year) {
        return repository.findAuthorsByBirthYearGreaterThan(year);
    }

    public List<AuthorWithAVGDTO> getAuthorsOrderedByAverageNumberOfPages() {
        List<IAuthorWithAVG> authorDTOS = repository.findAuthorWithAverage();
        return authorDTOS.stream().map(dto -> new AuthorWithAVGDTO(dto.getAuthor().getId(), dto.getAuthor().getName(), dto.getAuthor().getEmail(), dto.getAuthor().getBiography(), dto.getAuthor().getNationality(), dto.getAuthor().getBirthYear(), dto.getAveragePages())).collect(Collectors.toList());
    }
}
