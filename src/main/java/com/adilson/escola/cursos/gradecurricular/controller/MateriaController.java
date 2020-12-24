package com.adilson.escola.cursos.gradecurricular.controller;

import com.adilson.escola.cursos.gradecurricular.constants.HyperLinkConstants;
import com.adilson.escola.cursos.gradecurricular.dto.MateriaDto;
import com.adilson.escola.cursos.gradecurricular.model.Response;
import com.adilson.escola.cursos.gradecurricular.service.IMateriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("materia")
public class MateriaController {

	Logger log = LoggerFactory.getLogger(MateriaController.class);

	@Autowired
	private IMateriaService materiaService;

	@GetMapping
	public ResponseEntity<Response<List<MateriaDto>>> getMaterias() {

		log.info("Starting GET materia ...");

		Response<List<MateriaDto>> response = new Response<>();
		response.setData(materiaService.getAll());
		response.setStatusCode(HttpStatus.OK.value());

		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).getMaterias())
				.withSelfRel());

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<MateriaDto>> getMateriasById(@PathVariable Long id) {

		log.info("Starting GET materia by Id ...");

		Response<MateriaDto> response = new Response<>();
		response.setData(materiaService.getById(id));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).getMateriasById(id))
				.withSelfRel());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).delete(id))
				.withRel(HyperLinkConstants.DELETE.getValor()));
		response.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).updateMateria(materiaService.getById(id)))
				.withRel(HyperLinkConstants.UPDATE.getValor()));

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}
	
	@GetMapping("/horaMinima/{horaMinima}")
	public ResponseEntity<Response<List<MateriaDto>>> getMateriasByHoraMinima(@PathVariable int horaMinima) {

		log.info("Starting GET materia by carga horaria ...");

		Response <List<MateriaDto>> response = new Response<>();
		response.setData(materiaService.getMateriasByHoraMinima(horaMinima));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).getMateriasByHoraMinima(horaMinima))
				.withSelfRel());		

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@PostMapping
	public ResponseEntity<Boolean> createMateria(@Valid @RequestBody MateriaDto materiaDto) {

		log.info("Starting Create materia ...");

		return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.create(materiaDto));

	}

	@PutMapping
	public ResponseEntity<Boolean> updateMateria(@Valid @RequestBody MateriaDto materiaDto) {

		log.info("Starting UPDATE materia ...");

		return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.update(materiaDto));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {

		log.info("Starting DELETE materia ...");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.materiaService.delete(id));

	}

}
