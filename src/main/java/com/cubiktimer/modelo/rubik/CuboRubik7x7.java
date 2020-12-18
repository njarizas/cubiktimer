/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.cubiktimer.util.Constantes;

public class CuboRubik7x7 extends CuboRubik6x6 implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(CuboRubik7x7.class);

	public CuboRubik7x7(Integer idTipoCubo, String nombre) {
		this(idTipoCubo, nombre, Constantes.CUBO_7X7X7, 7);
	}

	public CuboRubik7x7(Integer idTipoCubo, String nombre, String parametro, int n) {
		super(idTipoCubo, nombre, parametro, n);
	}

	@Override
	public boolean girar(String giro) {
		giro = giro.toLowerCase().trim();
		if (giro.equals("3bw")) {
			tresbw(1);
		} else if (giro.equals("3bw2")) {
			tresbw(2);
		} else if (giro.equals("3bw'")) {
			tresbw(3);
		} else if (giro.equals("3dw")) {
			tresdw(1);
		} else if (giro.equals("3dw2")) {
			tresdw(2);
		} else if (giro.equals("3dw'")) {
			tresdw(3);
		} else if (giro.equals("3lw")) {
			treslw(1);
		} else if (giro.equals("3lw2")) {
			treslw(2);
		} else if (giro.equals("3lw'")) {
			treslw(3);
		} else {
			log.trace("verificando si es giro de 6x6");
			return super.girar(giro);
		}
		log.trace("es un giro válido de 7x7: " + giro);
		return true;
	}

	public void tresbw(int cant) {
		for (int i = 0; i < cant; i++) {
			tresbw();
		}
	}

	public void tresbw() {
		bw();
		for (int i = 0; i < n; i++) {
			Celda aux = top.getCara()[2][i];
			top.getCara()[2][i] = right.getCara()[i][n - 3];
			right.getCara()[i][n - 3] = bottom.getCara()[n - 3][n - 1 - i];
			bottom.getCara()[n - 3][n - 1 - i] = left.getCara()[n - 1 - i][2];
			left.getCara()[n - 1 - i][2] = aux;
		}
	}

	public void tresdw(int cant) {
		for (int i = 0; i < cant; i++) {
			tresdw();
		}
	}

	public void tresdw() {
		dw();
		for (int i = 0; i < n; i++) {
			Celda aux = back.getCara()[n - 3][i];
			back.getCara()[n - 3][i] = right.getCara()[n - 3][i];
			right.getCara()[n - 3][i] = front.getCara()[n - 3][i];
			front.getCara()[n - 3][i] = left.getCara()[n - 3][i];
			left.getCara()[n - 3][i] = aux;
		}
	}

	public void treslw(int cant) {
		for (int i = 0; i < cant; i++) {
			treslw();
		}
	}

	public void treslw() {
		lw();
		for (int i = 0; i < n; i++) {
			Celda aux = top.getCara()[i][2];
			top.getCara()[i][2] = back.getCara()[n - 1 - i][n - 3];
			back.getCara()[n - 1 - i][n - 3] = bottom.getCara()[i][2];
			bottom.getCara()[i][2] = front.getCara()[i][2];
			front.getCara()[i][2] = aux;
		}
	}

}
