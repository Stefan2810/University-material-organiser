/** Clasa care se ocupa cu pornitul aplicatiei, populeaza baza de date daca este empty
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti;

import com.exemplu.catalogcarti.model.Utilizator;
import com.exemplu.catalogcarti.model.Carte;
import com.exemplu.catalogcarti.model.CerereImprumut;
import com.exemplu.catalogcarti.repository.CerereImprumutRepository;
import com.exemplu.catalogcarti.repository.UtilizatorRepository;
import com.exemplu.catalogcarti.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogCartiApplication implements CommandLineRunner {

    @Autowired
    private UtilizatorRepository utilizatorRepository;

    @Autowired
    private CarteRepository carteRepository;

    public static void main(String[] args) {
        SpringApplication.run(CatalogCartiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // Populam baza de date daca aceasta este goala
        if (!utilizatorRepository.existsByUsername("admin1")) {
            utilizatorRepository.save(new Utilizator("admin1", "parola123", "admin"));
        }
        if (!utilizatorRepository.existsByUsername("user1")) {
            utilizatorRepository.save(new Utilizator("user1", "parola789", "cititor"));
        }

        if (!carteRepository.existsByTitluAndAutor("Introducere in Java", "Ion Popescu")) {
            carteRepository.save(new Carte("Introducere in Java", "Ion Popescu", "Programare", 2021));
        }
        if (!carteRepository.existsByTitluAndAutor("Structuri de date", "Maria Ionescu")) {
            carteRepository.save(new Carte("Structuri de date", "Maria Ionescu", "Informatică", 2020));
        }

        if (!carteRepository.existsByTitluAndAutor("Algoritmi avansați", "George Vasilescu")) {
            carteRepository.save(new Carte("Algoritmi avansați", "George Vasilescu", "Algoritmi", 2022));
        }
        if (!carteRepository.existsByTitluAndAutor("Design Patterns", "Cristian Tudor")) {
            carteRepository.save(new Carte("Design Patterns", "Cristian Tudor", "Programare", 2019));
        }
    }

}
