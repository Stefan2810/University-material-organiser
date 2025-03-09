/** Clasa Test pentru CatalogCartiApplication
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti;

import com.exemplu.catalogcarti.model.Carte;
import com.exemplu.catalogcarti.model.Utilizator;
import com.exemplu.catalogcarti.repository.CarteRepository;
import com.exemplu.catalogcarti.repository.UtilizatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class CatalogCartiApplicationTest {

    @InjectMocks
    private CatalogCartiApplication catalogCartiApplication;

    @Mock
    private UtilizatorRepository utilizatorRepository;

    @Mock
    private CarteRepository carteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_ShouldPopulateDatabase_WhenDataIsMissing() throws Exception {
        // Simuleaza comportamentul repository-ului pentru utilizatori
        when(utilizatorRepository.existsByUsername("admin1")).thenReturn(false);
        when(utilizatorRepository.existsByUsername("user1")).thenReturn(false);

        // Simuleaza comportamentul repository-ului pentru carti
        when(carteRepository.existsByTitluAndAutor("Introducere in Java", "Ion Popescu")).thenReturn(false);
        when(carteRepository.existsByTitluAndAutor("Structuri de date", "Maria Ionescu")).thenReturn(false);
        when(carteRepository.existsByTitluAndAutor("Algoritmi avansati", "George Vasilescu")).thenReturn(false);
        when(carteRepository.existsByTitluAndAutor("Design Patterns", "Cristian Tudor")).thenReturn(false);

        // Executa metoda care trebuie testata
        catalogCartiApplication.run();

        // Verifica apelurile catre repository-ul de utilizatori
        verify(utilizatorRepository, times(1)).existsByUsername("admin1");
        verify(utilizatorRepository, times(1)).existsByUsername("user1");
        verify(utilizatorRepository, times(1)).save(new Utilizator("admin1", "parola123", "admin"));
        verify(utilizatorRepository, times(1)).save(new Utilizator("user1", "parola789", "cititor"));

        // Verifica apelurile catre repository-ul de carti
        verify(carteRepository, times(1)).existsByTitluAndAutor("Introducere in Java", "Ion Popescu");
        verify(carteRepository, times(1)).existsByTitluAndAutor("Structuri de date", "Maria Ionescu");
        verify(carteRepository, times(1)).existsByTitluAndAutor("Algoritmi avansati", "George Vasilescu");
        verify(carteRepository, times(1)).existsByTitluAndAutor("Design Patterns", "Cristian Tudor");

        verify(carteRepository, times(1)).save(new Carte("Introducere in Java", "Ion Popescu", "Programare", 2021));
        verify(carteRepository, times(1)).save(new Carte("Structuri de date", "Maria Ionescu", "Informatica", 2020));
        verify(carteRepository, times(1)).save(new Carte("Algoritmi avansati", "George Vasilescu", "Algoritmi", 2022));
        verify(carteRepository, times(1)).save(new Carte("Design Patterns", "Cristian Tudor", "Programare", 2019));
    }

    @Test
    void run_ShouldNotPopulateDatabase_WhenDataExists() throws Exception {
        // Simuleaza comportamentul repository-ului pentru utilizatori
        when(utilizatorRepository.existsByUsername("admin1")).thenReturn(true);
        when(utilizatorRepository.existsByUsername("user1")).thenReturn(true);

        // Simuleaza comportamentul repository-ului pentru carti
        when(carteRepository.existsByTitluAndAutor("Introducere in Java", "Ion Popescu")).thenReturn(true);
        when(carteRepository.existsByTitluAndAutor("Structuri de date", "Maria Ionescu")).thenReturn(true);
        when(carteRepository.existsByTitluAndAutor("Algoritmi avansati", "George Vasilescu")).thenReturn(true);
        when(carteRepository.existsByTitluAndAutor("Design Patterns", "Cristian Tudor")).thenReturn(true);

        // Executa metoda care trebuie testata
        catalogCartiApplication.run();

        // Verifica apelurile catre repository-ul de utilizatori
        verify(utilizatorRepository, times(1)).existsByUsername("admin1");
        verify(utilizatorRepository, times(1)).existsByUsername("user1");
        verify(utilizatorRepository, never()).save(any(Utilizator.class));

        // Verifica apelurile catre repository-ul de carti
        verify(carteRepository, times(1)).existsByTitluAndAutor("Introducere in Java", "Ion Popescu");
        verify(carteRepository, times(1)).existsByTitluAndAutor("Structuri de date", "Maria Ionescu");
        verify(carteRepository, times(1)).existsByTitluAndAutor("Algoritmi avansati", "George Vasilescu");
        verify(carteRepository, times(1)).existsByTitluAndAutor("Design Patterns", "Cristian Tudor");

        verify(carteRepository, never()).save(any(Carte.class));
    }
}
