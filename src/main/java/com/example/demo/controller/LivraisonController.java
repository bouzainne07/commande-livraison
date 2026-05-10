package com.example.demo.controller;

import com.example.demo.entity.Livraison;
import com.example.demo.entity.StatutLivraison;
import com.example.demo.service.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/livraisons")
@CrossOrigin(origins = "*")
public class LivraisonController {

    @Autowired
    private LivraisonService livraisonService;

    @GetMapping
    public List<Livraison> getAllLivraisons() {
        return livraisonService.getAllLivraisons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livraison> getById(@PathVariable Long id) {
        return livraisonService.getLivraisonById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/commande/{commandeId}")
    public ResponseEntity<Livraison> getByCommande(@PathVariable Long commandeId) {
        return livraisonService.getLivraisonByCommande(commandeId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Livraison create(@RequestBody Livraison livraison) {
        return livraisonService.saveLivraison(livraison);
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<Livraison> updateStatut(
            @PathVariable Long id,
            @RequestParam StatutLivraison statut) {
        return ResponseEntity.ok(livraisonService.updateStatut(id, statut));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livraisonService.deleteLivraison(id);
        return ResponseEntity.ok().build();
    }
}