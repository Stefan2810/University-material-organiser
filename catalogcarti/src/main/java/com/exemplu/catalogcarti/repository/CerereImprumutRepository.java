/** Clasa Repository pentru CerereImprumut
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.repository;

import com.exemplu.catalogcarti.model.CerereImprumut;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CerereImprumutRepository extends JpaRepository<CerereImprumut, Long> {

    List<CerereImprumut> findByStatus(String status);

    boolean existsByUsernameCititorAndTitluCarte(String username, String bookTitle);

    void deleteByUsernameCititorAndTitluCarte(String username, String bookTitle);

    List<CerereImprumut> findByUsernameCititor(String usernameCititor);
}
