package com.souha.gds.controller.api;

import com.souha.gds.dto.ArticleDto;
import com.souha.gds.dto.LigneCommandeClientDto;
import com.souha.gds.dto.LigneCommandeFournisseurDto;
import com.souha.gds.dto.LigneVenteDto;
import com.souha.gds.util.StaticRoot;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(StaticRoot.APP_ROOT + "/articles")
public interface ArticleApi {

    @PostMapping(value = StaticRoot.APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un article (Ajouter / Modifier)", notes = "Cette méthode permet d'ajouter et de modifier un article", response = ArticleDto.class)
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "L'objet article créé / modifié")
    })
    ArticleDto save(@RequestBody ArticleDto articleDto);

    @GetMapping(value = StaticRoot.APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article", notes = "Cette méthode permet de recherhcer un article par son ID ", response = ArticleDto.class)
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "L'objet article est trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "L'objet article n'est pas trouvé dans la BDD")
    })
    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = StaticRoot.APP_ROOT + "/articles/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article avec son code",notes = "Cette méthode permet de rechercher un article par son code",response = ArticleDto.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "L'article est trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "L'article n'est pas trouvé dans la BDD")
    })
    ArticleDto findByCode(@PathVariable("codeArticle") String code);

    @GetMapping(value = StaticRoot.APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Charger la liste des articles", notes = "Cette méthode permet de renvoyer la liste des articles", responseContainer = "List<ArticleDto>")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Les articles sont trouvés dans la BDD"),
            @ApiResponse(code = 404, message = "Les articles ne sont pas trouvés dans la BDD")
    })
    List<ArticleDto> findAll();

    @DeleteMapping(value = StaticRoot.APP_ROOT + "/articles/delete/{idArticle}")
    @ApiOperation(value = "Supprimer un article", notes = "Cette méthode permet de supprimer un article par son ID ")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "L'objet article est supprimé de la BDD"),
            @ApiResponse(code = 404, message = "L'objet article n'est pas supprimé de la BDD")
    })
    void delete(@PathVariable("idArticle") Integer id);
    @GetMapping(value = StaticRoot.APP_ROOT + "/articles/filter/categorie/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)

    List<ArticleDto> findAllByCategorie(@PathVariable("idArticle")Integer idCategorie);
    @GetMapping(value = StaticRoot.APP_ROOT + "/articles/historique/vente/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)

    List<LigneVenteDto> findHistoriqueVente(@PathVariable("idArticle")Integer idArticle);
    @GetMapping(value = StaticRoot.APP_ROOT + "/articles/historique/commandeClient/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)

    List<LigneCommandeClientDto> findHistoriqueCommandeClient(@PathVariable("idArticle")Integer idArticle);
    @GetMapping(value = StaticRoot.APP_ROOT + "/articles/historique/commandeFournisseur/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)

    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(@PathVariable("idArticle")Integer idArticle);
}
