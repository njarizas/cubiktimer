package com.cubiktimer.modelo.rubik.estadisticas;

import java.io.Serializable;

public class RecordPBSingle implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idUsuario;
	private String nombrePuzzle;
	private String pbSingle;

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

	public String getPbSingle() {
		return pbSingle;
	}

	public void setPbSingle(String pbSingle) {
		this.pbSingle = pbSingle;
	}

	public String toString() {
		return "['" + nombrePuzzle + "'," + pbSingle + "]";
	}

}