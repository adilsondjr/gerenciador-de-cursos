package com.adilson.escola.cursos.gradecurricular.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MateriaDto  extends RepresentationModel<MateriaDto>{

    private Long id;

    @NotBlank(message = "Informe o nome da materia.")
    private String nome;

    @Min(value = 34, message = "Permitido o minimo de 34 horas por materia.")
    @Max(value = 120, message = "Permitido o maximo de 120 horas por materia.")
    private int cargaHoraria;

    @NotBlank(message = "Informe o codigo da materia.")
    private String codigo;

    @Min(value = 1, message = "Permitido o minimo de 1 vez ao ano.")
    @Max(value = 2, message = "Permitido o maximo de 2 vezes ao ano.")
    private int frequencia;

}
