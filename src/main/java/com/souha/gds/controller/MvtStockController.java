package com.souha.gds.controller;

import com.souha.gds.controller.api.MvtStockApi;
import com.souha.gds.dto.MvtStockDto;
import com.souha.gds.service.MvtStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MvtStockController implements MvtStockApi {

    private MvtStockService mvtStockService;

    @Autowired
    public MvtStockController(MvtStockService mvtStockService) {
        this.mvtStockService = mvtStockService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        return mvtStockService.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStockDto> findAllByArticleId(Integer idArticle) {
        return mvtStockService.findAllByArticleId(idArticle);
    }

    @Override
    public MvtStockDto entreeStock(MvtStockDto mvtStockDto) {
        return mvtStockService.entreeStock(mvtStockDto);
    }

    @Override
    public MvtStockDto sortieStock(MvtStockDto mvtStockDto) {
        return mvtStockService.sortieStock(mvtStockDto);
    }

    @Override
    public MvtStockDto correctionStockPositive(MvtStockDto mvtStockDto) {
        return mvtStockService.correctionStockPositive(mvtStockDto);
    }

    @Override
    public MvtStockDto correctionStockNegative(MvtStockDto mvtStockDto) {
        return mvtStockService.correctionStockNegative(mvtStockDto);
    }
}
