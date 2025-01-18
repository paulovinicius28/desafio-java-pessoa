package com.desafio.java.pessoa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.desafio.java.pessoa.entities.Usuario;
import com.desafio.java.pessoa.exception.HttpException;
import com.desafio.java.pessoa.model.AuthenticationRequest;
import com.desafio.java.pessoa.model.AuthenticationResponse;
import com.desafio.java.pessoa.repositories.UsuarioRepository;
import com.desafio.java.pessoa.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Integer JWT_EXPIRES_IN_SECONDS = 10;

	@Value("${app.jwt.secret-key}")
	private String secretKey;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public AuthenticationResponse auth(@Valid AuthenticationRequest request) {
		Authentication authentication = this.manager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword(), new ArrayList<>()));
		Usuario usuario = (Usuario) authentication.getPrincipal();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return AuthenticationResponse.builder().nome(usuario.getNome()).token(this.createToken(usuario)).build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Usuario getCurrent(String token) {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(this.secretKey.getBytes())).build();
			String jsonString = verifier.verify(token).getSubject();
			Map<String, Object> map = new ObjectMapper().readValue(jsonString, Map.class);
			return this.usuarioRepository.findById(((Integer) map.get("id")).longValue())
					.orElseThrow(() -> new HttpException("Acesso negado", HttpStatus.FORBIDDEN));
		} catch (Exception e) {
			return null;
		}
	}

	private String createToken(Usuario usuario) {
		try {

			return JWT.create().withSubject(new ObjectMapper().writeValueAsString(this.createMap(usuario)))
					.withIssuedAt(new Date())
					.withExpiresAt(new Date(System.currentTimeMillis() + (JWT_EXPIRES_IN_SECONDS * 1000)))
					.sign(Algorithm.HMAC256(this.secretKey.getBytes()));
		} catch (Exception e) {
			throw new HttpException(e);
		}
	}

	private Map<String, Object> createMap(Usuario usuario) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", usuario.getId());
		map.put("nome", usuario.getNome());
		map.put("email", usuario.getEmail());
		return map;
	}

}