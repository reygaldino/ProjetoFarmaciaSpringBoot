package com.farmacia.service;

import com.farmacia.model.User;

public interface UserService {
	void save (User user);
	
	User findByUsername(String username);
}
