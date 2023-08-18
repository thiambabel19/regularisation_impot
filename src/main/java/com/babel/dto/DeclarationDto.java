package com.babel.dto;

import com.babel.entities.Declarant;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeclarationDto {

    private Long id;

    @NotNull(message = "La date de declaration ne doit pas etre nulle")
    private String dateDeclaration;

    @NotNull(message = "Le montant de la declaration ne doit pas etre nul")
    private double montantDeclaration;

    @NotNull(message = "Le declarant ne doit pas etre nul")
    private Declarant declarant;
}
