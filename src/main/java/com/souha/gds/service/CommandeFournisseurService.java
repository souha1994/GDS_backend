package com.souha.gds.service;

import com.souha.gds.model.enumeration.EtatCommande;
import com.souha.gds.dto.CommandeFournisseurDto;
import com.souha.gds.dto.LigneCommandeFournisseurDto;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto);
    CommandeFournisseurDto updateEtatCommande(Integer idCommande , EtatCommande etatCommande);
    CommandeFournisseurDto updateQuantite(Integer idCommande, Integer idLigneCommande , BigDecimal quantite);
    CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur);
    CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande,  Integer idArticle);
    CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande);

    CommandeFournisseurDto findById(Integer id);

    CommandeFournisseurDto findByCode(String code);

    List<CommandeFournisseurDto> findAll();
    List<LigneCommandeFournisseurDto> findAllLigneCommandeFournisseurByCommandeFournisseurId(Integer idCommande);

    void delete(Integer id);
}
