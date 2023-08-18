package com.babel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Declaration {
//    id long, dateDeclaration date, montantDeclaration  double, idDeclarant long
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String dateDeclaration;

    private double montantDeclaration;

    @ManyToOne
    @JoinColumn(name = "Declarant_ID")
    private Declarant declarant;

    @OneToMany
    private List<Paiement> paiements;
}
