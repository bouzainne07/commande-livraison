package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lignes_commande")
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cette ligne appartient à UNE commande
    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @Column(nullable = false)
    private String produit; // nom du produit

    @Column(nullable = false)
    private Integer quantite;

    @Column(name = "prix_unitaire", nullable = false)
    private Double prixUnitaire;
}