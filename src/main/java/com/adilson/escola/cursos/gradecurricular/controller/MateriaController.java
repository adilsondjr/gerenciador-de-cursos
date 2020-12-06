package com.adilson.escola.cursos.gradecurricular.controller;

import com.adilson.escola.cursos.gradecurricular.entity.MateriaEntity;
import com.adilson.escola.cursos.gradecurricular.repository.IMateriaRepository;
import com.adilson.escola.cursos.gradecurricular.service.IMateriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("materia")
public class MateriaController {

    Logger log = LoggerFactory.getLogger(MateriaController.class);

    @Autowired
    private IMateriaRepository materiaRepository;

    @Autowired
    private IMateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> getMaterias() {

        log.info("Starting GET materia ...");

        return ResponseEntity.status(HttpStatus.OK).body(materiaService.getAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaEntity> getMateriasById(@PathVariable Long id) {

        log.info("Starting GET materia by Id ...");

        return ResponseEntity.status(HttpStatus.OK).body(materiaService.getById(id));

    }

    @PostMapping
    public ResponseEntity<Boolean> createMateria(@RequestBody MateriaEntity materiaEntity) {

        log.info("Starting Create materia ...");

        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.create(materiaEntity));

    }

    @PutMapping
    public ResponseEntity<Boolean> updateMateria(@RequestBody MateriaEntity materiaEntity) {

        log.info("Starting UPDATE materia ...");

        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.update(materiaEntity));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        log.info("Starting DELETE materia ...");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.materiaService.delete(id));

    }

}
