package com.polytec.gestionlibrary.Services;

import com.polytec.gestionlibrary.Entities.Category;

import java.util.List;

public interface IServiceCategory {
    void addCategory(Category category);
    Category getCategory(Long id);
    List<Category> getAllCategories();
    void updateCategory(Category category);
    void deleteCategory(Long id);
}