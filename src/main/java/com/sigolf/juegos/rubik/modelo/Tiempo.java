package com.sigolf.juegos.rubik.modelo;

import java.io.Serializable;

import com.sigolf.juegos.dto.TiempoRubikDTO;

/**
 *
 * @author Nelson
 */
public class Tiempo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CuboRubik cubo;
    private TiempoRubikDTO tiempoRubikDTO;
    

    public Tiempo(CuboRubik cubo, TiempoRubikDTO tiempoRubikDTO) {
        this.cubo = cubo;
        this.tiempoRubikDTO = tiempoRubikDTO;
    }

    public CuboRubik getCubo() {
        return cubo;
    }

    public void setCubo(CuboRubik cubo) {
        this.cubo = cubo;
    }

	public TiempoRubikDTO getTiempoRubikDTO() {
		return tiempoRubikDTO;
	}

	public void setTiempoRubikDTO(TiempoRubikDTO tiempoRubikDTO) {
		this.tiempoRubikDTO = tiempoRubikDTO;
	}

}
