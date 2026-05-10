package com.example.demo.service;

import com.example.demo.entity.Transporteur;
import com.example.demo.repositories.TransporteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TransporteurService {

    @Autowired
    private TransporteurRepository transporteurRepository;

    public List<Transporteur> getAllTransporteurs() {
        return transporteurRepository.findAll();
    }

    public Optional<Transporteur> getTransporteurById(Long id) {
        return transporteurRepository.findById(id);
    }

    public Transporteur saveTransporteur(Transporteur transporteur) {
        return transporteurRepository.save(transporteur);
    }

    public void deleteTransporteur(Long id) {
        transporteurRepository.deleteById(id);
    }
}