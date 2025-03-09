/** Clasa Repository pentru Utilizator
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.repository;

import com.exemplu.catalogcarti.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UtilizatorRepository extends JpaRepository<Utilizator, Long> {

    @Query("SELECT u FROM Utilizator u WHERE u.username = :username AND u.password = :password")
    Utilizator findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM Utilizator u WHERE u.username = :username")
    boolean existsByUsername(@Param("username") String username);

    @Query("SELECT u FROM Utilizator u WHERE u.username = :username")
    Utilizator findByUsername(@Param("username") String username);

    @Query("DELETE FROM Utilizator u WHERE u.username = :username")
    void deleteByUsername(@Param("username") String username);
}
