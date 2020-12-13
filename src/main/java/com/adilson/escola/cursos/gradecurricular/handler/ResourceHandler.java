package com.adilson.escola.cursos.gradecurricular.handler;

import com.adilson.escola.cursos.gradecurricular.exception.MateriaException;
import com.adilson.escola.cursos.gradecurricular.model.ErrorMapResponse;
import com.adilson.escola.cursos.gradecurricular.model.ErrorMapResponse.ErrorMapResponseBuilder;
import com.adilson.escola.cursos.gradecurricular.model.ErrorResponse;
import com.adilson.escola.cursos.gradecurricular.model.ErrorResponse.ErrorResponseBuilder;
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
    public ResponseEntity<ErrorMapResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error ).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        ErrorMapResponseBuilder errorMap = ErrorMapResponse.builder();
        errorMap.errors(errors).httpStatus(HttpStatus.BAD_REQUEST.value()).timeStamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap.build());
    }

    @ExceptionHandler(MateriaException.class)
    public ResponseEntity<ErrorResponse> handlerMateriaException(MateriaException ex) {
        ErrorResponseBuilder erro = ErrorResponse.builder();

        erro.httpStatus(ex.getHttpStatus().value());
        erro.message(ex.getMessage());
        erro.timeStamp(System.currentTimeMillis());

        return ResponseEntity.status(ex.getHttpStatus()).body(erro.build());
    }

}
