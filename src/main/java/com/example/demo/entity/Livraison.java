package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Data
@Entity
@Table(name = "livraisons")
public class Livraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

 // Une livraison est liée à UNE commande
    @OneToOne
    @JoinColumn(name = "commande_id", nullable = false)
    @JsonIgnoreProperties({"lignes", "livraison"})
    private Commande commande;

    // Une livraison est assurée par UN transporteur
    @ManyToOne
    @JoinColumn(name = "transporteur_id")
    private Transporteur transporteur;

    @Column(name = "date_livraison")
    private LocalDateTime dateLivraison;

    private Double cout;

    @Enumerated(EnumType.STRING)
    private StatutLivraison statut = StatutLivraison.EN_ATTENTE;
}