package com.api.planetario.dto;

import org.hibernate.validator.constraints.br.CPF;

import com.api.planetario.model.Profile;

public record ProfileDTO(Long id ,String name , String lastname , @CPF String cpf){

}
