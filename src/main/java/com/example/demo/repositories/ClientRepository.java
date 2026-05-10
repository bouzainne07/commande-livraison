package com.example.demo.repositories;

import com.example.demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository  // Dit à Spring : "cette interface gère la base de données"
public interface ClientRepository extends JpaRepository<Client, Long> {
    // JpaRepository<Client, Long> signifie :
    // - Client = la table qu'on gère
    // - Long = le type de l'id

    // Spring génère automatiquement : findAll(), findById(), save(), deleteById()

    // On ajoute juste cette méthode spéciale :
    Optional<Client> findByEmail(String email); // chercher un client par email
}