package com.adilson.escola.cursos.gradecurricular.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MateriaException extends RuntimeException {

	private static final long serialVersionUID = 7864247519846859511L;

	private final HttpStatus httpStatus;

	public MateriaException(final String mensagem, final HttpStatus httpStatus) {
		super(mensagem);
		this.httpStatus = httpStatus;
	}
}
