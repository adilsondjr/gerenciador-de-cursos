package com.adilson.escola.cursos.gradecurricular.service;

import java.util.List;

import com.adilson.escola.cursos.gradecurricular.entity.CursoEntity;
import com.adilson.escola.cursos.gradecurricular.model.CursoModel;

public interface ICursoService {

	Boolean cadastrar(CursoModel cursoModel);
	
	Boolean atualizar(CursoModel cursoModel);
	
	Boolean excluir(Long cursoId);
	
	CursoEntity consultarPorCodigo(String codCurso);
	
	List<CursoEntity> listar();
	
}
