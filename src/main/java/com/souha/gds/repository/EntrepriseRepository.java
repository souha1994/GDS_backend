package com.souha.gds.repository;

import com.souha.gds.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise,Integer> {


    Entreprise findByNom(String nom);
}
