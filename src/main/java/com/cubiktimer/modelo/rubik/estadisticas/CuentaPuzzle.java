/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik.estadisticas;

import java.io.Serializable;

public class CuentaPuzzle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idUsuario;
	private String nombrePuzzle;
	private Integer conteoPuzzle;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getNombrePuzzle() {
		return nombrePuzzle;
	}
	
	public void setNombrePuzzle(String nombrePuzzle) {
		this.nombrePuzzle = nombrePuzzle;
	}
	
	public Integer getConteoPuzzle() {
		return conteoPuzzle;
	}
	
	public void setConteoPuzzle(Integer conteoPuzzle) {
		this.conteoPuzzle = conteoPuzzle;
	}

	public String toString() {
		return "['" + nombrePuzzle + "'," + conteoPuzzle + "]";
	}
	
}
