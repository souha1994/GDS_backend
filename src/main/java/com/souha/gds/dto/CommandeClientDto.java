package com.souha.gds.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.souha.gds.model.CommandeClient;
import com.souha.gds.model.enumeration.EtatCommande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommandeClientDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private ClientDto client;

     private Integer idEntreprise;


    @JsonIgnore
    private List<LigneCommandeClientDto> ligneCommandeClients;

    public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        if (commandeClient == null) {
            //TODO throw an exception
            return null;
        }
        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .etatCommande(commandeClient.getEtatCommande())
                .client(ClientDto.fromEntity(commandeClient.getClient()))
                .idEntreprise(commandeClient.getIdEntreprise())
                .build();
    }

    public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {
        if (commandeClientDto == null) {
            //TODO throw an exception
            return null;
        }
        return CommandeClient.builder()
                .id(commandeClientDto.getId())
                .code(commandeClientDto.getCode())
                .dateCommande(commandeClientDto.getDateCommande())
                .etatCommande(commandeClientDto.getEtatCommande())
                .client(ClientDto.toEntity(commandeClientDto.getClient()))
                .idEntreprise(commandeClientDto.getIdEntreprise())
                .build();
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.getEtatCommande());
    }
}
