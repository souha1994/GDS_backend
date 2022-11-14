package com.souha.gds.service;

import com.souha.gds.dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto save(ClientDto ClientDto);

    ClientDto findById(Integer id);

   // ClientDto findByNomAndPrenom(String nom,String prenom);

    ClientDto findByMail(String mail);

    List<ClientDto> findAll();

    void delete(Integer id);
}
