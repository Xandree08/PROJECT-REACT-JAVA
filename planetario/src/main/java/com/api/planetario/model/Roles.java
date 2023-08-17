package com.api.planetario.model;


public enum Roles {
	
	ADMIN("admin"),
	USER("user");
	
	private String roles;
	
	
	Roles (String roles){
		this.roles = roles;
	}
	
	public String role () {
		return this.roles;
	}

}
