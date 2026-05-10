package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "commandes")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation : plusieurs commandes peuvent appartenir à UN client
    // @ManyToOne = "plusieurs commandes → un client"
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false) // crée une colonne "client_id" dans la table
    private Client client;

    @Column(name = "date_commande")
    private LocalDateTime dateCommande = LocalDateTime.now(); // date automatique à la création

    // Statut : EN_ATTENTE, VALIDEE, LIVREE, ANNULEE
    @Enumerated(EnumType.STRING) // stocke le texte "EN_ATTENTE" et non un chiffre
    private StatutCommande statut = StatutCommande.EN_ATTENTE;

    @Column(name = "montant_total")
    private Double montantTotal = 0.0;

    // Une commande a PLUSIEURS lignes de commande
    // @OneToMany = "une commande → plusieurs lignes"
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LigneCommande> lignes;
}