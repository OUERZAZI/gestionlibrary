package com.polytec.gestionlibrary.Services;

import com.polytec.gestionlibrary.Entities.Category;
import com.polytec.gestionlibrary.Repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ServiceCategory implements IServiceCategory {
    @Autowired
    private  CategoryRepository categoryRepository;

    @Override
    public void addCategory(Category category) {
        if (category.getId() != null) {
            // Charger l'entité existante pour éviter les conflits de contexte
            Category existingCategory = categoryRepository.findById(category.getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingCategory.setName(category.getName());
            existingCategory.setLibrary(category.getLibrary());
            existingCategory.setBooks(category.getBooks());
            categoryRepository.save(existingCategory);
        } else {
            categoryRepository.save(category);
        }
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void updateCategory(Category category) {
        if (category.getId() != null && categoryRepository.existsById(category.getId())) {
            categoryRepository.save(category);
        } else {
            throw new RuntimeException("Category not found for update");
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
