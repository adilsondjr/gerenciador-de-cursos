package com.adilson.escola.cursos.gradecurricular.repository;

import com.adilson.escola.cursos.gradecurricular.entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMateriaRepository extends JpaRepository<MateriaEntity, Long> {



}
