/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.dto;

import java.io.Serializable;

public class AverageDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idAverage;
	private Integer mejor;
	private String mejorTexto;
	private Integer peor;
	private String peorTexto;
	private Integer media;
	private String mediaTexto;
	private Integer ao5;
	private String ao5Texto;
	private Integer estado;

	public AverageDTO() {
		super();
		this.estado = 1;
	}

	public Integer getIdAverage() {
		return idAverage;
	}

	public void setIdAverage(Integer idAverage) {
		this.idAverage = idAverage;
	}

	public Integer getMejor() {
		return mejor;
	}

	public void setMejor(Integer mejor) {
		this.mejor = mejor;
	}

	public String getMejorTexto() {
		return mejorTexto;
	}

	public void setMejorTexto(String mejorTexto) {
		this.mejorTexto = mejorTexto;
	}

	public Integer getPeor() {
		return peor;
	}

	public void setPeor(Integer peor) {
		this.peor = peor;
	}

	public String getPeorTexto() {
		return peorTexto;
	}

	public void setPeorTexto(String peorTexto) {
		this.peorTexto = peorTexto;
	}

	public Integer getMedia() {
		return media;
	}

	public void setMedia(Integer media) {
		this.media = media;
	}

	public String getMediaTexto() {
		return mediaTexto;
	}

	public void setMediaTexto(String mediaTexto) {
		this.mediaTexto = mediaTexto;
	}

	public Integer getAo5() {
		return ao5;
	}

	public void setAo5(Integer ao5) {
		this.ao5 = ao5;
	}

	public String getAo5Texto() {
		return ao5Texto;
	}

	public void setAo5Texto(String ao5Texto) {
		this.ao5Texto = ao5Texto;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
