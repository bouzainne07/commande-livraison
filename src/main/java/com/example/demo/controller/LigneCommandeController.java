package com.example.demo.controller;

import com.example.demo.entity.Commande;
import com.example.demo.entity.LigneCommande;
import com.example.demo.repositories.CommandeRepository;
import com.example.demo.service.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lignes-commande")
@CrossOrigin(origins = "*")
public class LigneCommandeController {

    @Autowired
    private LigneCommandeService ligneCommandeService;

    @Autowired
    private CommandeRepository commandeRepository;

    @GetMapping("/commande/{commandeId}")
    public List<LigneCommande> getByCommande(@PathVariable Long commandeId) {
        return ligneCommandeService.getByCommande(commandeId);
    }

    @PostMapping
    public LigneCommande create(@RequestBody Map<String, Object> body) {
        LigneCommande ligne = new LigneCommande();

        // Récupère la commande depuis la base de données
        Long commandeId = Long.valueOf(
            ((Map<String, Object>) body.get("commande")).get("id").toString()
        );
        Commande commande = commandeRepository.findById(commandeId)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        ligne.setCommande(commande);
        ligne.setProduit(body.get("produit").toString());
        ligne.setQuantite(Integer.valueOf(body.get("quantite").toString()));
        ligne.setPrixUnitaire(Double.valueOf(body.get("prixUnitaire").toString()));

        return ligneCommandeService.saveLigne(ligne);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ligneCommandeService.deleteLigne(id);
    }
}