package com.souha.gds.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.souha.gds.dto.UtilisateurDto;
import com.souha.gds.exception.ErrorCode;
import com.souha.gds.exception.InvalidOperationException;
import com.souha.gds.service.UtilisateurService;
import com.souha.gds.service.FlickrPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("utilisateurStrategy")
@Slf4j
public class SaveUtilisateurPhoto implements Strategy<UtilisateurDto> {

    private FlickrPhotoService flickrPhotoService;
    private UtilisateurService utilisateurService;

    @Autowired
    public SaveUtilisateurPhoto(FlickrPhotoService flickrPhotoService, UtilisateurService utilisateurService) {
        this.flickrPhotoService = flickrPhotoService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto savePhoto(Integer id,InputStream photo, String titre) throws FlickrException {
        UtilisateurDto utilisateurDto = utilisateurService.findById(id);
        final String urlPhoto = flickrPhotoService.savePhoto(photo, titre);
        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("URL de la photo est invalide", ErrorCode.URL_PHOTO_INVALIDE);
        }
        utilisateurDto.setPhoto(urlPhoto);
        return utilisateurService.save(utilisateurDto);
    }
}
