package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "commandes")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le client est obligatoire")
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnoreProperties("commandes")
    private Client client;

    @Column(name = "date_commande")
    private LocalDateTime dateCommande = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatutCommande statut = StatutCommande.EN_ATTENTE;

    @PositiveOrZero(message = "Le montant ne peut pas être négatif")
    @Column(name = "montant_total")
    private Double montantTotal = 0.0;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("commande")
    private List<LigneCommande> lignes;
}