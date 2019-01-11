package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.cubiktimer.util.ScrambleGenerator;

public class Square1 extends Puzzle implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Square1.class);

	public Square1(Integer idTipoCubo, String nombre) {
		super(idTipoCubo, nombre);
	}

	@Override
	public String mezclar(String[] mezcla) {
		StringBuilder secuenciaMezclada = new StringBuilder("");
		for (String giro : mezcla) {
			if (girar(giro)) {
				secuenciaMezclada.append(giro + " ");
			}
		}
		return secuenciaMezclada.toString();
	}

	@Override
	public String[] generarMezcla() {
		return ScrambleGenerator.generarMezcla("sq1");
	}

	@Override
	public boolean girar(String giro) {
		if (giro.matches("^\\([\\-]{0,1}[0-6]{1},[\\-]{0,1}[0-6]{1}\\)$") || giro.equals("/")) {
			return true;
		}
		log.debug("giro no válido: " + giro);
		return false;
	}

}