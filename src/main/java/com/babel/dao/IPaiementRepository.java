package com.babel.dao;

import com.babel.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {
    @Query("SELECT p FROM Paiement p WHERE p.declaration.id = :x")
    List<Paiement> findPaiementByDeclaration(@Param("x")Long id);
}
