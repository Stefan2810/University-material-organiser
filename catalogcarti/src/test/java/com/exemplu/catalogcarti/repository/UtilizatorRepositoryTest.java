/** Clasa Test pentru UtilizatorRepository
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.repository;

import com.exemplu.catalogcarti.model.Utilizator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UtilizatorRepositoryTest {

    @Autowired
    private UtilizatorRepository utilizatorRepository;

    @Test
    void findByUsernameAndPassword() {
        // Arrange
        Utilizator utilizator = new Utilizator("testUser", "testPass", "cititor");
        utilizatorRepository.save(utilizator);

        // Act
        Utilizator foundUtilizator = utilizatorRepository.findByUsernameAndPassword("testUser", "testPass");

        // Assert
        assertNotNull(foundUtilizator, "Utilizatorul nu a fost găsit!");
        assertEquals("testUser", foundUtilizator.getUsername());
        assertEquals("testPass", foundUtilizator.getPassword());
    }


    @Test
    void existsByUsername() {
        // Arrange
        Utilizator utilizator = new Utilizator("testUser", "testPass", "cititor");
        utilizatorRepository.save(utilizator);

        // Act
        boolean exists = utilizatorRepository.existsByUsername("testUser");

        // Assert
        assertTrue(exists, "Utilizatorul nu exista!");
    }

    @Test
    void findByUsername() {
        // Arrange
        Utilizator utilizator = new Utilizator("testUser", "testPass", "cititor");
        utilizatorRepository.save(utilizator);

        // Act
        Optional<Utilizator> foundUtilizator = Optional.ofNullable(utilizatorRepository.findByUsername("testUser"));

        // Assert
        assertTrue(foundUtilizator.isPresent(), "Utilizatorul nu a fost gasit!");
        assertEquals("testUser", foundUtilizator.get().getUsername());
    }

    @Test
    void deleteByUsername() {
        // Arrange
        Utilizator utilizator = new Utilizator("testUser", "testPass", "cititor");
        utilizatorRepository.save(utilizator);

        // Act
        utilizatorRepository.deleteByUsername("testUser");
        Optional<Utilizator> deletedUtilizator = Optional.ofNullable(utilizatorRepository.findByUsername("testUser"));

        // Assert
        assertFalse(deletedUtilizator.isPresent(), "Utilizatorul nu a fost sters!");
    }
}
