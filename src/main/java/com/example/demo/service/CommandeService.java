package com.example.demo.service;

import com.example.demo.entity.Commande;
import com.example.demo.entity.StatutCommande;
import com.example.demo.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }

    // Toutes les commandes d'un client
    public List<Commande> getCommandesByClient(Long clientId) {
        return commandeRepository.findByClientId(clientId);
    }

    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    // Changer le statut d'une commande
    public Commande updateStatut(Long id, StatutCommande statut) {
        Commande commande = commandeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        commande.setStatut(statut);
        return commandeRepository.save(commande);
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
}