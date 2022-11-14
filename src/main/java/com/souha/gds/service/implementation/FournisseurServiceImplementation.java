package com.souha.gds.service.implementation;

import com.souha.gds.dto.FournisseurDto;
import com.souha.gds.exception.EntityNotFoundException;
import com.souha.gds.exception.ErrorCode;
import com.souha.gds.exception.InvalidEntityException;
import com.souha.gds.exception.InvalidOperationException;
import com.souha.gds.model.CommandeFournisseur;
import com.souha.gds.model.Fournisseur;
import com.souha.gds.repository.CommandeFournisseurRepository;
import com.souha.gds.repository.FournisseurRepository;
import com.souha.gds.service.FournisseurService;
import com.souha.gds.util.StaticUtil;
import com.souha.gds.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("FournisseurServiceImplementation")
@Slf4j
public class FournisseurServiceImplementation implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;
    private CommandeFournisseurRepository commandeFournisseurRepository;

    @Autowired
    public FournisseurServiceImplementation(FournisseurRepository fournisseurRepository, CommandeFournisseurRepository commandeFournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        List<String> errors = FournisseurValidator.validate(fournisseurDto);
        if (!errors.isEmpty()) {
            log.error("Le fournisseur est invalide");
            throw new InvalidEntityException("Le fournisseur est invalide", ErrorCode.CATEGORIE_NOT_VALIDE, errors);
        }
        return FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.toEntity(fournisseurDto)));
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if (id == null) {
            log.error("Le fournisseur avec l'id " + id + " n'est pas présent dans la BDD");
            return null;
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);
        return Optional.of(FournisseurDto.fromEntity(fournisseur.get()))
                .orElseThrow(() -> new EntityNotFoundException(StaticUtil.AUCUN_ELEMENT_TROUVE, ErrorCode.CLIENT_NOT_FOUND));
    }

    @Override
    public FournisseurDto findByMail(String mail) {
        if (mail == null) {
            log.error("Le fournisseur avec l'email " + mail + " n'est pas présent dans la BDD");
            return null;
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findByMail(mail);
        return Optional.of(FournisseurDto.fromEntity(fournisseur.get()))
                .orElseThrow(() -> new EntityNotFoundException(StaticUtil.AUCUN_ELEMENT_TROUVE, ErrorCode.CLIENT_NOT_FOUND));

    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll()
                .stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Impossible de supprimer le client, l'id est null");
            return;
        }
        List<CommandeFournisseur> commandeFournisseurs = commandeFournisseurRepository.findAllByFournisseurId(id);
        if (!commandeFournisseurs.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer le fournisseur, is est utilisé dans des commandes ", ErrorCode.FOURNISSEUR_ALREADY_IN_USE);
        }
        fournisseurRepository.deleteById(id);
    }
}
