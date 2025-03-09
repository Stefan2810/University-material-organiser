/** Clasa Repository pentru Carte
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.repository;

import com.exemplu.catalogcarti.model.Carte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteRepository extends JpaRepository<Carte, Long> {

    boolean existsByTitluAndAutor(String titlu, String autor);

    void deleteByTitluAndAutor(String titlu, String autor);
}
