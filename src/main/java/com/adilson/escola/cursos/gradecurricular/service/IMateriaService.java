package com.adilson.escola.cursos.gradecurricular.service;

import com.adilson.escola.cursos.gradecurricular.entity.MateriaEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMateriaService {

    public List<MateriaEntity> getAll();

    public MateriaEntity getById(Long id);

    public Boolean create(final MateriaEntity materiaEntity);

    public Boolean update(final MateriaEntity materiaEntity);

    public boolean delete(final Long id);
}
