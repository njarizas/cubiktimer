/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import java.io.Serializable;
import java.util.Objects;

import com.cubiktimer.modelo.dto.TiempoRubikDTO;

/**
 *
 * @author Nelson
 */
public class Tiempo implements Serializable, Comparable<Tiempo> {

	private static final long serialVersionUID = 1L;

	private Puzzle cubo;
	private TiempoRubikDTO tiempoRubikDTO;

	public Tiempo(Puzzle cubo, TiempoRubikDTO tiempoRubikDTO) {
		this.cubo = cubo;
		this.tiempoRubikDTO = tiempoRubikDTO;
	}

	/**
	 * Note: this class has a natural ordering that is inconsistent with equals.
	 */
	@Override
	public int compareTo(Tiempo otherTiempo) {
		return this.tiempoRubikDTO.compareTo(otherTiempo.tiempoRubikDTO);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tiempoRubikDTO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tiempo other = (Tiempo) obj;
		return Objects.equals(tiempoRubikDTO, other.tiempoRubikDTO);
	}

	public Puzzle getCubo() {
		return cubo;
	}

	public void setCubo(Puzzle cubo) {
		this.cubo = cubo;
	}

	public TiempoRubikDTO getTiempoRubikDTO() {
		return tiempoRubikDTO;
	}

	public void setTiempoRubikDTO(TiempoRubikDTO tiempoRubikDTO) {
		this.tiempoRubikDTO = tiempoRubikDTO;
	}

}
