package com.adilson.escola.cursos.gradecurricular.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "materia")
@Data
@NoArgsConstructor
public class MateriaEntity implements Serializable {

	private static final long serialVersionUID = -2051509500677088101L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id")
	private Long id;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Column(name = "nome")
	private String nome;

	@Column(name = "cargaHoraria")
	private int cargaHoraria;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Column(name = "codigo")
	private String codigo;

	@Column(name = "frequencia")
	private int frequencia;

}
