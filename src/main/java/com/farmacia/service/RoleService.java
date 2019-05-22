package com.farmacia.service;

import java.util.List;

import com.farmacia.model.Role;

public interface RoleService {
	
	void save (Role role);
	public Role findById(Long id);
	public List<Role> list();
	void deleteById(Long id);
}
