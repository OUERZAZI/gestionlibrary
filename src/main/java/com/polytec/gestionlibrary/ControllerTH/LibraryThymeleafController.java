package com.polytec.gestionlibrary.ControllerTH;

import com.polytec.gestionlibrary.Entities.Library;
import com.polytec.gestionlibrary.Entities.Gerant;
import com.polytec.gestionlibrary.Services.IServiceLibrary;
import com.polytec.gestionlibrary.Services.IServiceGerant;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/libraries")
public class LibraryThymeleafController {

    private final IServiceLibrary libraryService;
    private final IServiceGerant gerantService;


    @GetMapping
    public String listLibraries(Model model) {
        List<Library> libraries = libraryService.getAllLibraries();
        model.addAttribute("libraries", libraries);
        return "listLibrary";
    }

    @GetMapping("/form")
    public String showAddForm(Model model) {
        model.addAttribute("library", new Library());
        model.addAttribute("gerants", gerantService.getAllGerants());
        return "addLibrary";
    }

    @PostMapping("/save")
    public String saveLibrary(@ModelAttribute Library library) {
        libraryService.addLibrary(library);
        return "redirect:/libraries";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Library library = libraryService.getLibrary(id);
        if (library != null) {
            model.addAttribute("library", library);
            model.addAttribute("gerants", gerantService.getAllGerants());
            return "addLibrary";
        }
        return "redirect:/libraries";
    }

    @GetMapping("/delete/{id}")
    public String deleteLibrary(@PathVariable Long id) {
        libraryService.deleteLibrary(id);
        return "redirect:/libraries";
    }
}
