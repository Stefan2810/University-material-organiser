/** Clasa Test pentru CarteRepository
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */


package com.exemplu.catalogcarti.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.exemplu.catalogcarti.model.Carte;

@DataJpaTest
public class CarteRepositoryTest {

    @Autowired
    private CarteRepository carteRepository;

    @Test
    public void testSaveCarte() {
        Carte carte = new Carte("Titlu", "Autor", "Categorie", 2022);
        Carte savedCarte = carteRepository.save(carte);

        assertNotNull(savedCarte);
        assertEquals("Titlu", savedCarte.getTitlu());
    }
}