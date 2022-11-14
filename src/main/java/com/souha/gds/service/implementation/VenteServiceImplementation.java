package com.souha.gds.service.implementation;

import com.souha.gds.dto.ArticleDto;
import com.souha.gds.dto.LigneVenteDto;
import com.souha.gds.dto.MvtStockDto;
import com.souha.gds.dto.VenteDto;
import com.souha.gds.exception.EntityNotFoundException;
import com.souha.gds.exception.ErrorCode;
import com.souha.gds.exception.InvalidEntityException;
import com.souha.gds.exception.InvalidOperationException;
import com.souha.gds.model.Article;
import com.souha.gds.model.LigneVente;
import com.souha.gds.model.Vente;
import com.souha.gds.model.enumeration.SourceMvtStock;
import com.souha.gds.model.enumeration.TypeMvt;
import com.souha.gds.repository.ArticleRepository;
import com.souha.gds.repository.LigneVenteRepository;
import com.souha.gds.repository.VenteRepository;
import com.souha.gds.service.MvtStockService;
import com.souha.gds.service.VenteService;
import com.souha.gds.validator.LigneVenteValidator;
import com.souha.gds.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("VenteServiceImplementation")
@Slf4j
public class VenteServiceImplementation implements VenteService {

    private final VenteRepository venteRepository;
    private final ArticleRepository articleRepository;
    private final LigneVenteRepository ligneVenteRepository;
    private final MvtStockService mvtStockService;

    @Autowired
    public VenteServiceImplementation(VenteRepository venteRepository, ArticleRepository articleRepository, LigneVenteRepository ligneVenteRepository, MvtStockService mvtStockService) {
        this.venteRepository = venteRepository;
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.mvtStockService = mvtStockService;
    }

    @Override
    public VenteDto save(VenteDto venteDto) {
        //Valider la vente
        List<String> errors = VenteValidator.validate(venteDto);
        if (!errors.isEmpty()) {
            log.error("La vente n'est pas valide");
            throw new InvalidEntityException("La vente n'est pas valide", ErrorCode.VENTE_NOT_VALIDE, errors);
        }

        //Valider les lignes de vente
        List<String> articleErrors = new ArrayList<>();
        venteDto.getLigneVente().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (!article.isPresent()) {
                articleErrors.add("Aucun article avec l'id " + ligneVenteDto.getArticle().getId() + "n'est présent dans la BDD");
            } else {
                articleErrors.addAll(LigneVenteValidator.validate(ligneVenteDto));
            }
        });
        if (!articleErrors.isEmpty()) {
            log.error("La liste des lignes articles n'est pas valide");
            throw new InvalidEntityException("La liste des lignes articles n'est pas valide", ErrorCode.ARTICLE_NOT_VALIDE, articleErrors);
        }

        Vente savedVente = venteRepository.save(VenteDto.toEntity(venteDto));
        venteDto.getLigneVente().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVente);
            ligneVenteRepository.save(ligneVente);
            updateMvtStock(ligneVente);

        });
        return VenteDto.fromEntity(savedVente);
    }

    @Override
    public VenteDto findById(Integer id) {
        if (id == null) {
            log.error("Vente ID est null");
            return null;
        }
        Optional<Vente> vente = venteRepository.findById(id);
        VenteDto venteDto = VenteDto.fromEntity(vente.get());
        return Optional.of(venteDto)
                .orElseThrow(() -> new EntityNotFoundException("Aucun vente avec l'id = " + id + " n'est pas présente dans la BDD", ErrorCode.ARTICLE_NOT_FOUND));
    }

    @Override
    public VenteDto findByCode(String code) {
        if (code == null) {
            log.error("Vente code est null");
            return null;
        }
        Optional<Vente> vente = venteRepository.findByCode(code);
        VenteDto venteDto = VenteDto.fromEntity(vente.get());
        return Optional.of(venteDto)
                .orElseThrow(() -> new EntityNotFoundException("Aucune vente avec le code = " + code + " n'est pas présente dans la BDD", ErrorCode.ARTICLE_NOT_FOUND));

    }

    @Override
    public VenteDto findByDateVente(Instant dateVente) {
        if (dateVente == null) {
            log.error("Vente dateVente est null");
            return null;
        }
        Optional<Vente> vente = venteRepository.findByDateVente(dateVente);
        VenteDto venteDto = VenteDto.fromEntity(vente.get());
        return Optional.of(venteDto)
                .orElseThrow(() -> new EntityNotFoundException("Aucune vente avec la date = " + dateVente + " n'est pas présente dans la BDD", ErrorCode.ARTICLE_NOT_FOUND));

    }

    @Override
    public List<VenteDto> findAll() {
        return venteRepository.findAll()
                .stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Impossible de supprimer la vente, l'id est null");
            return;
        }
        List<LigneVente> ligneVentes = ligneVenteRepository.findAllByVenteId(id);
        if (!ligneVentes.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer la vente, elle est utilisée dans des lignes de vente", ErrorCode.VENTE_ALREADY_IN_USE);
        }
            venteRepository.deleteById(id);
    }

    private void updateMvtStock(LigneVente ligneVente) {
       MvtStockDto sortieStock = MvtStockDto.builder()
                .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                .dateMvt(Instant.now())
                .typeMvt(TypeMvt.SORTIE)
                .sourceMvtStock(SourceMvtStock.VENTE)
                .quantite(ligneVente.getQuantite())
                .idEntreprise(ligneVente.getIdEntreprise())
                .build();
        mvtStockService.sortieStock(sortieStock);

    }
}
