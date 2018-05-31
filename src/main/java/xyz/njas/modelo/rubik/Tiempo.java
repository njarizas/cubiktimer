package xyz.njas.modelo.rubik;

import java.io.Serializable;

import xyz.njas.modelo.dto.TiempoRubikDTO;

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

	@Override
	public int compareTo(Tiempo otherTiempo) {
		return this.tiempoRubikDTO.compareTo(otherTiempo.tiempoRubikDTO);
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
