package com.desafio.java.pessoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.java.pessoa.model.AuthenticationRequest;
import com.desafio.java.pessoa.model.AuthenticationResponse;
import com.desafio.java.pessoa.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("login")
public class AuthenticationController {

	@Autowired
	private AuthenticationService service;

	@Operation(description = "Realiza o login do usuário, informando o email e senha.")
	@ApiResponses(value = {			
			@ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso.", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = AuthenticationResponse.class)) }),
			@ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos.", content = @Content)
	})
	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public AuthenticationResponse login(@Valid AuthenticationRequest request) {
		return this.service.auth(request);
	}

}