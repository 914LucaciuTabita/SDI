package ro.ubb.library.Repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.library.DTO.AuthorDTO;
import ro.ubb.library.DTO.IAuthorWithAVG;
import ro.ubb.library.Model.Author;

import java.util.List;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAuthorsByBirthYearGreaterThan(Integer birth_year);

    @Query("select a as author, avg(b.pages) as averagePages from Author a Join Book b on b.author = a group by a order by averagePages desc")
    List<IAuthorWithAVG> findAuthorWithAverage();
}
