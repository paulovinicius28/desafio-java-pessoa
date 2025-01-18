package com.desafio.java.pessoa.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int status;	
	private String title;
	private String message;
	
	@Builder.Default
	private LocalDateTime timestamp = LocalDateTime.now();

}