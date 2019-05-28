package com.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
