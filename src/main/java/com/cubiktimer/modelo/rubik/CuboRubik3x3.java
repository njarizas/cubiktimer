package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import com.cubiktimer.util.ScrambleGenerator;

public class CuboRubik3x3 extends CuboRubikNxN implements Serializable {

	private static final long serialVersionUID = 1L;

	public CuboRubik3x3(Integer idTipoCubo, String nombre) {
		this(idTipoCubo, nombre, 3);
	}

	public CuboRubik3x3(Integer idTipoCubo, String nombre, int n) {
		super(idTipoCubo, nombre, n);
	}

	@Override
	public String[] generarMezcla() {
		return ScrambleGenerator.generarMezcla("333");
	}

}
