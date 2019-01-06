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
		//TODO implementar metodo para validar giros
//		giro = giro.toLowerCase().trim();
//		if (giro.equalsIgnoreCase("u")) {
//		} else if (giro.equalsIgnoreCase("u'")) {
//		} else if (giro.equalsIgnoreCase("r")) {
//		} else if (giro.equalsIgnoreCase("r'")) {
//		} else if (giro.equalsIgnoreCase("l")) {
//		} else if (giro.equalsIgnoreCase("l'")) {
//		} else if (giro.equalsIgnoreCase("b")) {
//		} else if (giro.equalsIgnoreCase("b'")) {
//		} else {
//			log.trace("giro no válido: "+giro);
//			return false;
//		}
		return true;
	}

}
