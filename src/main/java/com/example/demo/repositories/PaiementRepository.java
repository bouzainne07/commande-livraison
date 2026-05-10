package com.example.demo.repositories;

import com.example.demo.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    Optional<Paiement> findByCommandeId(Long commandeId);
}