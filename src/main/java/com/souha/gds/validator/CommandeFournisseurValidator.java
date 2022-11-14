package com.souha.gds.validator;

import com.souha.gds.dto.CommandeFournisseurDto;
import com.souha.gds.util.StaticUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {

    public static List<String> validate(CommandeFournisseurDto commandeFournisseurDto){
        List<String> errors = new ArrayList<>();

        if(commandeFournisseurDto!=null ){
            if(!StringUtils.hasLength(commandeFournisseurDto.getCode())){
                errors.add(StaticUtil.CODE_OBLIGATOIRE);
            }
            if(commandeFournisseurDto.getDateCommande()==null){
                errors.add(StaticUtil.DATE_COMMANDE_OBLIGATOIRE);
            }
            if(StringUtils.hasLength(commandeFournisseurDto.getEtatCommande().toString())){
                errors.add(StaticUtil.ETAT_COMMANDE_OBLIGATOIRE);
            }
            if(commandeFournisseurDto.getFournisseur()==null){
                errors.add(StaticUtil.FOURNISSEUR_OBLIGATOIRE);
            }
        }else{
            errors.add(StaticUtil.ENTITE_NULL);
        }

        return errors;
    }
}
