package com.adilson.escola.cursos.gradecurricular.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("materia")
public class MateriaController {

    Logger log = LoggerFactory.getLogger(MateriaController.class);

    @GetMapping
    public ResponseEntity<String> helloWorldRest() {

        log.info("Starting GET materia ...");

        return ResponseEntity.status(HttpStatus.OK).body("Olá Rest");
    }


}
