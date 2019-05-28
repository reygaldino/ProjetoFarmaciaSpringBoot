package com.farmacia.service;

import java.util.List;

import com.farmacia.model.Client;

public interface ClientService {
	
	void save(Client client);
	Client findById(Long id);
	public List<Client> findAll();
}
