/** Clasa Test pentru CerereImprumutController
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.controller;

import com.exemplu.catalogcarti.model.CerereImprumut;
import com.exemplu.catalogcarti.repository.CerereImprumutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CerereImprumutControllerTest {

    @InjectMocks
    private CerereImprumutController cerereImprumutController;

    @Mock
    private CerereImprumutRepository cerereImprumutRepository;

    private CerereImprumut cerere;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cerere = new CerereImprumut("user1", "TitluCarte", "in asteptare");
        cerere.setId(1L);
    }

    @Test
    void trimiteCerere() {
        // Arrange
        when(cerereImprumutRepository.save(any(CerereImprumut.class))).thenReturn(cerere);

        // Act
        ResponseEntity<CerereImprumut> response = cerereImprumutController.trimiteCerere(cerere);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("in asteptare", response.getBody().getStatus());
        verify(cerereImprumutRepository, times(1)).save(cerere);
    }

    @Test
    void getCereri() {
        // Arrange
        List<CerereImprumut> cereri = new ArrayList<>();
        cereri.add(cerere);
        when(cerereImprumutRepository.findByStatus("in asteptare")).thenReturn(cereri);

        // Act
        ResponseEntity<List<CerereImprumut>> response = cerereImprumutController.getCereri();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("in asteptare", response.getBody().get(0).getStatus());
        verify(cerereImprumutRepository, times(1)).findByStatus("in asteptare");
    }

    @Test
    void modificaStatus_CerereExistenta() {
        // Arrange
        CerereImprumut cerereActualizata = new CerereImprumut("user1", "TitluCarte", "aprobat");
        when(cerereImprumutRepository.findById(1L)).thenReturn(Optional.of(cerere));
        when(cerereImprumutRepository.save(any(CerereImprumut.class))).thenReturn(cerereActualizata);

        // Act
        CerereImprumut response = cerereImprumutController.modificaStatus(1L, cerereActualizata);

        // Assert
        assertNotNull(response);
        assertEquals("aprobat", response.getStatus());
        verify(cerereImprumutRepository, times(1)).findById(1L);
        verify(cerereImprumutRepository, times(1)).save(cerere);
    }

    @Test
    void modificaStatus_CerereInexistenta() {
        // Arrange
        when(cerereImprumutRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                cerereImprumutController.modificaStatus(1L, cerere)
        );

        // Verifică statusul folosind getRawStatusCode()
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatusCode());
        assertEquals("Cerere de imprumut inexistenta", exception.getReason());

        // Verifică interacțiunile cu repository-ul
        verify(cerereImprumutRepository, times(1)).findById(1L);
        verify(cerereImprumutRepository, never()).save(any());
    }

}
