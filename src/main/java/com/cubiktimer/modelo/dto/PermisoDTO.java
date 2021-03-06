/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.dto;

import java.io.Serializable;

public class PermisoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idPermiso;
	private String url;
	private Integer idPadre;
	private String nombrePermiso;
	private String descripcionPermiso;
	private String namePermiso;
	private String descriptionPermiso;
	private Integer estado;
	
	@Override
	public String toString() {
		return "PermisoDTO [idPermiso=" + idPermiso + ", url=" + url + ", idPadre=" + idPadre + ", nombrePermiso="
				+ nombrePermiso + ", descripcionPermiso=" + descripcionPermiso + ", namePermiso=" + namePermiso
				+ ", descriptionPermiso=" + descriptionPermiso + ", estado=" + estado + "]";
	}

	public Integer getIdPermiso() {
		return idPermiso;
	}
	
	public void setIdPermiso(Integer idPermiso) {
		this.idPermiso = idPermiso;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getIdPadre() {
		return idPadre;
	}
	
	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}
	
	public String getNombrePermiso() {
		return nombrePermiso;
	}
	
	public void setNombrePermiso(String nombrePermiso) {
		this.nombrePermiso = nombrePermiso;
	}
	
	public String getDescripcionPermiso() {
		return descripcionPermiso;
	}
	
	public void setDescripcionPermiso(String descripcionPermiso) {
		this.descripcionPermiso = descripcionPermiso;
	}
	
	public String getNamePermiso() {
		return namePermiso;
	}
	
	public void setNamePermiso(String namePermiso) {
		this.namePermiso = namePermiso;
	}
	
	public String getDescriptionPermiso() {
		return descriptionPermiso;
	}
	
	public void setDescriptionPermiso(String descriptionPermiso) {
		this.descriptionPermiso = descriptionPermiso;
	}
	
	public Integer getEstado() {
		return estado;
	}
	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
}
