package com.desafio.java.pessoa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.desafio.java.pessoa.entities.Usuario;
import com.desafio.java.pessoa.exception.HttpException;
import com.desafio.java.pessoa.model.UsuarioDTO;
import com.desafio.java.pessoa.model.UsuarioSaveDTO;
import com.desafio.java.pessoa.model.UsuarioUpdateDTO;
import com.desafio.java.pessoa.repositories.UsuarioRepository;
import com.desafio.java.pessoa.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<UsuarioDTO> findAll() {
		return this.repository.findAll().stream().map(p -> this.map(p)).toList();
	}

	@Override
	public Page<UsuarioDTO> findAll(Pageable pageable) {
		return this.repository.findAll(pageable).map(p -> this.map(p));
	}

	@Override
	public UsuarioDTO findById(Long id) {
		return this.map(this.repository.findById(id)
				.orElseThrow(() -> new HttpException("Usuário não encontrado", HttpStatus.NOT_FOUND)));
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.repository.findByEmail(username)
				.orElseThrow(() -> new HttpException("Usuário " + username + " não encontrado", HttpStatus.NOT_FOUND));
	}

	private UsuarioDTO map(Usuario entity) {
		return UsuarioDTO.builder().id(entity.getId()).nome(entity.getNome()).email(entity.getEmail()).build();
	}

	@Override
	public Usuario save(UsuarioSaveDTO usuario) {
		return this.repository.save(new Usuario(
				usuario.getNome(),
				usuario.getEmail(),
				passwordEncoder.encode(usuario.getSenha())));
	}

	@Override
	public void update(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {

		Usuario retornoUsuarioBanco = this.repository.findById(id).orElseThrow(() -> new HttpException("Usuário " +  " não encontrado", HttpStatus.NOT_FOUND));

		if (usuarioUpdateDTO.getNome() != null && !usuarioUpdateDTO.getNome().isEmpty()) {
			retornoUsuarioBanco.setNome(usuarioUpdateDTO.getNome());
		}

		if (usuarioUpdateDTO.getEmail() != null && !usuarioUpdateDTO.getEmail().isEmpty()) {
			retornoUsuarioBanco.setEmail(usuarioUpdateDTO.getEmail());
		}

		if (usuarioUpdateDTO.getSenha() != null && !usuarioUpdateDTO.getSenha().isEmpty()) {
			retornoUsuarioBanco.setSenha(passwordEncoder.encode(usuarioUpdateDTO.getSenha()));
		}
		
		this.repository.save(retornoUsuarioBanco);

	}

	@Override
	public void delete(Long id) {
		Usuario retornoUsuarioBanco = this.repository.findById(id).orElseThrow(() -> new HttpException("Usuário " +  " não encontrado", HttpStatus.NOT_FOUND));

		this.repository.delete(retornoUsuarioBanco);

	}

}
