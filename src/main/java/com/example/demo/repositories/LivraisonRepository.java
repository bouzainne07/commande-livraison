package com.example.demo.repositories;

import com.example.demo.entity.Livraison;
import com.example.demo.entity.StatutLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, Long> {

    // Trouver la livraison d'une commande
    Optional<Livraison> findByCommandeId(Long commandeId);

    // Toutes les livraisons selon statut
    List<Livraison> findByStatut(StatutLivraison statut);
}