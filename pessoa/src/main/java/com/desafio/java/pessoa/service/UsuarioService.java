package com.desafio.java.pessoa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.desafio.java.pessoa.model.UsuarioDTO;

public interface UsuarioService extends UserDetailsService {

	List<UsuarioDTO> findAll();
	
	Page<UsuarioDTO> findAll(Pageable pageable);
	
	UsuarioDTO findById(Long id);
	
}
