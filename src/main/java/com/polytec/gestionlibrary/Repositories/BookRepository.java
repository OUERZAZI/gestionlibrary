package com.polytec.gestionlibrary.Repositories;

import com.polytec.gestionlibrary.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
