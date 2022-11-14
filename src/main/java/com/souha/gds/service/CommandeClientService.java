package com.souha.gds.service;

import com.souha.gds.model.enumeration.EtatCommande;
import com.souha.gds.dto.CommandeClientDto;
import com.souha.gds.dto.LigneCommandeClientDto;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto commandeClientDto);
    CommandeClientDto updateEtatCommande(Integer idCommande , EtatCommande etatCommande);
    CommandeClientDto updateQuantite(Integer idCommande, Integer idLigneCommande , BigDecimal quantite);
    CommandeClientDto updateClient(Integer idCommande, Integer idClient);
    CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande,  Integer idArticle);
    CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande);

    CommandeClientDto findById(Integer id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll();
    List<LigneCommandeClientDto> findAllLigneCommandeClientByCommandeClientId(Integer idCommande);

    void delete(Integer id);
}
