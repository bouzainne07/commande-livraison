package com.example.demo.repositories;

import com.example.demo.entity.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {

    // Toutes les lignes d'une commande
    List<LigneCommande> findByCommandeId(Long commandeId);
}