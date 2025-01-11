package com.polytec.gestionlibrary.Repositories;

import com.polytec.gestionlibrary.Entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
