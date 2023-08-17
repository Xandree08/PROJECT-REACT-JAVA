package com.api.planetario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.planetario.model.User;
import com.api.planetario.repository.UserRepository;


@Service
public class AuthorizationSecurity implements UserDetailsService{

	@Autowired
	UserRepository userrepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	
		
		return userrepository.findByEmail(email);
	}

	
	
}
