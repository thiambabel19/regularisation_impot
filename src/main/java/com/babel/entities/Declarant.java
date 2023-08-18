package com.babel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Declarant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80)
    private String raisonSociale;

    @Column(length = 80)
    private String adresse;

    @Column(length = 80)
    private String email;

    @Column(length = 20)
    private String telephone;

}
