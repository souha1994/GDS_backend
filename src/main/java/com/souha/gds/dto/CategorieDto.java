package com.souha.gds.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.souha.gds.model.Categorie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategorieDto {

    private Integer id;

    private String code;

    private String designation;

     private Integer idEntreprise;

    @JsonIgnore
    private List<ArticleDto> articles;

    public static CategorieDto fromEntity(Categorie categorie) {
        if (categorie == null) {
            //TODO throw an exception
            return null;
        }
        return CategorieDto.builder()
                .id(categorie.getId())
                .code(categorie.getCode())
                .designation(categorie.getDesignation())
                .idEntreprise(categorie.getIdEntreprise())
                .build();
    }
    public static Categorie toEntity(CategorieDto categorieDto) {
        if (categorieDto == null) {
            //TODO throw an exception
            return null;
        }
        return Categorie.builder()
                .id(categorieDto.getId())
                .code(categorieDto.getCode())
                .designation(categorieDto.getDesignation())
                .idEntreprise(categorieDto.getIdEntreprise())
                .build();
    }
}
