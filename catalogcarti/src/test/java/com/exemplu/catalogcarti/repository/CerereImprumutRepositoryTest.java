/** Clasa Test pentru CerereImprumutRepository
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */
package com.exemplu.catalogcarti.repository;

import com.exemplu.catalogcarti.model.CerereImprumut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CerereImprumutRepositoryTest {

    @Autowired
    private CerereImprumutRepository cerereImprumutRepository;

    @Test
    void findByStatus() {
        // Arrange
        CerereImprumut cerere1 = new CerereImprumut("user1", "carte1", "aprobat");
        CerereImprumut cerere2 = new CerereImprumut("user2", "carte2", "in asteptare");
        cerereImprumutRepository.save(cerere1);
        cerereImprumutRepository.save(cerere2);

        // Act
        List<CerereImprumut> rezultate = cerereImprumutRepository.findByStatus("aprobat");

        // Assert
        assertEquals(1, rezultate.size());
        assertEquals("user1", rezultate.get(0).getUsernameCititor());
    }

    @Test
    void existsByUsernameCititorAndTitluCarte() {
        // Arrange
        CerereImprumut cerere = new CerereImprumut("user1", "carte1", "in asteptare");
        cerereImprumutRepository.save(cerere);

        // Act
        boolean exista = cerereImprumutRepository.existsByUsernameCititorAndTitluCarte("user1", "carte1");

        // Assert
        assertTrue(exista);
    }

    @Test
    void deleteByUsernameCititorAndTitluCarte() {
        // Arrange
        CerereImprumut cerere = new CerereImprumut("user1", "carte1", "in asteptare");
        cerereImprumutRepository.save(cerere);

        // Act
        cerereImprumutRepository.deleteByUsernameCititorAndTitluCarte("user1", "carte1");

        // Assert
        boolean exista = cerereImprumutRepository.existsByUsernameCititorAndTitluCarte("user1", "carte1");
        assertFalse(exista);
    }
}
