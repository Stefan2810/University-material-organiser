/** Clasa Test pentru CarteController
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.exemplu.catalogcarti.model.Carte;
import com.exemplu.catalogcarti.service.CarteService;
import org.springframework.http.ResponseEntity;
import java.util.*;

public class CarteControllerTest {

    @InjectMocks
    private CarteController carteController;

    @Mock
    private CarteService carteService;

    private Carte carte;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        carte = new Carte("Titlu", "Autor", "Categorie", 2022);
    }

    @Test
    public void testAdaugaCarte() {
        when(carteService.adaugaCarte(any(Carte.class))).thenReturn(carte);

        ResponseEntity<Carte> response = carteController.adaugaCarte(carte);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Titlu", response.getBody().getTitlu());
    }

    @Test
    public void testGetCarti() {
        List<Carte> carti = Arrays.asList(carte);
        when(carteService.getToateCartile()).thenReturn(carti);

        ResponseEntity<List<Carte>> response = carteController.getCarti();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }
}