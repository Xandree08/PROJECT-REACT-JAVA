package com.api.planetario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.planetario.model.User;
import com.api.planetario.repository.UserRepository;
import com.api.planetario.security.SecurityFilter;
import com.api.planetario.services.TokenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.AssertFalse.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class DashboardController {
	
	@Autowired
	SecurityFilter filter;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	TokenService tokenservice;
	
	@GetMapping("/dashboard")
	public ResponseEntity<UserDetails> getUserFromToken(HttpServletRequest request) throws UsernameNotFoundException{
		
		String token = tokenservice.resolveToken(request);
		 if (token != null) {
	            String username = tokenservice.getUsernameFromToken(token);
	            UserDetails user = userrepository.findByEmail(username);
	            
	            return ResponseEntity.ok(user);
	        }
		
		 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
