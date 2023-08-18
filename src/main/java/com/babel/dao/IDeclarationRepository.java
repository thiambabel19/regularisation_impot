package com.babel.dao;

import com.babel.entities.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeclarationRepository extends JpaRepository<Declaration, Long> {
}
