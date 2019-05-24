package com.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	public List<Role> findByName(String name);

	
}
