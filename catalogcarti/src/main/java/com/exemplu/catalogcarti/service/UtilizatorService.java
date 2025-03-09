/** Clasa Service pentru obiecte de tip Utilizator
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.service;

import com.exemplu.catalogcarti.model.Utilizator;
import com.exemplu.catalogcarti.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilizatorService {

    @Autowired
    private UtilizatorRepository utilizatorRepository;

    public String authenticateUser(String username, String password, String rol) {
        Utilizator utilizator = utilizatorRepository.findByUsername(username);

        if (utilizator == null) {
            return "Utilizator inexistent!";
        }

        if (!utilizator.getPassword().equals(password.trim())) {
            return "Parola incorectă!";
        }

        if (!utilizator.getRol().equals(rol)) {
            return "Rol incorect!";
        }

        return utilizator.getRol();
    }

    public Utilizator createUser(String username, String password, String rol) {
        if (utilizatorRepository.existsByUsername(username)) {
            throw new RuntimeException("Utilizatorul există deja!");
        }

        Utilizator utilizator = new Utilizator(username, password, rol);

        return utilizatorRepository.save(utilizator);
    }

}