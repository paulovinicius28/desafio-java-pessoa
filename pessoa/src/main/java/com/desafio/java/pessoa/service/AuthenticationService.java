package com.desafio.java.pessoa.service;

import com.desafio.java.pessoa.entities.Usuario;
import com.desafio.java.pessoa.model.AuthenticationRequest;
import com.desafio.java.pessoa.model.AuthenticationResponse;

public interface AuthenticationService {
	
	AuthenticationResponse auth(AuthenticationRequest request);
	
	Usuario getCurrent(String token);

}