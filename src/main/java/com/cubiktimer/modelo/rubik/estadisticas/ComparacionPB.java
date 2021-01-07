/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik.estadisticas;

import java.io.Serializable;

public class ComparacionPB implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombrePuzzle;
	private Integer mejorTiempo;
	private Integer peorTiempo;
	private RecordPBSingle recordPropio;
	private RecordPBSingle recordAmigo;

	public ComparacionPB() {
		super();
		recordPropio = new RecordPBSingle();
		recordAmigo = new RecordPBSingle();
	}

	public String getNombrePuzzle() {
		return nombrePuzzle;
	}

	public void setNombrePuzzle(String nombrePuzzle) {
		this.nombrePuzzle = nombrePuzzle;
	}

	public Integer getMejorTiempo() {
		return mejorTiempo;
	}

	public void setMejorTiempo(Integer mejorTiempo) {
		this.mejorTiempo = mejorTiempo;
	}

	public Integer getPeorTiempo() {
		return peorTiempo;
	}

	public void setPeorTiempo(Integer peorTiempo) {
		this.peorTiempo = peorTiempo;
	}

	public RecordPBSingle getRecordPropio() {
		return recordPropio;
	}

	public void setRecordPropio(RecordPBSingle recordPropio) {
		this.recordPropio = recordPropio;
	}

	public RecordPBSingle getRecordAmigo() {
		return recordAmigo;
	}

	public void setRecordAmigo(RecordPBSingle recordAmigo) {
		this.recordAmigo = recordAmigo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("<ComparacionPB>");
		if (recordPropio != null) {
			builder.append("<recordPropio>").append(recordPropio).append("</recordPropio>");
		}
		if (recordAmigo != null) {
			builder.append("<recordAmigo>").append(recordAmigo).append("</recordAmigo>");
		}
		builder.append("</ComparacionPB>");
		return builder.toString();
	}

}
