/** Clasa Test pentru modelul Carte
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarteTest {

    @Test
    void getId() {
        // Arrange
        Carte carte = new Carte();
        carte.setId(1L);

        // Act
        Long id = carte.getId();

        // Assert
        assertEquals(1L, id);
    }

    @Test
    void setId() {
        // Arrange
        Carte carte = new Carte();

        // Act
        carte.setId(1L);

        // Assert
        assertEquals(1L, carte.getId());
    }

    @Test
    void getTitlu() {
        // Arrange
        Carte carte = new Carte();
        carte.setTitlu("Titlu Test");

        // Act
        String titlu = carte.getTitlu();

        // Assert
        assertEquals("Titlu Test", titlu);
    }

    @Test
    void setTitlu() {
        // Arrange
        Carte carte = new Carte();

        // Act
        carte.setTitlu("Titlu Test");

        // Assert
        assertEquals("Titlu Test", carte.getTitlu());
    }

    @Test
    void getAutor() {
        // Arrange
        Carte carte = new Carte();
        carte.setAutor("Autor Test");

        // Act
        String autor = carte.getAutor();

        // Assert
        assertEquals("Autor Test", autor);
    }

    @Test
    void setAutor() {
        // Arrange
        Carte carte = new Carte();

        // Act
        carte.setAutor("Autor Test");

        // Assert
        assertEquals("Autor Test", carte.getAutor());
    }

    @Test
    void getCategorie() {
        // Arrange
        Carte carte = new Carte();
        carte.setCategorie("Categorie Test");

        // Act
        String categorie = carte.getCategorie();

        // Assert
        assertEquals("Categorie Test", categorie);
    }

    @Test
    void setCategorie() {
        // Arrange
        Carte carte = new Carte();

        // Act
        carte.setCategorie("Categorie Test");

        // Assert
        assertEquals("Categorie Test", carte.getCategorie());
    }

    @Test
    void getAnPublicare() {
        // Arrange
        Carte carte = new Carte();
        carte.setAnPublicare(2023);

        // Act
        int anPublicare = carte.getAnPublicare();

        // Assert
        assertEquals(2023, anPublicare);
    }

    @Test
    void setAnPublicare() {
        // Arrange
        Carte carte = new Carte();

        // Act
        carte.setAnPublicare(2023);

        // Assert
        assertEquals(2023, carte.getAnPublicare());
    }
}
