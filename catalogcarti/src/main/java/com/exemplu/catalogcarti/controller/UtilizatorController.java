/** Clasa Controller pentru Utilizator
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.controller;

import com.exemplu.catalogcarti.model.Utilizator;
import com.exemplu.catalogcarti.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilizatorController {

    @Autowired
    private UtilizatorRepository utilizatorRepository;

    @PostMapping("/api/utilizatori")
    public ResponseEntity<String> login(@RequestBody Utilizator loginRequest) {
        try {
            Utilizator utilizator = utilizatorRepository.findByUsername(loginRequest.getUsername());
            if (utilizator == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilizator inexistent!");
            }

            if (!utilizator.getPassword().equals(loginRequest.getPassword().trim())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Parola incorectă!");
            }

            return ResponseEntity.ok(utilizator.getRol());  // Returnează doar rolul utilizatorului

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eroare la procesarea cererii!");
        }
    }

    @PostMapping("/api/create")
    public ResponseEntity<String> createUser(@RequestBody Utilizator newUser) {
        try {
            if (utilizatorRepository.existsByUsername(newUser.getUsername())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Utilizatorul există deja!");
            }

            newUser.setRol("cititor");

            utilizatorRepository.save(newUser);

            return ResponseEntity.status(HttpStatus.CREATED).body("Utilizator creat cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eroare la crearea contului!");
        }
    }

}