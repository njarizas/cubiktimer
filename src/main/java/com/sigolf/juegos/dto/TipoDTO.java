package com.sigolf.juegos.dto;

import java.io.Serializable;

public class TipoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idTipo;
	private Integer idPadre;
	private String nombreTipo;
	private String nameTipo;
	private Integer estado;
	public Integer getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}
	public Integer getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}
	public String getNombreTipo() {
		return nombreTipo;
	}
	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}
	public String getNameTipo() {
		return nameTipo;
	}
	public void setNameTipo(String nameTipo) {
		this.nameTipo = nameTipo;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
