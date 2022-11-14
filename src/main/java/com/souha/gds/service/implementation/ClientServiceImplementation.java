package com.souha.gds.service.implementation;

import com.souha.gds.dto.ClientDto;
import com.souha.gds.exception.EntityNotFoundException;
import com.souha.gds.exception.ErrorCode;
import com.souha.gds.exception.InvalidEntityException;
import com.souha.gds.exception.InvalidOperationException;
import com.souha.gds.model.Client;
import com.souha.gds.model.CommandeClient;
import com.souha.gds.repository.ClientRepository;
import com.souha.gds.repository.CommandeClientRepository;
import com.souha.gds.service.ClientService;
import com.souha.gds.util.StaticUtil;
import com.souha.gds.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ClientServiceImplementation")
@Slf4j
public class ClientServiceImplementation implements ClientService {

    private final ClientRepository clientRepository;
    private CommandeClientRepository commandeClientRepository;


    @Autowired
    public ClientServiceImplementation(ClientRepository clientRepository, CommandeClientRepository commandeClientRepository) {
        this.clientRepository = clientRepository;
        this.commandeClientRepository = commandeClientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String> errors = ClientValidator.validate(clientDto);
        if (!errors.isEmpty()) {
            log.error("Le client est invalide");
            throw new InvalidEntityException("Le client est invalide", ErrorCode.CLIENT_NOT_VALIDE, errors);
        }
        return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(clientDto)));
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id == null) {
            log.error("Le client avec l'id " + id + " n'est pas présent dans la BDD");
            return null;
        }
        Optional<Client> client = clientRepository.findById(id);
        return Optional.of(ClientDto.fromEntity(client.get()))
                .orElseThrow(() -> new EntityNotFoundException(StaticUtil.AUCUN_ELEMENT_TROUVE, ErrorCode.CLIENT_NOT_FOUND));
    }

    @Override
    public ClientDto findByMail(String mail) {
        if (mail == null) {
            log.error("Le client avec l'email " + mail + " n'est pas présent dans la BDD");
            return null;
        }
        Optional<Client> client = clientRepository.findByMail(mail);
        return Optional.of(ClientDto.fromEntity(client.get()))
                .orElseThrow(() -> new EntityNotFoundException(StaticUtil.AUCUN_ELEMENT_TROUVE, ErrorCode.CLIENT_NOT_FOUND));

    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Impossible de supprimer le client, l'id est null");
            return;
        }
        List<CommandeClient> commandeClients = commandeClientRepository.findAllByClientId(id);
        if (!commandeClients.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer le client, is est utilisé dans des commandes", ErrorCode.CLIENT_ALREADY_IN_USE);
        }
        clientRepository.deleteById(id);

    }
}
