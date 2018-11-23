package com.cubiktimer.modelo.dto;

import java.io.Serializable;

public class UsuarioRolDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idUsuario;
	private Integer idRol;
	private Integer estado;
	
	public UsuarioRolDTO() {
		super();
		this.estado=1;
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Integer getIdRol() {
		return idRol;
	}
	
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	
	public Integer getEstado() {
		return estado;
	}
	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
}
