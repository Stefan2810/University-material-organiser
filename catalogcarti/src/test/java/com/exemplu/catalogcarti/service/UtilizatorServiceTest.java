/** Clasa Test pentru UtilizatorService
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.service;

import com.exemplu.catalogcarti.model.Utilizator;
import com.exemplu.catalogcarti.repository.UtilizatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilizatorServiceTest {

    @InjectMocks
    private UtilizatorService utilizatorService;

    @Mock
    private UtilizatorRepository utilizatorRepository;

    private Utilizator utilizator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        utilizator = new Utilizator();
        utilizator.setUsername("testUser");
        utilizator.setPassword("testPass");
        utilizator.setRol("cititor");
    }

    @Test
    void authenticateUser_Success() {
        when(utilizatorRepository.findByUsername("testUser")).thenReturn(utilizator);

        String result = utilizatorService.authenticateUser("testUser", "testPass", "cititor");

        assertEquals("cititor", result);
        verify(utilizatorRepository, times(1)).findByUsername("testUser");
    }

    @Test
    void authenticateUser_UserNotFound() {
        when(utilizatorRepository.findByUsername("nonExistentUser")).thenReturn(null);

        String result = utilizatorService.authenticateUser("nonExistentUser", "testPass", "cititor");

        assertEquals("Utilizator inexistent!", result);
        verify(utilizatorRepository, times(1)).findByUsername("nonExistentUser");
    }

    @Test
    void authenticateUser_WrongPassword() {
        when(utilizatorRepository.findByUsername("testUser")).thenReturn(utilizator);

        String result = utilizatorService.authenticateUser("testUser", "wrongPass", "cititor");

        assertEquals("Parola incorectă!", result);
        verify(utilizatorRepository, times(1)).findByUsername("testUser");
    }

    @Test
    void authenticateUser_WrongRole() {
        when(utilizatorRepository.findByUsername("testUser")).thenReturn(utilizator);

        String result = utilizatorService.authenticateUser("testUser", "testPass", "admin");

        assertEquals("Rol incorect!", result);
        verify(utilizatorRepository, times(1)).findByUsername("testUser");
    }

    @Test
    void createUser_Success() {
        when(utilizatorRepository.existsByUsername("newUser")).thenReturn(false);
        when(utilizatorRepository.save(any(Utilizator.class))).thenReturn(utilizator);

        Utilizator result = utilizatorService.createUser("newUser", "newPass", "cititor");

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals("testPass", result.getPassword());
        assertEquals("cititor", result.getRol());
        verify(utilizatorRepository, times(1)).existsByUsername("newUser");
        verify(utilizatorRepository, times(1)).save(any(Utilizator.class));
    }

    @Test
    void createUser_UserAlreadyExists() {
        when(utilizatorRepository.existsByUsername("existingUser")).thenReturn(true);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> utilizatorService.createUser("existingUser", "password", "cititor")
        );

        assertEquals("Utilizatorul există deja!", exception.getMessage());
        verify(utilizatorRepository, times(1)).existsByUsername("existingUser");
        verify(utilizatorRepository, never()).save(any(Utilizator.class));
    }
}
