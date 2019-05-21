package com.cubiktimer.modelo.dto;

public class UsuarioRolPK {

	private Integer idUsuario;
	private Integer idRol;

	public UsuarioRolPK(Integer idUsuario, Integer idRol) {
		super();
		this.idUsuario = idUsuario;
		this.idRol = idRol;
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

}
