package com.souha.gds.service;

import com.souha.gds.dto.VenteDto;

import java.time.Instant;
import java.util.List;

public interface VenteService {
    VenteDto save(VenteDto VenteDto);

    VenteDto findById(Integer id);

    VenteDto findByCode(String code);

    VenteDto findByDateVente(Instant dateVente);

    List<VenteDto> findAll();

    void delete(Integer id);
}
