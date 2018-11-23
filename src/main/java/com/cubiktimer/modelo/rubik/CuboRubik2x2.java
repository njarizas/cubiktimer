package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import com.cubiktimer.util.ScrambleGenerator;

public class CuboRubik2x2 extends CuboRubikNxN implements Serializable {

	private static final long serialVersionUID = 1L;

	public CuboRubik2x2(Integer idTipoCubo, String nombre) {
		this(idTipoCubo, nombre, 2);
	}

	public CuboRubik2x2(Integer idTipoCubo, String nombre, int n) {
		super(idTipoCubo, nombre, n);
	}

	@Override
	public String[] generarMezcla() {
		return ScrambleGenerator.generarMezcla("222");
	}

}
