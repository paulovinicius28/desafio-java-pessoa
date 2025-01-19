package com.desafio.java.pessoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.java.pessoa.converter.UsuarioDTOConverter;
import com.desafio.java.pessoa.entities.Usuario;
import com.desafio.java.pessoa.model.UsuarioDTO;
import com.desafio.java.pessoa.model.UsuarioSaveDTO;
import com.desafio.java.pessoa.model.UsuarioUpdateDTO;
import com.desafio.java.pessoa.service.UsuarioService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "usuarios")
@OpenAPIDefinition(info = @Info(
        title = "Desafio Java - API",
        description = "Mapeamentos das requisições para o desafio, feitos em Java.",
        version = "1.0.0"))
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@Operation(description = "Busca a lista de todos os usuários cadastrados paginados de forma ASCENDENTE.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorno dos usuários cadastrados.", content = { @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = UsuarioDTO.class))) }),
			@ApiResponse(responseCode = "404", description = "Usuário com o ID informado não encontrado.", content = @Content),
			@ApiResponse(responseCode = "403", description = "Acesso bloqueado - necessária autenticação.", content = @Content)
	})
	@GetMapping
	public Page<UsuarioDTO> findAll(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return this.service.findAll(pageable);
	}

	@Operation(description = "Busca o usuário, baseado no ID informado.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorno do usuário com o ID informado.", content = { @Content(mediaType = "application/json",
		               schema = @Schema(implementation = UsuarioDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Usuário com o ID informado não encontrado.", content = @Content),
			@ApiResponse(responseCode = "403", description = "Acesso bloqueado - necessária autenticação.", content = @Content)
	})		
	@GetMapping("{id}")
	public UsuarioDTO findById(@PathVariable Long id) {
		return this.service.findById(id);
	}

	@Operation(description = "Realiza o cadastro do usuário.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso.", content = { @Content(mediaType = "application/json",
		               schema = @Schema(implementation = UsuarioDTO.class)) }),
			@ApiResponse(responseCode = "403", description = "Acesso bloqueado - necessária autenticação.", content = @Content)
	})	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UsuarioDTO save(@RequestBody UsuarioSaveDTO usuarioSaveDTO) {
		Usuario usuarioSalvo =  this.service.save(usuarioSaveDTO);

		return UsuarioDTOConverter.converter(usuarioSalvo);

	}
	
	@Operation(description = "Altera o usuário baseado no ID informado.")
	@PutMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Usuário Alterado com sucesso.", content = @Content),
			@ApiResponse(responseCode = "403", description = "Acesso bloqueado - necessária autenticação.", content = @Content)
	})
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") Long id,
			@RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {

		this.service.update(id, usuarioUpdateDTO);
		
	}

	@Operation(description = "Exclui o usuário baseado no ID informado.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Usuário Excluído com sucesso.",  content = @Content),		
			@ApiResponse(responseCode = "403", description = "Acesso bloqueado - necessária autenticação.", content = @Content)
	})
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.service.delete(id);
		
	}

}