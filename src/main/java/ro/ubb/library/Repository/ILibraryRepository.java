package ro.ubb.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.library.Model.Library;

@Repository
public interface ILibraryRepository extends JpaRepository<Library, Long> {
}
