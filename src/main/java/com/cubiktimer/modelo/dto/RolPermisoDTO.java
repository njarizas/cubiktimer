package com.cubiktimer.modelo.dto;

import java.io.Serializable;

public class RolPermisoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private RolPermisoPK rolPermisoPK;
	private Integer estado;

	public RolPermisoDTO() {
		super();
		this.estado = 1;
	}

	public RolPermisoPK getRolPermisoPK() {
		return rolPermisoPK;
	}

	public void setRolPermisoPK(RolPermisoPK rolPermisoPK) {
		this.rolPermisoPK = rolPermisoPK;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
