package com.desafio.java.pessoa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.desafio.java.pessoa.exception.ErrorDTO;
import com.desafio.java.pessoa.exception.HttpException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	private static final HttpStatus DEFAULT_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDTO> exception(Exception exception) {
		exception.printStackTrace();
		HttpStatus status = DEFAULT_STATUS;
		return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(ErrorDTO.builder()
				.title(status.getReasonPhrase()).status(status.value()).message(exception.getMessage()).build());
	}
	
	@ExceptionHandler(HttpException.class)
	public ResponseEntity<ErrorDTO> httpException(HttpException exception) {
		exception.printStackTrace();
		HttpStatus status = exception.getStatus();
		return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(ErrorDTO.builder()
				.title(status.getReasonPhrase()).status(status.value()).message(exception.getMessage()).build());
	}

}