package com.example.demo.service;

import com.example.demo.entity.LigneCommande;
import com.example.demo.repositories.LigneCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LigneCommandeService {

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    public List<LigneCommande> getByCommande(Long commandeId) {
        return ligneCommandeRepository.findByCommandeId(commandeId);
    }

    public LigneCommande saveLigne(LigneCommande ligne) {
        return ligneCommandeRepository.save(ligne);
    }

    public void deleteLigne(Long id) {
        ligneCommandeRepository.deleteById(id);
    }
}