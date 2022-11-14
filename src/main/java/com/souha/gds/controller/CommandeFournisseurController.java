package com.souha.gds.controller;

import com.souha.gds.controller.api.CommandeFournisseurApi;
import com.souha.gds.model.enumeration.EtatCommande;
import com.souha.gds.dto.CommandeFournisseurDto;
import com.souha.gds.dto.LigneCommandeFournisseurDto;
import com.souha.gds.service.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

    private final CommandeFournisseurService commandeFournisseurService;

    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto commandeFournisseurDto) {
        return ResponseEntity.ok(commandeFournisseurService.save(commandeFournisseurDto));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return ResponseEntity.ok(commandeFournisseurService.updateEtatCommande(idCommande, etatCommande));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateQuantite(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return ResponseEntity.ok(commandeFournisseurService.updateQuantite(idCommande, idLigneCommande, quantite));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateFournisseur(Integer idCommande, Integer idFournisseur) {
        return ResponseEntity.ok(commandeFournisseurService.updateFournisseur(idCommande, idFournisseur));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateArticle(Integer idCommande, Integer idLigneCommmande, Integer idArticle) {
        return ResponseEntity.ok(commandeFournisseurService.updateArticle(idCommande, idLigneCommmande, idArticle));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> deleteArticle(Integer idCommande, Integer idLigneCommmande) {
        return ResponseEntity.ok(commandeFournisseurService.deleteArticle(idCommande, idLigneCommmande));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findById(Integer id) {
        return ResponseEntity.ok(commandeFournisseurService.findById(id));
    }

    @Override
    public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
        return ResponseEntity.ok(commandeFournisseurService.findAll());
    }

    @Override
    public ResponseEntity<List<LigneCommandeFournisseurDto>> findAllLigneCommandeFournisseurByCommandeFournisseurId(Integer idCommande) {
        return ResponseEntity.ok(commandeFournisseurService.findAllLigneCommandeFournisseurByCommandeFournisseurId(idCommande));
    }

    @Override
    public ResponseEntity delete(Integer id) {
        commandeFournisseurService.delete(id);
        return ResponseEntity.ok().build();
    }
}
