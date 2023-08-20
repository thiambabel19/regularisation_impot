package com.babel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String datePaiement;

    private double montantPaiement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Declaration_ID")
    private Declaration declaration;
}
