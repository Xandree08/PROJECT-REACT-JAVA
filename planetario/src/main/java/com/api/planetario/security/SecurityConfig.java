package com.api.planetario.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
@Autowired 
SecurityFilter securityfilter;
	
	@Bean
	public SecurityFilterChain filterchain (HttpSecurity http) throws Exception {
		
		return http.csrf(csrf -> csrf.disable()).
				sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/auth/login").permitAll()
						.requestMatchers("/auth/register").permitAll()
						.requestMatchers("/dashboard").permitAll()
						.anyRequest().authenticated()
						).addFilterBefore(securityfilter, UsernamePasswordAuthenticationFilter.class)
				.build()
				;

	}

	
	@Bean
	public BCryptPasswordEncoder BCryptPasswordEncoder () {
		return new BCryptPasswordEncoder(); 
	}
	
	@Bean
	public AuthenticationManager manager (AuthenticationConfiguration config)throws Exception {
		return config.getAuthenticationManager();
	}
	
}
