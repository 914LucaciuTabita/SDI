package ro.ubb.library.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.library.Model.Borrowing;
import ro.ubb.library.Model.Library;
import ro.ubb.library.Repository.ILibraryRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService implements ILibraryService{

    @Autowired
    private ILibraryRepository repository;

    @Override
    public List<Library> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Library> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Library addLibrary(Library library) {
        return repository.save(new Library(null, library.getName(), library.getCity(), library.getAddress(), library.getPhoneNumber(), library.getCapacity(), new HashSet<>()));
    }

    @Override
    public Boolean deleteLibrary(Long id) {
        Optional<Library> library = repository.findById(id);
        if (library.isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Library> updateLibrary(Library library) {
        Optional<Library> oldLibrary = repository.findById(library.getId());
        if (oldLibrary.isEmpty()) {
            return oldLibrary;
        }
        Library toUpdate = oldLibrary.get();
        toUpdate.setName(library.getName());
        toUpdate.setCity(library.getCity());
        toUpdate.setAddress(toUpdate.getAddress());
        toUpdate.setPhoneNumber(library.getPhoneNumber());
        toUpdate.setCapacity(toUpdate.getCapacity());
        repository.save(toUpdate);
        return oldLibrary;
    }
}
