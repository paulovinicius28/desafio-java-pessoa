package com.desafio.java.pessoa.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	
}