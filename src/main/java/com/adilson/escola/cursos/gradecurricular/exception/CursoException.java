package com.adilson.escola.cursos.gradecurricular.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CursoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;

	public CursoException(String message, HttpStatus httpStatus ) {
		super(message);
		this.httpStatus = httpStatus;
	}

}
