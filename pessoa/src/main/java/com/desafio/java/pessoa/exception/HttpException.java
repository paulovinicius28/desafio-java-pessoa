package com.desafio.java.pessoa.exception;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HttpException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final HttpStatus DEFAULT_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

	private HttpStatus status;

	/** COM THROWABLE **/
	public HttpException(Throwable throwable) {
		this(throwable, DEFAULT_STATUS);
	}

	public HttpException(Throwable throwable, HttpStatus status) {
		this(null, throwable, status);
	}

	/** COM MENSAGEM **/
	public HttpException(String message) {
		this(message, DEFAULT_STATUS);
	}

	public HttpException(String message, HttpStatus status) {
		this(message, null, status);
	}

	/** COM STATUS **/
	public HttpException(HttpStatus status) {
		this(null, null, status);
	}

	/** COM AMBOS **/
	public HttpException(String message, Throwable throwable) {
		this(message, throwable, DEFAULT_STATUS);
	}

	public HttpException(String message, Throwable throwable, HttpStatus status) {
		super(message, throwable);
		this.status = status;
	}

}
