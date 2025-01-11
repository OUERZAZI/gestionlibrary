package com.polytec.gestionlibrary.Services;

import com.polytec.gestionlibrary.Entities.Gerant;

import java.util.List;

public interface IServiceGerant {
    void addGerant(Gerant gerant);
    Gerant getGerant(Long id);
    List<Gerant> getAllGerants();
    void updateGerant(Gerant gerant);
    void deleteGerant(Long id);
}