package com.example.demo.controller;

import com.example.demo.entity.Commande;
import com.example.demo.entity.StatutCommande;
import com.example.demo.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@CrossOrigin(origins = "*")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        return commandeService.getCommandeById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // GET toutes les commandes d'un client
    @GetMapping("/client/{clientId}")
    public List<Commande> getCommandesByClient(@PathVariable Long clientId) {
        return commandeService.getCommandesByClient(clientId);
    }

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.saveCommande(commande);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande commande) {
        return commandeService.getCommandeById(id)
            .map(existing -> {
                commande.setId(id);
                return ResponseEntity.ok(commandeService.saveCommande(commande));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // PUT changer le statut d'une commande
    @PutMapping("/{id}/statut")
    public ResponseEntity<Commande> updateStatut(
            @PathVariable Long id,
            @RequestParam StatutCommande statut) {
        return ResponseEntity.ok(commandeService.updateStatut(id, statut));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.ok().build();
    }public CommandeController() {
		// TODO Auto-generated constructor stub
	}
    @PutMapping("/{id}/recalculer")
    public ResponseEntity<Commande> recalculer(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.recalculerMontant(id));
    }
}