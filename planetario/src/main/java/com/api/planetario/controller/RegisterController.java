package com.api.planetario.controller;

import java.net.PasswordAuthentication;

import org.apache.tomcat.util.http.parser.Authorization;
import com.api.planetario.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.planetario.dto.LoginDTO;
import com.api.planetario.dto.ProfileDTO;
import com.api.planetario.dto.RegisterDTO;
import com.api.planetario.dto.UserDTO;
import com.api.planetario.model.Profile;
import com.api.planetario.model.Roles;
import com.api.planetario.model.User;
import com.api.planetario.repository.ProfileRepository;
import com.api.planetario.repository.UserRepository;
import com.api.planetario.services.AuthorizationSecurity;
import com.api.planetario.services.TokenService;

import jakarta.validation.Valid;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class RegisterController {

	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	TokenService tokenservice;
	
	
	@Autowired
	PasswordEncoder encoder;
    
	@Autowired
	UserRepository userrepository;
	
	

	
	@PostMapping("/register")
	public ResponseEntity<String> Register(@Valid  @RequestBody RegisterDTO register){
		
		User user = new User();
		user.setRole(Roles.USER);
		user.setEmail(register.emails());
		user.setPassword(encoder.encode(register.passwords()));
		user.setUsername(register.usernames());
		
		
		userrepository.save(user);
	
		
		return ResponseEntity.ok("");
	}
	
	@PostMapping("/login")
	 public ResponseEntity Login (@RequestBody @Valid LoginDTO logindto) {
	 	
	UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(logindto.email(),logindto.password());
	Authentication authenticate = manager.authenticate(login);
	var Usuario =(User) authenticate.getPrincipal() ;
	return ResponseEntity.ok(tokenservice.generateToken(Usuario));
	} 

}
