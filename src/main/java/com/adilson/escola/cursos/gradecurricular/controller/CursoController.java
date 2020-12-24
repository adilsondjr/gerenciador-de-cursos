package com.adilson.escola.cursos.gradecurricular.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adilson.escola.cursos.gradecurricular.entity.CursoEntity;
import com.adilson.escola.cursos.gradecurricular.model.CursoModel;
import com.adilson.escola.cursos.gradecurricular.model.Response;
import com.adilson.escola.cursos.gradecurricular.service.ICursoService;

@RestController
@RequestMapping("/curso")
public class CursoController {
	
	Logger log = LoggerFactory.getLogger(CursoController.class);

	@Autowired
	private ICursoService cursoService;

	/*
	 * Cadastro curso, passando a os códigos das matérias a serem cadastradas
	 */
	@PostMapping
	public ResponseEntity<Response<Boolean>> cadastrarCurso(@Valid @RequestBody CursoModel curso) {
		
		log.info("Starting create curso ...");

		Response<Boolean> response = new Response<>();

		response.setData(cursoService.cadastrar(curso));
		response.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/*
	 * Listar cursos
	 */
	@GetMapping
	public ResponseEntity<Response<List<CursoEntity>>> listarCurso() {
		
		log.info("Starting Get all cursos ...");
		
		Response<List<CursoEntity>> response = new Response<>();
		
		response.setData(this.cursoService.listar());
		response.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/*
	 * Consultar curso por código do curso
	 */
	@GetMapping("/{codCurso}")
	public ResponseEntity<Response<CursoEntity>> consultarCursoPorMateria(@PathVariable String codCurso) {
		
		log.info("Starting get curso by codigo do curso ...");
		
		Response<CursoEntity> response = new Response<>();
		
		response.setData(this.cursoService.consultarPorCodigo(codCurso));
		response.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/*
	 * Atualizar curso
	 */
	@PutMapping
	public ResponseEntity<Response<Boolean>> atualizarCurso(@Valid @RequestBody CursoModel curso) {
		
		log.info("Starting update curso ...");
		
		Response<Boolean> response = new Response<>();
		
		response.setData(cursoService.atualizar(curso));
		response.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/*
	 * Excluir curso
	 */
	@DeleteMapping("/{cursoId}")
	public ResponseEntity<Response<Boolean>> excluirCurso( @PathVariable Long cursoId) {
		
		log.info("Starting delete curso ...");
		
		Response<Boolean> response = new Response<>();
		
		response.setData(cursoService.excluir(cursoId));
		response.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
