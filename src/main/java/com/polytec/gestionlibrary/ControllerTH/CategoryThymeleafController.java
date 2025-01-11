package com.polytec.gestionlibrary.ControllerTH;

import com.polytec.gestionlibrary.Entities.Category;
import com.polytec.gestionlibrary.Services.IServiceCategory;
import com.polytec.gestionlibrary.Services.IServiceBook;
import com.polytec.gestionlibrary.Services.IServiceLibrary;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryThymeleafController {

    private IServiceCategory categoryService;
    private IServiceLibrary libraryService;
    private IServiceBook bookService;


    @GetMapping
    public String listCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "listCategory";
    }

    @GetMapping("/form")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("libraries", libraryService.getAllLibraries());
        model.addAttribute("books", bookService.getAllBooks());
        return "addCategory";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute Category category) {
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategory(id);
        if (category != null) {
            model.addAttribute("category", category);
            model.addAttribute("libraries", libraryService.getAllLibraries());
            model.addAttribute("books", bookService.getAllBooks());
            return "addCategory";
        }
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}