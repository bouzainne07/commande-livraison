package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "paiements")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "commande_id", nullable = false)
    @JsonIgnoreProperties({"lignes", "paiement"})  // ← remplace @JsonIgnore
    private Commande commande;

    @Column(name = "date_paiement")
    private LocalDateTime datePaiement;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut = StatutPaiement.EN_ATTENTE;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_paiement")
    private ModePaiement mode;
}