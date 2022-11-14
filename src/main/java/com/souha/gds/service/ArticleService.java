package com.souha.gds.service;

import com.souha.gds.dto.ArticleDto;
import com.souha.gds.dto.LigneCommandeClientDto;
import com.souha.gds.dto.LigneCommandeFournisseurDto;
import com.souha.gds.dto.LigneVenteDto;

import java.util.List;

public interface ArticleService {
    ArticleDto save(ArticleDto articleDto);
    ArticleDto findById(Integer id);
    ArticleDto findByCode(String code);
    List<ArticleDto> findAll();
    List<ArticleDto> findAllByCategorie(Integer idCategorie);
    List<LigneVenteDto> findHistoriqueVente(Integer idArticle);
    List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle);
    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);
    void delete(Integer id);
}
