package com.souha.gds.repository;

import com.souha.gds.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface ClientRepository extends JpaRepository<Client,Integer> {

    Optional<Client> findByMail(String mail);
}
