/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

public class CaraSkewb implements Serializable {

	private static final long serialVersionUID = 1L;

	private Celda celdaNoroccidente;
	private Celda celdaNororiente;
	private Celda celdaCentro;
	private Celda celdaSuroccidente;
	private Celda celdaSuroriente;

	public CaraSkewb(String color) {
		super();
		celdaNoroccidente = new Celda(color);
		celdaNororiente = new Celda(color);
		celdaCentro = new Celda(color);
		celdaSuroccidente = new Celda(color);
		celdaSuroriente = new Celda(color);
	}

	public CaraSkewb(Celda celdaNoroccidente, Celda celdaNororiente, Celda celdaCentro, Celda celdaSuroccidente,
			Celda celdaSuroriente) {
		super();
		this.celdaNoroccidente = celdaNoroccidente;
		this.celdaNororiente = celdaNororiente;
		this.celdaCentro = celdaCentro;
		this.celdaSuroccidente = celdaSuroccidente;
		this.celdaSuroriente = celdaSuroriente;
	}

	public boolean estaResuelto() {
		return (celdaNoroccidente.equals(celdaNororiente) && celdaNororiente.equals(celdaCentro)
				&& celdaCentro.equals(celdaSuroccidente) && celdaSuroccidente.equals(celdaSuroriente)
				&& celdaSuroriente.equals(celdaNoroccidente));
	}

	@Override
	public String toString() {
		return celdaNoroccidente + "," + celdaNororiente + "," + celdaCentro + "," + celdaSuroccidente + ","
				+ celdaSuroriente;
	}

	public Celda getCeldaNoroccidente() {
		return celdaNoroccidente;
	}

	public void setCeldaNoroccidente(Celda celdaNoroccidente) {
		this.celdaNoroccidente = celdaNoroccidente;
	}

	public Celda getCeldaNororiente() {
		return celdaNororiente;
	}

	public void setCeldaNororiente(Celda celdaNororiente) {
		this.celdaNororiente = celdaNororiente;
	}

	public Celda getCeldaCentro() {
		return celdaCentro;
	}

	public void setCeldaCentro(Celda celdaCentro) {
		this.celdaCentro = celdaCentro;
	}

	public Celda getCeldaSuroccidente() {
		return celdaSuroccidente;
	}

	public void setCeldaSuroccidente(Celda celdaSuroccidente) {
		this.celdaSuroccidente = celdaSuroccidente;
	}

	public Celda getCeldaSuroriente() {
		return celdaSuroriente;
	}

	public void setCeldaSuroriente(Celda celdaSuroriente) {
		this.celdaSuroriente = celdaSuroriente;
	}

}
