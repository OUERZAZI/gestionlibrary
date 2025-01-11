package com.polytec.gestionlibrary.Services;

import com.polytec.gestionlibrary.Entities.Gerant;
import com.polytec.gestionlibrary.Entities.Library;
import com.polytec.gestionlibrary.Repositories.GerantRepository;
import com.polytec.gestionlibrary.Repositories.LibraryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ServiceLibrary implements IServiceLibrary {
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private GerantRepository gerantRepository;

    @Override
    public void addLibrary(Library library) {
        Library existingLibrary = null;
        if (library.getId() != null) {
            // Charger la bibliothèque existante pour éviter le conflit
            existingLibrary = libraryRepository.findById(library.getId())
                    .orElse(null);
        }

        if (library.getGerant() != null && library.getGerant().getId() != null) {
            Gerant gerant = gerantRepository.findById(library.getGerant().getId())
                    .orElseThrow(() -> new RuntimeException("Gerant introuvable avec l'ID : " + library.getGerant().getId()));
            library.setGerant(gerant);
        }

        if (existingLibrary != null) {
            // Mettre à jour les champs de la bibliothèque existante
            existingLibrary.setName(library.getName());
            existingLibrary.setAddress(library.getAddress());
            existingLibrary.setGerant(library.getGerant());
            libraryRepository.save(existingLibrary);
        } else {
            libraryRepository.save(library);
        }
    }

    @Override
    public Library getLibrary(Long id) {
        return libraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Library not found"));
    }

    @Override
    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    @Override
    public void updateLibrary(Library library) {
        if (library.getId() != null && libraryRepository.existsById(library.getId())) {
            libraryRepository.save(library);
        } else {
            throw new RuntimeException("Library not found for update");
        }
    }

    @Override
    public void deleteLibrary(Long id) {
        libraryRepository.deleteById(id);
    }
}
