package com.cubiktimer.modelo.dto;

import java.io.Serializable;

public class ParametroDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String valor;

	public ParametroDTO() {
		super();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
