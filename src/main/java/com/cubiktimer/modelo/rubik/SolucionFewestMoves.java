package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

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

	@Override
	public int compareTo(SolucionFewestMoves otherTiempo) {
		return this.fewestMoveDTO.compareTo(otherTiempo.fewestMoveDTO);
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
