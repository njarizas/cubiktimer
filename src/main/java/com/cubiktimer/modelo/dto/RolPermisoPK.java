package com.cubiktimer.modelo.dto;

public class RolPermisoPK {

	private Integer idPermiso;
	private Integer idRol;

	public RolPermisoPK(Integer idPermiso, Integer idRol) {
		super();
		this.idPermiso = idPermiso;
		this.idRol = idRol;
	}

	public Integer getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(Integer idPermiso) {
		this.idPermiso = idPermiso;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

}
