package ro.ubb.library.Service;

import ro.ubb.library.Model.Borrowing;
import ro.ubb.library.Model.Library;

import java.util.List;
import java.util.Optional;

public interface ILibraryService {

    List<Library> findAll();

    Optional<Library> findById(Long id);

    Library addLibrary(Library library);

    Boolean deleteLibrary(Long id);

    Optional<Library> updateLibrary(Library library);
}
