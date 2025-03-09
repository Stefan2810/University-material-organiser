/** Clasa Test pentru CerereImprumutService
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.service;

import com.exemplu.catalogcarti.model.CerereImprumut;
import com.exemplu.catalogcarti.repository.CerereImprumutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CerereImprumutServiceTest {

    @InjectMocks
    private CerereImprumutService cerereImprumutService;

    @Mock
    private CerereImprumutRepository cerereImprumutRepository;

    private CerereImprumut cerere;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cerere = new CerereImprumut();
        cerere.setId(1L);
        cerere.setUsernameCititor("testUser");
        cerere.setTitluCarte("Test Carte");
        cerere.setStatus("in asteptare");
    }

    @Test
    void actualizeazaStatusCerere_Success() {
        // Arrange
        when(cerereImprumutRepository.findById(1L)).thenReturn(Optional.of(cerere));
        when(cerereImprumutRepository.save(any(CerereImprumut.class))).thenReturn(cerere);

        // Act
        ResponseEntity<String> response = cerereImprumutService.actualizeazaStatusCerere(1L, "aprobat");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Status actualizat.", response.getBody());
        verify(cerereImprumutRepository, times(1)).findById(1L);
        verify(cerereImprumutRepository, times(1)).save(any(CerereImprumut.class));
    }

    @Test
    void actualizeazaStatusCerere_NotFound() {
        // Arrange
        when(cerereImprumutRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<String> response = cerereImprumutService.actualizeazaStatusCerere(1L, "aprobat");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Cererea nu exista.", response.getBody());
        verify(cerereImprumutRepository, times(1)).findById(1L);
        verify(cerereImprumutRepository, never()).save(any(CerereImprumut.class));
    }

    @Test
    void actualizeazaStatusCerere_InvalidStatus() {
        // Arrange
        when(cerereImprumutRepository.findById(1L)).thenReturn(Optional.of(cerere));

        // Act
        ResponseEntity<String> response = cerereImprumutService.actualizeazaStatusCerere(1L, "invalidStatus");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Status invalid.", response.getBody());
        verify(cerereImprumutRepository, times(1)).findById(1L);
        verify(cerereImprumutRepository, never()).save(any(CerereImprumut.class));
    }
}
