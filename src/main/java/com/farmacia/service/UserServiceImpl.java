package com.farmacia.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.farmacia.model.User;
import com.farmacia.repository.RoleRepository;
import com.farmacia.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(user);
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
		
	}

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return userRepository.getOne(id);
	}

	

}
