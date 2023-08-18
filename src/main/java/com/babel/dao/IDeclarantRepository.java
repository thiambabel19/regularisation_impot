package com.babel.dao;

import com.babel.entities.Declarant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IDeclarantRepository extends JpaRepository<Declarant, Long> {
    @Query("SELECT d FROM Declarant d WHERE d.email = :x")
    public Declarant findByCode(@Param("x") String email);
}
