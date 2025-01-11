package com.polytec.gestionlibrary.Controllers;

import com.polytec.gestionlibrary.Entities.Library;
import com.polytec.gestionlibrary.Entities.Gerant;
import com.polytec.gestionlibrary.Services.IServiceLibrary;
import com.polytec.gestionlibrary.Services.IServiceGerant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    private final IServiceLibrary libraryService;
    private final IServiceGerant gerantService;

    public LibraryController(IServiceLibrary libraryService, IServiceGerant gerantService) {
        this.libraryService = libraryService;
        this.gerantService = gerantService;
    }

    @PostMapping
    public ResponseEntity<String> addLibrary(@RequestBody Library library) {
        if (library.getGerant() != null && library.getGerant().getId() != null) {
            Gerant gerant = gerantService.getGerant(library.getGerant().getId());
            library.setGerant(gerant);
        }
        libraryService.addLibrary(library);
        return ResponseEntity.ok("Library added successfully!");
    }

    @GetMapping
    public List<Library> getAllLibraries() {
        return libraryService.getAllLibraries();
    }

    @GetMapping("/{id}")
    public Library getLibrary(@PathVariable Long id) {
        return libraryService.getLibrary(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLibrary(@PathVariable Long id, @RequestBody Library updatedLibrary) {
        Library existingLibrary = libraryService.getLibrary(id);
        existingLibrary.setName(updatedLibrary.getName());
        existingLibrary.setAddress(updatedLibrary.getAddress());
        libraryService.updateLibrary(existingLibrary);
        return ResponseEntity.ok("Library updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLibrary(@PathVariable Long id) {
        libraryService.deleteLibrary(id);
        return ResponseEntity.ok("Library deleted successfully!");
    }
}
