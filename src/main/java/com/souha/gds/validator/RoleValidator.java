package com.souha.gds.validator;

import com.souha.gds.dto.RoleDto;
import com.souha.gds.util.StaticUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleValidator {

    public static List<String> validate(RoleDto roleDto){
        List<String> errors = new ArrayList<>();

        if(roleDto!=null){
            if(!StringUtils.hasLength(roleDto.getRoleName())){
                errors.add(StaticUtil.NOM_OBLIGATOIRE);
            }
            if(roleDto.getUtilisateur()==null){
                errors.add(StaticUtil.UTILISATEUR_OBLIGATOIRE);
            }
        }else{
            errors.add(StaticUtil.ENTITE_NULL);
        }
        return errors;
    }
}
