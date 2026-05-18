package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "transporteurs")
public class Transporteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(nullable = false)
    private String nom;

    @Pattern(regexp = "^[0-9]{8}$", message = "Le téléphone doit avoir 8 chiffres")
    private String telephone;

    @Min(value = 0, message = "La note minimum est 0")
    @Max(value = 5, message = "La note maximum est 5")
    private Double note;
}