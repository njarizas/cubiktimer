/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.cubiktimer.util.Constantes;

public class CuboRubik6x6 extends CuboRubik5x5 implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(CuboRubik6x6.class);

	public CuboRubik6x6(Integer idTipoCubo, String nombre) {
		this(idTipoCubo, nombre, Constantes.CUBO_6X6X6, 6);
	}

	protected CuboRubik6x6(Integer idTipoCubo, String nombre, String parametro, int n) {
		super(idTipoCubo, nombre, parametro, n);
	}

	@Override
	public boolean girar(String giro) {
		giro = giro.toLowerCase().trim();
		if (giro.equals("3fw")) {
			tresfw(1);
		} else if (giro.equals("3fw2")) {
			tresfw(2);
		} else if (giro.equals("3fw'")) {
			tresfw(3);
		} else if (giro.equals("3rw")) {
			tresrw(1);
		} else if (giro.equals("3rw2")) {
			tresrw(2);
		} else if (giro.equals("3rw'")) {
			tresrw(3);
		} else if (giro.equals("3uw")) {
			tresuw(1);
		} else if (giro.equals("3uw2")) {
			tresuw(2);
		} else if (giro.equals("3uw'")) {
			tresuw(3);
		} else {
			log.trace("verificando si es giro de 5x5");
			return super.girar(giro);
		}
		log.trace("es un giro válido de 6x6: " + giro);
		return true;
	}

	public void tresfw(int cant) {
		for (int i = 0; i < cant; i++) {
			tresfw();
		}
	}

	public void tresfw() {
		super.fw();
		for (int i = 0; i < n; i++) {
			Celda aux = top.getCara()[n - 3][i];
			top.getCara()[n - 3][i] = left.getCara()[n - 1 - i][n - 3];
			left.getCara()[n - 1 - i][n - 3] = bottom.getCara()[2][n - 1 - i];
			bottom.getCara()[2][n - 1 - i] = right.getCara()[i][2];
			right.getCara()[i][2] = aux;
		}
	}

	public void tresrw(int cant) {
		for (int i = 0; i < cant; i++) {
			tresrw();
		}
	}

	public void tresrw() {
		rw();
		for (int i = 0; i < n; i++) {
			Celda aux = top.getCara()[i][n - 3];
			top.getCara()[i][n - 3] = front.getCara()[i][n - 3];
			front.getCara()[i][n - 3] = bottom.getCara()[i][n - 3];
			bottom.getCara()[i][n - 3] = back.getCara()[n - 1 - i][2];
			back.getCara()[n - 1 - i][2] = aux;
		}
	}

	public void tresuw(int cant) {
		for (int i = 0; i < cant; i++) {
			tresuw();
		}
	}

	public void tresuw() {
		uw();
		for (int i = 0; i < n; i++) {
			Celda aux = left.getCara()[2][i];
			left.getCara()[2][i] = front.getCara()[2][i];
			front.getCara()[2][i] = right.getCara()[2][i];
			right.getCara()[2][i] = back.getCara()[2][i];
			back.getCara()[2][i] = aux;
		}
	}

}
