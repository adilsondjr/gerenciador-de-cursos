package com.adilson.escola.cursos.gradecurricular.repository;

import com.adilson.escola.cursos.gradecurricular.entity.MateriaEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IMateriaRepository extends JpaRepository<MateriaEntity, Long> {

	@Query("select m from MateriaEntity m where m.cargaHoraria >= :horaMinima")
	public List<MateriaEntity> findByHoraMinima(@Param("horaMinima") int horaMinima);

}
