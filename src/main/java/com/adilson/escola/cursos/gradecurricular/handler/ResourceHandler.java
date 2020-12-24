package com.adilson.escola.cursos.gradecurricular.handler;

import com.adilson.escola.cursos.gradecurricular.exception.MateriaException;
import com.adilson.escola.cursos.gradecurricular.model.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResourceHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<Map<String, String>>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach(error -> {
			String field = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(field, message);
		});

		Response<Map<String, String>> response = new Response<>();

		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setData(errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(MateriaException.class)
	public ResponseEntity<Response<String>> handlerMateriaException(MateriaException ex) {
		Response<String> response = new Response<>();

		response.setStatusCode(ex.getHttpStatus().value());
		response.setData(ex.getMessage());

		return ResponseEntity.status(ex.getHttpStatus()).body(response);
	}
}
