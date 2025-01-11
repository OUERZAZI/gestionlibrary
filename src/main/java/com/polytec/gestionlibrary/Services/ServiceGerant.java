package com.polytec.gestionlibrary.Services;

import com.polytec.gestionlibrary.Entities.Gerant;
import com.polytec.gestionlibrary.Repositories.GerantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ServiceGerant implements IServiceGerant {
    @Autowired
    private GerantRepository gerantRepository;

    @Override
    public void addGerant(Gerant gerant) {
        gerantRepository.save(gerant);
    }

    @Override
    public Gerant getGerant(Long id) {
        return gerantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gerant not found"));
    }

    @Override
    public List<Gerant> getAllGerants() {
        return gerantRepository.findAll();
    }

    @Override
    public void updateGerant(Gerant gerant) {
        if (gerant.getId() != null && gerantRepository.existsById(gerant.getId())) {
            gerantRepository.save(gerant);
        } else {
            throw new RuntimeException("Gerant not found for update");
        }
    }

    @Override
    public void deleteGerant(Long id) {
        gerantRepository.deleteById(id);
    }
}