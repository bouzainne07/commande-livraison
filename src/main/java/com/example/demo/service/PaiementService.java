package com.example.demo.service;

import com.example.demo.entity.Paiement;
import com.example.demo.entity.StatutPaiement;
import com.example.demo.repositories.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    public Optional<Paiement> getPaiementById(Long id) {
        return paiementRepository.findById(id);
    }

    public Paiement savePaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    // Valider un paiement
    public Paiement validerPaiement(Long id) {
        Paiement paiement = paiementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Paiement non trouvé"));
        paiement.setStatut(StatutPaiement.PAYE);
        paiement.setDatePaiement(LocalDateTime.now());
        return paiementRepository.save(paiement);
    }

    public void deletePaiement(Long id) {
        paiementRepository.deleteById(id);
    }
}