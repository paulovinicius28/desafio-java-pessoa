package com.desafio.java.pessoa.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private String nome;	
	private String email;
	private String senha;

}