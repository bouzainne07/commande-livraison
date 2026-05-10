package com.example.demo.controller;

import com.example.demo.entity.Paiement;
import com.example.demo.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@CrossOrigin(origins = "*")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    @GetMapping
    public List<Paiement> getAllPaiements() {
        return paiementService.getAllPaiements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> getById(@PathVariable Long id) {
        return paiementService.getPaiementById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Paiement create(@RequestBody Paiement paiement) {
        return paiementService.savePaiement(paiement);
    }

    // Valider un paiement
    @PutMapping("/{id}/valider")
    public ResponseEntity<Paiement> valider(@PathVariable Long id) {
        return ResponseEntity.ok(paiementService.validerPaiement(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paiementService.deletePaiement(id);
        return ResponseEntity.ok().build();
    }
}