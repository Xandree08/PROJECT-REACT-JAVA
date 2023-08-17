package com.api.planetario.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.api.planetario.dto.LoginDTO;
import com.api.planetario.dto.UserDTO;
import com.api.planetario.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	String secretkey;
	
	
     public String generateToken (User user) {
    	 
    	 try {
    		 Algorithm algorithm = Algorithm.HMAC256(secretkey);
    		 String token = JWT.create().withIssuer("Login")
    				 .withSubject(user.getUsername()).
    				 withExpiresAt(LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00")))
    				 .sign(algorithm);
    		 
    		 return token;
			
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro na criação do Token " , e);
		}
     }

     
     public String validateToken (String token) {
    	 
    	 try {
    		 Algorithm algorithm = Algorithm.HMAC256(secretkey);
    		 return JWT.require(algorithm)
    				 .withIssuer("Login")
    				 .build()
    				 .verify(token)
    				 .getSubject();
			
		} catch (JWTVerificationException e) {
			
			return null;
		}
     }
     
     public String resolveToken(HttpServletRequest request) {
         String bearerToken = request.getHeader("Authorization");
         if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
             return bearerToken.substring(7);
         }
         return null;
     }
     
     public String getUsernameFromToken(String token) {
    	    try {
    	        Algorithm algorithm = Algorithm.HMAC256(secretkey);
    	        DecodedJWT jwt = JWT.require(algorithm)
    	            .withIssuer("Login")
    	            .build()
    	            .verify(token);

    	        return jwt.getSubject();

    	    } catch (JWTVerificationException e) {
    	        return null;
    	    }
    	}
    	





     }
     
     
     
     
