/** Clasa Test pentru modelul CerereImprumut
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */


package com.exemplu.catalogcarti.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CerereImprumutTest {

    @Test
    void getId() {
        // Arrange
        CerereImprumut cerere = new CerereImprumut();
        cerere.setId(1L);

        // Act
        Long id = cerere.getId();

        // Assert
        assertEquals(1L, id);
    }

    @Test
    void setId() {
        // Arrange
        CerereImprumut cerere = new CerereImprumut();

        // Act
        cerere.setId(1L);

        // Assert
        assertEquals(1L, cerere.getId());
    }

    @Test
    void getUsernameCititor() {
        // Arrange
        CerereImprumut cerere = new CerereImprumut();
        cerere.setUsernameCititor("user1");

        // Act
        String username = cerere.getUsernameCititor();

        // Assert
        assertEquals("user1", username);
    }

    @Test
    void setUsernameCititor() {
        // Arrange
        CerereImprumut cerere = new CerereImprumut();

        // Act
        cerere.setUsernameCititor("user1");

        // Assert
        assertEquals("user1", cerere.getUsernameCititor());
    }

    @Test
    void getTitluCarte() {
        // Arrange
        CerereImprumut cerere = new CerereImprumut();
        cerere.setTitluCarte("Titlu Test");

        // Act
        String titluCarte = cerere.getTitluCarte();

        // Assert
        assertEquals("Titlu Test", titluCarte);
    }

    @Test
    void setTitluCarte() {
        // Arrange
        CerereImprumut cerere = new CerereImprumut();

        // Act
        cerere.setTitluCarte("Titlu Test");

        // Assert
        assertEquals("Titlu Test", cerere.getTitluCarte());
    }

    @Test
    void getStatus() {
        // Arrange
        CerereImprumut cerere = new CerereImprumut();
        cerere.setStatus("aprobat");

        // Act
        String status = cerere.getStatus();

        // Assert
        assertEquals("aprobat", status);
    }

    @Test
    void setStatus() {
        // Arrange
        CerereImprumut cerere = new CerereImprumut();

        // Act
        cerere.setStatus("aprobat");

        // Assert
        assertEquals("aprobat", cerere.getStatus());
    }
}
