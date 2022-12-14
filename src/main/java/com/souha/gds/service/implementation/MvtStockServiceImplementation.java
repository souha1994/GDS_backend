package com.souha.gds.service.implementation;

import com.souha.gds.dto.MvtStockDto;
import com.souha.gds.exception.ErrorCode;
import com.souha.gds.exception.InvalidEntityException;
import com.souha.gds.model.enumeration.TypeMvt;
import com.souha.gds.repository.MvtStockRepository;
import com.souha.gds.service.ArticleService;
import com.souha.gds.service.MvtStockService;
import com.souha.gds.validator.MvtStockValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service("MvtStockServiceImplementation")
@Slf4j
public class MvtStockServiceImplementation implements MvtStockService {

    private final MvtStockRepository mvtStockRepository;
    private final ArticleService articleService;

    @Autowired
    public MvtStockServiceImplementation(MvtStockRepository mvtStockRepository, ArticleService articleService) {
        this.mvtStockRepository = mvtStockRepository;
        this.articleService = articleService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        if (idArticle == null) {
            log.warn("ID article est null");
            return BigDecimal.valueOf(-1);
        }
        articleService.findById(idArticle);
        return mvtStockRepository.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStockDto> findAllByArticleId(Integer idArticle) {
        return mvtStockRepository.findAllByArticleId(idArticle)
                .stream()
                .map(MvtStockDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MvtStockDto entreeStock(MvtStockDto mvtStockDto) {
        saveMouvementStock(mvtStockDto, TypeMvt.ENTREE);
        mvtStockDto.setQuantite(BigDecimal.valueOf(Math.abs(mvtStockDto.getQuantite().doubleValue())));
        return MvtStockDto.fromEntity(mvtStockRepository.save(MvtStockDto.toEntity(mvtStockDto)));
    }


    @Override
    public MvtStockDto sortieStock(MvtStockDto mvtStockDto) {
        saveMouvementStock(mvtStockDto, TypeMvt.SORTIE);
        mvtStockDto.setQuantite(BigDecimal.valueOf(Math.abs(mvtStockDto.getQuantite().doubleValue()) * -1));
        return MvtStockDto.fromEntity(mvtStockRepository.save(MvtStockDto.toEntity(mvtStockDto)));
    }

    @Override
    public MvtStockDto correctionStockPositive(MvtStockDto mvtStockDto) {
        saveMouvementStock(mvtStockDto, TypeMvt.CORRECTION_POSITIVE);
        mvtStockDto.setQuantite(BigDecimal.valueOf(Math.abs(mvtStockDto.getQuantite().doubleValue())));
        return MvtStockDto.fromEntity(mvtStockRepository.save(MvtStockDto.toEntity(mvtStockDto)));
    }

    @Override
    public MvtStockDto correctionStockNegative(MvtStockDto mvtStockDto) {
        saveMouvementStock(mvtStockDto, TypeMvt.CORRECTION_NEGATIVE);
        mvtStockDto.setQuantite(BigDecimal.valueOf(Math.abs(mvtStockDto.getQuantite().doubleValue()) * -1));
        return MvtStockDto.fromEntity(mvtStockRepository.save(MvtStockDto.toEntity(mvtStockDto)));
    }

    private void saveMouvementStock(MvtStockDto mvtStockDto, TypeMvt typeMvt) {
        List<String> errors = MvtStockValidator.validate(mvtStockDto);
        if (!errors.isEmpty()) {
            log.error("Le  mouvement du stock est invalide {}" + mvtStockDto);
            throw new InvalidEntityException("Le mouvement du stock est invalide", ErrorCode.MVT_NOT_VALIDE, errors);
        }
        mvtStockDto.setTypeMvt(typeMvt);
    }
}
