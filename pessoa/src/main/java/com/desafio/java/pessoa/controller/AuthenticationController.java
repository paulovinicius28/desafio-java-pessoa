package com.desafio.java.pessoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.java.pessoa.model.AuthenticationRequest;
import com.desafio.java.pessoa.model.AuthenticationResponse;
import com.desafio.java.pessoa.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("login")
public class AuthenticationController {

	@Autowired
	private AuthenticationService service;

	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public AuthenticationResponse login(@Valid AuthenticationRequest request) {
		return this.service.auth(request);
	}

}