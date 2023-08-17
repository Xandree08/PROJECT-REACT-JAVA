package com.api.planetario.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.planetario.model.User;
import com.api.planetario.repository.UserRepository;
import com.api.planetario.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	/*
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	TokenService tokenservice;
	
         private String recoverToken(HttpServletRequest request) {
		
		var authHeader = request.getHeader("Authorization");
		if(authHeader == null) return null;
		return authHeader.replace("Bearer" , "");
		
	     }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		var token = this.recoverToken(request);		
		if(token != null) {
			
			var subject = tokenservice.validateToken(token);
			UserDetails user = userrepository.findByUsername(subject);
			var authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),null,user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		filterChain.doFilter(request, response);
		
		
	}
	
	
	*/
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	TokenService tokenservice;
	
	String token;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		var auth = request.getHeader("Authorization");		
		if(auth != null) {
			token = auth.replace("Bearer", "");
			var subject = this.tokenservice.validateToken(token);
			UserDetails user = userrepository.findByEmail(subject);
			var authentication = new UsernamePasswordAuthenticationToken(user,null,null);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		filterChain.doFilter(request, response);
		
		
	}
	
	

}
