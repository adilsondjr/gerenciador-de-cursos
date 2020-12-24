package com.adilson.escola.cursos.gradecurricular.service;

import com.adilson.escola.cursos.gradecurricular.dto.MateriaDto;

import java.util.List;

public interface IMateriaService {

	public List<MateriaDto> getAll();

	public MateriaDto getById(Long id);
	
	public List<MateriaDto> getMateriasByHoraMinima(int horaMinima);

	public Boolean create(final MateriaDto materiaDto);

	public Boolean update(final MateriaDto materiaDto);

	public boolean delete(final Long id);

}
