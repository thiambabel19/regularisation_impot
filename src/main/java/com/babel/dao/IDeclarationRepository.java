package com.babel.dao;

import com.babel.entities.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDeclarationRepository extends JpaRepository<Declaration, Long> {

    @Query("SELECT d FROM Declaration d LEFT JOIN d.paiements p WHERE p IS NULL OR p.montantPaiement != d.montantDeclaration")
    List<Declaration> getDeclarationNonReglee();
}
