package com.souha.gds.repository;

import com.souha.gds.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CategorieRepository extends JpaRepository<Categorie,Integer> {

    Categorie findByDesignation(String designation);

    Categorie findByCode(String code);
}
