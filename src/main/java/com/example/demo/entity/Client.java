package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 50, message = "Le nom doit avoir entre 2 et 50 caractères")
    @Column(nullable = false)
    private String nom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    @Column(nullable = false, unique = true)
    private String email;

    private String adresse;

    @Pattern(regexp = "^[0-9]{8}$", message = "Le téléphone doit avoir 8 chiffres")
    @Column(name = "telephone")
    private String telephone;
}