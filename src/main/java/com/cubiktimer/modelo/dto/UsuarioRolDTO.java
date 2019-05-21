package com.cubiktimer.modelo.dto;

import java.io.Serializable;

public class UsuarioRolDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UsuarioRolPK usuarioRolPK;
	private Integer estado;

	public UsuarioRolDTO() {
		super();
		this.estado = 1;
	}

	public UsuarioRolPK getUsuarioRolPK() {
		return usuarioRolPK;
	}

	public void setUsuarioRolPK(UsuarioRolPK usuarioRolPK) {
		this.usuarioRolPK = usuarioRolPK;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
