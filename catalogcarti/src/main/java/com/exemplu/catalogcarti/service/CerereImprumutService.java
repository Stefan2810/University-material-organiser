/** Clasa Service pentru obiecte de tip CerereImprumut
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import com.exemplu.catalogcarti.model.CerereImprumut;
import com.exemplu.catalogcarti.repository.CerereImprumutRepository;

@Service
public class CerereImprumutService {

    private final CerereImprumutRepository cerereImprumutRepository;

    public CerereImprumutService(CerereImprumutRepository cerereImprumutRepository) {
        this.cerereImprumutRepository = cerereImprumutRepository;
    }

    @Transactional
    public ResponseEntity<String> actualizeazaStatusCerere(Long id, String status) {
        Optional<CerereImprumut> cerere = cerereImprumutRepository.findById(id);
        if (!cerere.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cererea nu există.");
        }
        if (!status.equals("Aprobat") && !status.equals("Respins")) {
            return ResponseEntity.badRequest().body("Status invalid.");
        }
        CerereImprumut cerereImprumut = cerere.get();
        cerereImprumut.setStatus(status);
        cerereImprumutRepository.save(cerereImprumut);
        return ResponseEntity.ok("Status actualizat.");
    }

    public List<CerereImprumut> getCereriByUsername(String username) {
        return cerereImprumutRepository.findByUsernameCititor(username);
    }
}
