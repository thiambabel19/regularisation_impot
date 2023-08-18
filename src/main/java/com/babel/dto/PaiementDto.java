package com.babel.dto;

import com.babel.entities.Declaration;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaiementDto {

    private Long id;

    @NotNull(message = "La date du paiement ne doit pas etre nulle")
    private String datePaiement;

    @NotNull(message = "Le montant du paiement ne doit pas etre nul")
    private double montantPaiement;

    @NotNull(message = "La declaration ne doit pas etre nulle")
    private Declaration declaration;
}
