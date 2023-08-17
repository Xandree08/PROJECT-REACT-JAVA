package com.api.planetario.dto;

import com.api.planetario.model.Profile;
import com.api.planetario.model.Sales;

import jakarta.validation.constraints.Email;

public record UserDTO(Long id , String username , String password , @Email String email ) {

}
