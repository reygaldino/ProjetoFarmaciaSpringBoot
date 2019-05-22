package com.farmacia.service;

import java.util.List;

import com.farmacia.model.User;

public interface UserService {
	void save (User user);
	User findByUsername(String username);
	User findById(Long id);
	public List<User> findAll();
}
