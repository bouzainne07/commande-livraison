package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "lignes_commande")
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    @JsonIgnoreProperties({"lignes", "client"})
    private Commande commande;

    @NotBlank(message = "Le produit est obligatoire")
    @Column(nullable = false)
    private String produit;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 1, message = "La quantité doit être au moins 1")
    @Column(nullable = false)
    private Integer quantite;

    @NotNull(message = "Le prix unitaire est obligatoire")
    @Positive(message = "Le prix doit être positif")
    @Column(name = "prix_unitaire", nullable = false)
    private Double prixUnitaire;
}