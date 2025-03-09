/** Clasa Test pentru modelul Utilizator
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */


package com.exemplu.catalogcarti.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilizatorTest {

    @Test
    void getId() {
        // Arrange
        Utilizator utilizator = new Utilizator();
        utilizator.setId(1L);

        // Act
        Long id = utilizator.getId();

        // Assert
        assertEquals(1L, id);
    }

    @Test
    void setId() {
        // Arrange
        Utilizator utilizator = new Utilizator();

        // Act
        utilizator.setId(1L);

        // Assert
        assertEquals(1L, utilizator.getId());
    }

    @Test
    void getUsername() {
        // Arrange
        Utilizator utilizator = new Utilizator();
        utilizator.setUsername("testUser");

        // Act
        String username = utilizator.getUsername();

        // Assert
        assertEquals("testUser", username);
    }

    @Test
    void setUsername() {
        // Arrange
        Utilizator utilizator = new Utilizator();

        // Act
        utilizator.setUsername("testUser");

        // Assert
        assertEquals("testUser", utilizator.getUsername());
    }

    @Test
    void getPassword() {
        // Arrange
        Utilizator utilizator = new Utilizator();
        utilizator.setPassword("testPass");

        // Act
        String password = utilizator.getPassword();

        // Assert
        assertEquals("testPass", password);
    }

    @Test
    void setPassword() {
        // Arrange
        Utilizator utilizator = new Utilizator();

        // Act
        utilizator.setPassword("testPass");

        // Assert
        assertEquals("testPass", utilizator.getPassword());
    }

    @Test
    void getRol() {
        // Arrange
        Utilizator utilizator = new Utilizator();
        utilizator.setRol("admin");

        // Act
        String rol = utilizator.getRol();

        // Assert
        assertEquals("admin", rol);
    }

    @Test
    void setRol() {
        // Arrange
        Utilizator utilizator = new Utilizator();

        // Act
        utilizator.setRol("admin");

        // Assert
        assertEquals("admin", utilizator.getRol());
    }
}
