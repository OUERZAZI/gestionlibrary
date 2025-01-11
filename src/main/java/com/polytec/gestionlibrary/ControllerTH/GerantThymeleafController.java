package com.polytec.gestionlibrary.ControllerTH;

import com.polytec.gestionlibrary.Entities.Gerant;
import com.polytec.gestionlibrary.Services.IServiceGerant;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/gerants")
public class GerantThymeleafController {

    private IServiceGerant gerantService;


    @GetMapping
    public String listGerants(Model model) {
        List<Gerant> gerants = gerantService.getAllGerants();
        model.addAttribute("gerants", gerants);
        return "listGerant";
    }

    @GetMapping("/form")
    public String showAddForm(Model model) {
        model.addAttribute("gerant", new Gerant());
        return "addGerant";
    }

    @PostMapping("/save")
    public String saveGerant(@ModelAttribute Gerant gerant) {
        gerantService.addGerant(gerant);
        return "redirect:/gerants";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Gerant gerant = gerantService.getGerant(id);
        if (gerant != null) {
            model.addAttribute("gerant", gerant);
            return "addGerant";
        }
        return "redirect:/gerants";
    }

    @GetMapping("/delete/{id}")
    public String deleteGerant(@PathVariable Long id) {
        gerantService.deleteGerant(id);
        return "redirect:/gerants";
    }
}
