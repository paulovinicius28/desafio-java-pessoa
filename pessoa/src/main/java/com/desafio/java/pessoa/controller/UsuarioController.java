package com.desafio.java.pessoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.java.pessoa.converter.UsuarioDTOConverter;
import com.desafio.java.pessoa.entities.Usuario;
import com.desafio.java.pessoa.model.UsuarioDTO;
import com.desafio.java.pessoa.model.UsuarioSaveDTO;
import com.desafio.java.pessoa.model.UsuarioUpdateDTO;
import com.desafio.java.pessoa.service.UsuarioService;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public Page<UsuarioDTO> findAll(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return this.service.findAll(pageable);
	}

	@GetMapping("{id}")
	public UsuarioDTO findById(@PathVariable Long id) {
		return this.service.findById(id);
	}

	@PostMapping
	public UsuarioDTO save(@RequestBody UsuarioSaveDTO usuarioSaveDTO) {
		Usuario usuarioSalvo =  this.service.save(usuarioSaveDTO);

		return UsuarioDTOConverter.converter(usuarioSalvo);

	}

	@PutMapping("/{id}")
	public void update(@PathVariable("id") Long id,
			@RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {

		this.service.update(id, usuarioUpdateDTO);

		//return UsuarioDTOConverter.converter(usuarioSalvo);

	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		this.service.delete(id);

		//return UsuarioDTOConverter.converter(usuarioSalvo);

	}

}