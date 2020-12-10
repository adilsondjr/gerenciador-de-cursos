package com.adilson.escola.cursos.gradecurricular.handler;

import com.adilson.escola.cursos.gradecurricular.exception.MateriaException;
import com.adilson.escola.cursos.gradecurricular.model.ErrorResponse;
import com.adilson.escola.cursos.gradecurricular.model.ErrorResponse.ErrorResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(MateriaException.class)
    public ResponseEntity<ErrorResponse> handlerMateriaException(MateriaException ex) {
        ErrorResponseBuilder erro = ErrorResponse.builder();

        erro.httpStatus(ex.getHttpStatus().value());
        erro.message(ex.getMessage());
        erro.timeStamp(System.currentTimeMillis());

        return ResponseEntity.status(ex.getHttpStatus()).body(erro.build());
    }

}
