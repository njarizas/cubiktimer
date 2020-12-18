/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import java.io.Serializable;
import java.util.Arrays;

import com.cubiktimer.util.Util;

/**
 * Clase que representa una cara de cubo rubik nxn
 * 
 * @author Nelson Ariza
 *
 */
public class CaraRubik implements Serializable {

	private static final long serialVersionUID = 1L;

	private int n;
	private Celda[][] cara;

	public CaraRubik(int n, String color) {
		super();
		this.n = n;
		this.cara = new Celda[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cara[i][j] = new Celda(color);
			}
		}
	}

	/**
	 * Metodo que se encarga de girar la carala cantidad de veces recibida por
	 * parametro en sentido de las agujas del reloj
	 * 
	 * @param <int>cantidadGiros<int> la cantidad de veces a girar teniendo en
	 *        cuenta que cada giro 90° en sentido de las agujas del reloj
	 */
	public void girarCara(int cantidadGiros) {
		for (int i = 0; i < cantidadGiros; i++) {
			girarCara();
		}
	}

	/**
	 * Metodo que se encarga de girar la cara una sola vez en sentido de las agujas
	 * del reloj
	 */
	public void girarCara() {
		for (int i = 0; i < this.n / 2; i++) {
			for (int j = i; j < this.n - 1 - i; j++) {
				Celda celdaTempo;
				celdaTempo = cara[i][j];
				cara[i][j] = cara[n - 1 - j][i];
				cara[n - 1 - j][i] = cara[n - i - 1][n - 1 - j];
				cara[n - i - 1][n - 1 - j] = cara[j][n - i - 1];
				cara[j][n - i - 1] = celdaTempo;
			}
		}
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public Celda[][] getCara() {
		return cara;
	}

	public void setCara(Celda[][] cara) {
		this.cara = cara;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(cara);
		result = prime * result + n;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CaraRubik other = (CaraRubik) obj;
		if (n != other.n) {
			return false;
		}
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (!cara[i][j].equals(other.cara[i][j])) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < cara[i].length; j++) {
				str.append((cara[i][j] != null) ? cara[i][j] + "\t" : "\t");
			}
			str.append("\n");
		}
		return str.toString();
	}
	
	public String faceletString() {
		StringBuilder str = new StringBuilder("");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < cara[i].length; j++) {
				str.append(Util.obtenerLetraDeCaraPorColor(cara[i][j].getCodigoColor()));
			}
		}
		return str.toString();
	}

	public boolean estaResuelto() {
		Celda aux = cara[0][0];
		for (Celda[] fila : cara) {
			for (Celda celda : fila) {
				if (!celda.equals(aux)) {
					return false;
				}
			}
		}
		return true;
	}

}
