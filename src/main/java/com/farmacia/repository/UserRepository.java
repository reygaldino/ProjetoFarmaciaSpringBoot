package com.farmacia.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);

}
