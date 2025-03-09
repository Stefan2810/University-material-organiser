/** Clasa Test pentru CarteService
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.exemplu.catalogcarti.model.Carte;
import com.exemplu.catalogcarti.repository.CarteRepository;

public class CarteServiceTest {

    @InjectMocks
    private CarteService carteService;

    @Mock
    private CarteRepository carteRepository;

    private Carte carte;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        carte = new Carte("Titlu", "Autor", "Categorie", 2022);
    }

    @Test
    public void testAdaugaCarte() {
        when(carteRepository.save(any(Carte.class))).thenReturn(carte);

        Carte savedCarte = carteService.adaugaCarte(carte);

        assertNotNull(savedCarte);
        assertEquals("Titlu", savedCarte.getTitlu());
    }

    @Test
    public void testStergeCarte() {
        doNothing().when(carteRepository).deleteById(anyLong());

        carteService.stergeCarte(1L);

        verify(carteRepository, times(1)).deleteById(1L);
    }
}
