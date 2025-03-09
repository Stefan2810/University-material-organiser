/** Clasa Controller pentru elemente de tip CerereImprumut
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.controller;

import com.exemplu.catalogcarti.service.CerereImprumutService;
import com.exemplu.catalogcarti.model.CerereImprumut;
import com.exemplu.catalogcarti.repository.CerereImprumutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/api/cereri")
public class CerereImprumutController {

    @Autowired
    private CerereImprumutRepository cerereImprumutRepository;

    private static final List<String> STATUSE_VALIDE = List.of("in asteptare", "aprobat", "respingere");

    @PostMapping
    public ResponseEntity<CerereImprumut> trimiteCerere(@RequestBody CerereImprumut cerere) {
        cerere.setStatus("in asteptare");
        CerereImprumut cerereSalvata = cerereImprumutRepository.save(cerere);
        return ResponseEntity.ok(cerereSalvata);
    }

    @GetMapping
    public ResponseEntity<List<CerereImprumut>> getCereri() {
        List<CerereImprumut> cereri = cerereImprumutRepository.findByStatus("in asteptare");
        return ResponseEntity.ok(cereri);
    }

    @PutMapping("/{id}")
    public CerereImprumut modificaStatus(@PathVariable Long id, @RequestBody CerereImprumut cerere) {
        System.out.println("ID primit: " + id);
        System.out.println("Status primit: " + cerere.getStatus());
        CerereImprumut cerereExistentă = cerereImprumutRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cerere de împrumut inexistentă")
        );
        cerereExistentă.setStatus(cerere.getStatus());
        return cerereImprumutRepository.save(cerereExistentă);
    }
    @Autowired
    private CerereImprumutService cerereImprumutService;

    @GetMapping("/{username}")
    public ResponseEntity<List<CerereImprumut>> getCereriByUser(@PathVariable String username) {
        List<CerereImprumut> cereri = cerereImprumutService.getCereriByUsername(username);
        return ResponseEntity.ok(cereri);
    }
}
