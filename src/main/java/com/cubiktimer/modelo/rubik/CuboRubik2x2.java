package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.cubiktimer.util.ScrambleGenerator;

public class CuboRubik2x2 extends CuboRubikNxN implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(CuboRubik2x2.class);

	public CuboRubik2x2(Integer idTipoCubo, String nombre) {
		super(idTipoCubo, nombre, 2);
	}

	@Override
	public String[] generarMezcla() {
		return ScrambleGenerator.generarMezcla("222");
	}

	@Override
	public boolean girosAdicionales(String giro) {
		log.trace("giro no válido para 2x2x2");
		return false;
	}

}
