package com.babel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String dateDeclaration;

    private double montantDeclaration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Declarant_ID")
    private Declarant declarant;

    @OneToMany(mappedBy = "declaration", cascade = CascadeType.ALL)
    private List<Paiement> paiements;
}
