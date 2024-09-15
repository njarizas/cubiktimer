/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cubiktimer.util.Constantes;

public class Square1 extends Puzzle implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(Square1.class);

	public Square1(Integer idTipoCubo, String nombre) {
		super(idTipoCubo, nombre, Constantes.SQUARE_1);
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
		if (giro.matches("^\\([\\-]{0,1}[0-6]{1},[\\-]{0,1}[0-6]{1}\\)$") || giro.equals("/")) {
			return true;
		}
		log.trace("giro no válido: " + giro);
		return false;
	}

}
