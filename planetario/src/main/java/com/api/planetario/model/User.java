package com.api.planetario.model;


import static com.api.planetario.model.Roles.ADMIN;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.api.planetario.dto.UserDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.validation.constraints.Email;

@Entity
public class User implements UserDetails{
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	@Column(unique = true ) 
	private String username;
	
	@Column(nullable= false)
	private String password;
	
	@Email@Column
	private String email;
	
	@ManyToOne
	@JoinColumn(name="profile_id")
	private Profile profile;
	

	@Column
	private double balance = 0;
	
	@ManyToOne
	private Sales sales;
	
	@Enumerated(EnumType.STRING)
    private Roles role;

	
	public User() {}
	


	public User(Long id, String username, String password, @Email String email, Profile profile, Sales sales , Roles roles , double balance) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile = profile;
		this.sales = sales;
		this.role = roles;
		this.balance = balance;
	}








	public Long getId() {
		return id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
   @Override
	public String getUsername() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String getPassword() {
		return password;	
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getEmail() {
		return email;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}



	public void setEmail(@Email String email) {
		this.email = email;
	}




	public Roles getRole() {
		return role;
	}



	public void setRole(Roles role) {
		this.role = role;
	}



	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Sales getSales() {
		return sales;
	}

	public void setSales(Sales sales) {
		this.sales = sales;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}







	






}






