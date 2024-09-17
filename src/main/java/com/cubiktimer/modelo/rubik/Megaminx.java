/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cubiktimer.util.Constantes;

public class Megaminx extends Puzzle implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(Megaminx.class);

	public Megaminx(Integer idTipoCubo, String nombre) {
		super(idTipoCubo, nombre, Constantes.MEGAMINX);
	}

	@Override
	public String mezclar(String[] mezcla) {
		StringBuilder secuenciaMezclada = new StringBuilder("");
		for (String giro : mezcla) {
			if (girar(giro)) {
				secuenciaMezclada.append(giro.toUpperCase() + " ");
			}
		}
		return secuenciaMezclada.toString();
	}

	@Override
	public boolean girar(String giro) {
		giro = giro.toLowerCase().trim();
		if (!giro.equals("d--") && !giro.equals("d++") && !giro.equals("r--") && !giro.equals("r++")
				&& !giro.equals("u") && !giro.equals("u'")) {
			log.trace("giro no válido: " + giro);
			return false;
		}
		return true;
	}

}
