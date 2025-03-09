/** Clasa Service pentru obiecte de tip Carte
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.service;

import com.exemplu.catalogcarti.model.Carte;
import com.exemplu.catalogcarti.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarteService {

    @Autowired
    private CarteRepository carteRepository;

    public List<Carte> getToateCartile() {
        return carteRepository.findAll();
    }

    public Carte adaugaCarte(Carte carte) {
        return carteRepository.save(carte);
    }

    public void stergeCarte(Long id) {
        Optional<Carte> carteOptional = carteRepository.findById(id);
        if (carteOptional.isPresent()) {
            carteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cartea cu ID-ul " + id + " nu a fost găsită.");
        }
    }

    public Optional<Carte> getCarteById(Long id) {
        return carteRepository.findById(id);
    }
}
