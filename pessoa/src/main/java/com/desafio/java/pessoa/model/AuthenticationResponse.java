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
public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String token;
	
}