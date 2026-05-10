package com.example.demo.service;

import com.example.demo.entity.Livraison;
import com.example.demo.entity.StatutLivraison;
import com.example.demo.repositories.LivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;

    public List<Livraison> getAllLivraisons() {
        return livraisonRepository.findAll();
    }

    public Optional<Livraison> getLivraisonById(Long id) {
        return livraisonRepository.findById(id);
    }

    public Optional<Livraison> getLivraisonByCommande(Long commandeId) {
        return livraisonRepository.findByCommandeId(commandeId);
    }

    public Livraison saveLivraison(Livraison livraison) {
        return livraisonRepository.save(livraison);
    }

    // Mettre à jour le statut de livraison
    public Livraison updateStatut(Long id, StatutLivraison statut) {
        Livraison livraison = livraisonRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Livraison non trouvée"));
        livraison.setStatut(statut);
        return livraisonRepository.save(livraison);
    }

    public void deleteLivraison(Long id) {
        livraisonRepository.deleteById(id);
    }
}