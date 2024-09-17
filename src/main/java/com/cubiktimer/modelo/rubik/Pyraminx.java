/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cubiktimer.util.Constantes;

public class Pyraminx extends Puzzle implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(Pyraminx.class);

	public Pyraminx(Integer idTipoCubo, String nombre) {
		super(idTipoCubo, nombre, Constantes.PYRAMINX);
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
	public boolean girar(String giro) {
		giro = giro.toLowerCase().trim();
		if (!giro.equalsIgnoreCase("u") && !giro.equalsIgnoreCase("u'") && !giro.equalsIgnoreCase("r")
				&& !giro.equalsIgnoreCase("r'") && !giro.equalsIgnoreCase("l") && !giro.equalsIgnoreCase("l'")
				&& !giro.equalsIgnoreCase("b") && !giro.equalsIgnoreCase("b'")) {
			log.trace("giro no válido: " + giro);
			return false;
		}
		return true;
	}

}
