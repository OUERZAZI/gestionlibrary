package com.polytec.gestionlibrary.Controllers;

import com.polytec.gestionlibrary.Entities.Gerant;
import com.polytec.gestionlibrary.Services.IServiceGerant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gerants")
public class GerantController {

    private final IServiceGerant gerantService;

    public GerantController(IServiceGerant gerantService) {
        this.gerantService = gerantService;
    }

    @PostMapping
    public ResponseEntity<String> addGerant(@RequestBody Gerant gerant) {
        gerantService.addGerant(gerant);
        return ResponseEntity.ok("Gerant added successfully!");
    }

    @GetMapping
    public List<Gerant> getAllGerants() {
        return gerantService.getAllGerants();
    }

    @GetMapping("/{id}")
    public Gerant getGerant(@PathVariable Long id) {
        return gerantService.getGerant(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGerant(@PathVariable Long id, @RequestBody Gerant updatedGerant) {
        Gerant existingGerant = gerantService.getGerant(id);
        existingGerant.setName(updatedGerant.getName());
        existingGerant.setEmail(updatedGerant.getEmail());
        gerantService.updateGerant(existingGerant);
        return ResponseEntity.ok("Gerant updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGerant(@PathVariable Long id) {
        gerantService.deleteGerant(id);
        return ResponseEntity.ok("Gerant deleted successfully!");
    }
}
