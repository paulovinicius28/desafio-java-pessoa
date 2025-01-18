package com.desafio.java.pessoa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.desafio.java.pessoa.entities.Usuario;
import com.desafio.java.pessoa.model.UsuarioDTO;
import com.desafio.java.pessoa.model.UsuarioSaveDTO;
import com.desafio.java.pessoa.model.UsuarioUpdateDTO;

public interface UsuarioService extends UserDetailsService {

	List<UsuarioDTO> findAll();
	
	Page<UsuarioDTO> findAll(Pageable pageable);
	
	UsuarioDTO findById(Long id);
	
	Usuario save(UsuarioSaveDTO usuario);

	void update(Long id, UsuarioUpdateDTO usuarioUpdateDTO);

	void delete(Long id);
	
}
