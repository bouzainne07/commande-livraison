package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service  // Dit à Spring : "cette classe contient la logique métier"
public class ClientService {

    @Autowired  // Spring injecte automatiquement le repository (pas besoin de new)
    private ClientRepository clientRepository;

    // Récupérer tous les clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Récupérer un client par son id
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    // Créer ou modifier un client
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    // Supprimer un client
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}