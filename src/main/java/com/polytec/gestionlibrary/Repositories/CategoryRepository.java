package com.polytec.gestionlibrary.Repositories;

import com.polytec.gestionlibrary.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
