package com.example.demo.repositories;

import com.example.demo.entity.Commande;
import com.example.demo.entity.StatutCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    // Toutes les commandes d'un client
    List<Commande> findByClientId(Long clientId);

    // Toutes les commandes selon un statut (ex: EN_ATTENTE)
    List<Commande> findByStatut(StatutCommande statut);
}