/** Clasa Controller pentru obiectele de tip Carte
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.controller;

import com.exemplu.catalogcarti.model.Carte;
import com.exemplu.catalogcarti.service.CarteService;
import com.exemplu.catalogcarti.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/carti")
public class CarteController {

    @Autowired
    private CarteService carteService;

    @Autowired
    private CarteRepository carteRepository; // Asigură-te că injectezi repository-ul corect

    @PostMapping
    public ResponseEntity<Carte> adaugaCarte(@RequestBody Carte carte) {
        if (carte.getTitlu() == null || carte.getAutor() == null || carte.getAnPublicare() == 0) {
            return ResponseEntity.badRequest().build();
        }
        Carte carteSalvata = carteService.adaugaCarte(carte);
        return ResponseEntity.ok(carteSalvata);
    }

    @GetMapping
    public ResponseEntity<List<Carte>> getCarti() {
        List<Carte> carti = carteService.getToateCartile();
        return ResponseEntity.ok(carti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carte> getCarte(@PathVariable Long id) {
        System.out.println("Fetching book with ID: " + id);

        Optional<Carte> carte = carteRepository.findById(id);

        return carte.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carte> actualizeazaCarte(@PathVariable Long id, @RequestBody Carte carteNoua) {
        Optional<Carte> carteExistenta = carteRepository.findById(id);

        if (carteExistenta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Carte carteDeActualizat = carteExistenta.get();

        carteDeActualizat.setTitlu(carteNoua.getTitlu());
        carteDeActualizat.setAutor(carteNoua.getAutor());
        carteDeActualizat.setAnPublicare(carteNoua.getAnPublicare());

        carteRepository.save(carteDeActualizat);

        return ResponseEntity.ok(carteDeActualizat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> stergeCarte(@PathVariable Long id) {
        carteService.stergeCarte(id);
        return ResponseEntity.noContent().build();
    }
}
