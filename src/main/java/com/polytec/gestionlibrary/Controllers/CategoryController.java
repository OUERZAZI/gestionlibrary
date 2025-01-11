package com.polytec.gestionlibrary.Controllers;

import com.polytec.gestionlibrary.Entities.Category;
import com.polytec.gestionlibrary.Entities.Book;
import com.polytec.gestionlibrary.Entities.Library;
import com.polytec.gestionlibrary.Services.IServiceCategory;
import com.polytec.gestionlibrary.Services.IServiceBook;
import com.polytec.gestionlibrary.Services.IServiceLibrary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final IServiceCategory categoryService;
    private final IServiceLibrary libraryService;
    private final IServiceBook bookService;

    public CategoryController(IServiceCategory categoryService, IServiceLibrary libraryService, IServiceBook bookService) {
        this.categoryService = categoryService;
        this.libraryService = libraryService;
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<String> addCategory(@RequestBody Category category) {
        if (category.getLibrary() != null && category.getLibrary().getId() != null) {
            Library library = libraryService.getLibrary(category.getLibrary().getId());
            category.setLibrary(library);
        }
        if (category.getBooks() != null && !category.getBooks().isEmpty()) {
            List<Book> books = category.getBooks().stream()
                    .map(book -> bookService.getBook(book.getId()))
                    .collect(Collectors.toList());
            category.setBooks(books);
        }
        categoryService.addCategory(category);
        return ResponseEntity.ok("Category added successfully!");
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        Category existingCategory = categoryService.getCategory(id);
        if (updatedCategory.getName() != null) {
            existingCategory.setName(updatedCategory.getName());
        }
        categoryService.updateCategory(existingCategory);
        return ResponseEntity.ok("Category updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully!");
    }
}
