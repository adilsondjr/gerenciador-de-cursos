package com.adilson.escola.cursos.gradecurricular.constants;

import lombok.Getter;

@Getter
public enum HyperLinkConstants {

	UPDATE("UPDATE"),
	DELETE("DELETE"),
	GET_ALL("GET_ALL"),
	GET("GET");
	
	private final String valor;
	
	private HyperLinkConstants(String valor) {
		this.valor = valor;
	}
	
}
