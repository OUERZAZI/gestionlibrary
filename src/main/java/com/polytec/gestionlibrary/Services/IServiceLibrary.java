package com.polytec.gestionlibrary.Services;

import com.polytec.gestionlibrary.Entities.Library;

import java.util.List;

public interface IServiceLibrary {
    void addLibrary(Library library);
    Library getLibrary(Long id);
    List<Library> getAllLibraries();
    void updateLibrary(Library library);
    void deleteLibrary(Long id);
}
