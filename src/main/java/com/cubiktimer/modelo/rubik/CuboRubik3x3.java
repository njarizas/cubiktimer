package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import com.cubiktimer.util.Constantes;

public class CuboRubik3x3 extends CuboRubikNxN implements Serializable {

	private static final long serialVersionUID = 1L;

	public CuboRubik3x3(Integer idTipoCubo, String nombre) {
		this(idTipoCubo, nombre, Constantes.CUBO_3X3X3, 3);
	}

	private CuboRubik3x3(Integer idTipoCubo, String nombre, String parametro, int n) {
		super(idTipoCubo, nombre, parametro, n);
	}

}
