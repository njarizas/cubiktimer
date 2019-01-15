package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import com.cubiktimer.util.Constantes;

public class CuboRubik2x2 extends CuboRubikNxN implements Serializable {

	private static final long serialVersionUID = 1L;

	public CuboRubik2x2(Integer idTipoCubo, String nombre) {
		this(idTipoCubo, nombre, Constantes.CUBO_2X2X2, 2);
	}

	private CuboRubik2x2(Integer idTipoCubo, String nombre, String parametro, int n) {
		super(idTipoCubo, nombre, parametro, n);
	}

}
