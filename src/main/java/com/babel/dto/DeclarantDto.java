package com.babel.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeclarantDto {

    private Long id;

    @NotNull(message = "La raison sociale ne doit pas etre nulle")
    private String raisonSociale;

    @NotNull(message = "L'adresse ne doit pas etre nulle")
    private String adresse;

    @NotNull(message = "L'email ne doit pas etre nulle")
    private String email;

    @NotNull(message = "Le telephone ne doit pas etre nul")
    private String telephone;

}
