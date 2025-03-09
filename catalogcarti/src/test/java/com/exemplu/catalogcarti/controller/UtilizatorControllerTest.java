/** Clasa Test pentru UtilizatorController
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.controller;

import com.exemplu.catalogcarti.model.Utilizator;
import com.exemplu.catalogcarti.repository.UtilizatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UtilizatorControllerTest {

    @InjectMocks
    private UtilizatorController utilizatorController;

    @Mock
    private UtilizatorRepository utilizatorRepository;

    private Utilizator utilizator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        utilizator = new Utilizator();
        utilizator.setId(1L);
        utilizator.setUsername("testUser");
        utilizator.setPassword("testPass");
        utilizator.setRol("cititor");
    }

    @Test
    void login_Success() {
        // Arrange
        when(utilizatorRepository.findByUsername("testUser")).thenReturn(utilizator);

        Utilizator loginRequest = new Utilizator();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPass");

        // Act
        ResponseEntity<String> response = utilizatorController.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("cititor", response.getBody().trim());
        verify(utilizatorRepository, times(1)).findByUsername("testUser");
    }

    @Test
    void login_UserNotFound() {
        // Arrange
        when(utilizatorRepository.findByUsername("testUser")).thenReturn(null);

        Utilizator loginRequest = new Utilizator();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPass");

        // Act
        ResponseEntity<String> response = utilizatorController.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
        verify(utilizatorRepository, times(1)).findByUsername("testUser");
    }

    @Test
    void login_WrongPassword() {
        // Arrange
        when(utilizatorRepository.findByUsername("testUser")).thenReturn(utilizator);

        Utilizator loginRequest = new Utilizator();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("wrongPass");

        // Act
        ResponseEntity<String> response = utilizatorController.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
        verify(utilizatorRepository, times(1)).findByUsername("testUser");
    }

    @Test
    void createUser_Success() {
        // Arrange
        when(utilizatorRepository.existsByUsername("newUser")).thenReturn(false);
        when(utilizatorRepository.save(any(Utilizator.class))).thenReturn(utilizator);

        Utilizator newUser = new Utilizator();
        newUser.setUsername("newUser");
        newUser.setPassword("newPass");

        // Act
        ResponseEntity<String> response = utilizatorController.createUser(newUser);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Utilizator creat cu succes!", response.getBody());
        verify(utilizatorRepository, times(1)).existsByUsername("newUser");
        verify(utilizatorRepository, times(1)).save(any(Utilizator.class));
    }

    @Test
    void createUser_UserAlreadyExists() {
        // Arrange
        when(utilizatorRepository.existsByUsername("existingUser")).thenReturn(true);

        Utilizator newUser = new Utilizator();
        newUser.setUsername("existingUser");
        newUser.setPassword("newPass");

        // Act
        ResponseEntity<String> response = utilizatorController.createUser(newUser);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Utilizatorul exista deja!", response.getBody());
        verify(utilizatorRepository, times(1)).existsByUsername("existingUser");
        verify(utilizatorRepository, never()).save(any(Utilizator.class));
    }
}
