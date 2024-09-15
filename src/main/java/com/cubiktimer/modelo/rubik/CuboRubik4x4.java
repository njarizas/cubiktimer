/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cubiktimer.util.Constantes;

public class CuboRubik4x4 extends CuboRubikNxN implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CuboRubik4x4.class);

	public CuboRubik4x4(Integer idTipoCubo, String nombre) {
		this(idTipoCubo, nombre, Constantes.CUBO_4X4X4, 4);
	}

	protected CuboRubik4x4(Integer idTipoCubo, String nombre, String parametro, int n) {
		super(idTipoCubo, nombre, parametro, n);
	}

	@Override
	public boolean girar(String giro) {
		giro = giro.toLowerCase().trim();
		if (giro.equals("fw")) {
			fw(1);
		} else if (giro.equals("fw2")) {
			fw(2);
		} else if (giro.equals("fw'")) {
			fw(3);
		} else if (giro.equals("uw")) {
			uw(1);
		} else if (giro.equals("uw2")) {
			uw(2);
		} else if (giro.equals("uw'")) {
			uw(3);
		} else if (giro.equals("rw")) {
			rw(1);
		} else if (giro.equals("rw2")) {
			rw(2);
		} else if (giro.equals("rw'")) {
			rw(3);
		} else {
			log.trace("verificando si es giro de 2x2 o 3x3");
			return super.girar(giro);
		}
		log.trace("es un giro válido de 4x4: " + giro);
		return true;
	}

	public void fw(int cant) {
		for (int i = 0; i < cant; i++) {
			fw();
		}
	}

	public void fw() {
		f();
		for (int i = 0; i < n; i++) {
			Celda aux = top.getCara()[n - 2][i];
			top.getCara()[n - 2][i] = left.getCara()[n - 1 - i][n - 2];
			left.getCara()[n - 1 - i][n - 2] = bottom.getCara()[1][n - 1 - i];
			bottom.getCara()[1][n - 1 - i] = right.getCara()[i][1];
			right.getCara()[i][1] = aux;
		}
	}

	public void rw(int cant) {
		for (int i = 0; i < cant; i++) {
			rw();
		}
	}

	public void rw() {
		r();
		for (int i = 0; i < n; i++) {
			Celda aux = top.getCara()[i][n - 2];
			top.getCara()[i][n - 2] = front.getCara()[i][n - 2];
			front.getCara()[i][n - 2] = bottom.getCara()[i][n - 2];
			bottom.getCara()[i][n - 2] = back.getCara()[n - 1 - i][1];
			back.getCara()[n - 1 - i][1] = aux;
		}
	}

	public void uw(int cant) {
		for (int i = 0; i < cant; i++) {
			uw();
		}
	}

	public void uw() {
		u();
		for (int i = 0; i < n; i++) {
			Celda aux = left.getCara()[1][i];
			left.getCara()[1][i] = front.getCara()[1][i];
			front.getCara()[1][i] = right.getCara()[1][i];
			right.getCara()[1][i] = back.getCara()[1][i];
			back.getCara()[1][i] = aux;
		}
	}

}
