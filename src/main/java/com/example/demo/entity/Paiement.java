package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "paiements")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @Column(name = "date_paiement")
    private LocalDateTime datePaiement;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut = StatutPaiement.EN_ATTENTE;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_paiement")
    private ModePaiement mode;
}