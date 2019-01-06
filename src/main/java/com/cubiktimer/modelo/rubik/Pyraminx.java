package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.cubiktimer.util.ScrambleGenerator;

public class Pyraminx extends Puzzle implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Pyraminx.class);

	public Pyraminx(Integer idTipoCubo, String nombre) {
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
		return ScrambleGenerator.generarMezcla("pyram");
	}

	@Override
	public boolean girar(String giro) {
		giro = giro.toLowerCase().trim();
		if (giro.equalsIgnoreCase("u")) {
		} else if (giro.equalsIgnoreCase("u'")) {
		} else if (giro.equalsIgnoreCase("r")) {
		} else if (giro.equalsIgnoreCase("r'")) {
		} else if (giro.equalsIgnoreCase("l")) {
		} else if (giro.equalsIgnoreCase("l'")) {
		} else if (giro.equalsIgnoreCase("b")) {
		} else if (giro.equalsIgnoreCase("b'")) {
		} else {
			log.trace("giro no válido: "+giro);
			return false;
		}
		return true;
	}

}
