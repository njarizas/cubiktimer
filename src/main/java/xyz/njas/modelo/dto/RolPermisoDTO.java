package xyz.njas.modelo.dto;

import java.io.Serializable;

public class RolPermisoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idRol;
	private Integer idPermiso;
	private Integer estado;
	
	public RolPermisoDTO() {
		super();
		this.estado = 1;
	}

	public Integer getIdRol() {
		return idRol;
	}
	
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	
	public Integer getIdPermiso() {
		return idPermiso;
	}
	
	public void setIdPermiso(Integer idPermiso) {
		this.idPermiso = idPermiso;
	}
	
	public Integer getEstado() {
		return estado;
	}
	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
