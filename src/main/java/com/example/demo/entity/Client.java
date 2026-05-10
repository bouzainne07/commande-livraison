package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data               // Lombok : génère automatiquement les getters, setters, toString
@Entity             // Dit à Spring : "cette classe = une table dans la base de données"
@Table(name = "clients")  // Le nom de la table dans MySQL sera "clients"
public class Client {

    @Id                                        // C'est la clé primaire (l'identifiant unique)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // L'id s'incrémente automatiquement (1, 2, 3...)
    private Long id;

    @Column(nullable = false)                  // Ce champ est obligatoire (ne peut pas être vide)
    private String nom;

    @Column(nullable = false, unique = true)   // Email obligatoire ET unique (pas deux fois le même)
    private String email;

    private String adresse;

    @Column(name = "telephone")
    private String telephone;
}