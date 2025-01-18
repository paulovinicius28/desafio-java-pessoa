package com.desafio.java.pessoa.converter;

import com.desafio.java.pessoa.entities.Usuario;
import com.desafio.java.pessoa.model.UsuarioDTO;

public class UsuarioDTOConverter {

	public static UsuarioDTO converter(Usuario usuarioSalvo) {
		return new UsuarioDTO(
				usuarioSalvo.getId(),
				usuarioSalvo.getEmail(),
				usuarioSalvo.getNome()
				);
		
	}

}
