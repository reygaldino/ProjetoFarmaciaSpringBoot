package com.farmacia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.model.Client;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientService clientService;

	@Override
	public void save(Client client) {
		
		clientService.save(client);
	}

	@Override
	public Client findById(Long id) {
		
		return clientService.findById(id);
	}

	@Override
	public List<Client> findAll() {
		
		return clientService.findAll();
	}

}
