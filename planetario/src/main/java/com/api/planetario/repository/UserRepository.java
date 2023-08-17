package com.api.planetario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.planetario.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	UserDetails findByEmail(String email);

}
