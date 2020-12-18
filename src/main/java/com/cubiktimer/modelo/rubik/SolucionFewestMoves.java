/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import java.io.Serializable;
import java.util.Objects;

import com.cubiktimer.modelo.dto.FewestMovesDTO;

/**
 *
 * @author Nelson
 */
public class SolucionFewestMoves implements Serializable, Comparable<SolucionFewestMoves> {

	private static final long serialVersionUID = 1L;

	private Puzzle cubo;
	private FewestMovesDTO fewestMoveDTO;

	public SolucionFewestMoves(Puzzle cubo, FewestMovesDTO fewestMoveDTO) {
		this.cubo = cubo;
		this.fewestMoveDTO = fewestMoveDTO;
	}

	/**
	 * Note: this class has a natural ordering that is inconsistent with equals.
	 */
	@Override
	public int compareTo(SolucionFewestMoves otherTiempo) {
		return this.fewestMoveDTO.compareTo(otherTiempo.fewestMoveDTO);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fewestMoveDTO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolucionFewestMoves other = (SolucionFewestMoves) obj;
		return Objects.equals(fewestMoveDTO, other.fewestMoveDTO);
	}

	public Puzzle getCubo() {
		return cubo;
	}

	public void setCubo(Puzzle cubo) {
		this.cubo = cubo;
	}

	public FewestMovesDTO getFewestMoveDTO() {
		return fewestMoveDTO;
	}

	public void setFewestMoveDTO(FewestMovesDTO fewestMoveDTO) {
		this.fewestMoveDTO = fewestMoveDTO;
	}

}
