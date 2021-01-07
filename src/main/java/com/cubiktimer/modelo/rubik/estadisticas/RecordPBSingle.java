/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik.estadisticas;

import java.io.Serializable;

public class RecordPBSingle implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idUsuario;
	private Integer idTipoCubo;
	private String nombrePuzzle;
	private String pbTexto;
	private Integer pbNumero;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdTipoCubo() {
		return idTipoCubo;
	}

	public void setIdTipoCubo(Integer idTipoCubo) {
		this.idTipoCubo = idTipoCubo;
	}

	public String getNombrePuzzle() {
		return nombrePuzzle;
	}

	public void setNombrePuzzle(String nombrePuzzle) {
		this.nombrePuzzle = nombrePuzzle;
	}

	public String getPbTexto() {
		return pbTexto;
	}

	public void setPbTexto(String pbTexto) {
		this.pbTexto = pbTexto;
	}

	public Integer getPbNumero() {
		return pbNumero;
	}

	public void setPbNumero(Integer pbNumero) {
		this.pbNumero = pbNumero;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("<RecordPBSingle>");
		if (idUsuario != null) {
			builder.append("<idUsuario>").append(idUsuario).append("</idUsuario>");
		}
		if (idTipoCubo != null) {
			builder.append("<idTipoCubo>").append(idTipoCubo).append("</idTipoCubo>");
		}
		if (nombrePuzzle != null) {
			builder.append("<nombrePuzzle>").append(nombrePuzzle).append("</nombrePuzzle>");
		}
		if (pbTexto != null) {
			builder.append("<pbTexto>").append(pbTexto).append("</pbTexto>");
		}
		if (pbNumero != null) {
			builder.append("<pbNumero>").append(pbNumero).append("</pbNumero>");
		}
		builder.append("</RecordPBSingle>");
		return builder.toString();
	}

}