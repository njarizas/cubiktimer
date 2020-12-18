/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

/**
 *
 * @author Nelson
 */
public class Celda implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoColor;
	private String sigla;

	public Celda(String color) {
		this.codigoColor = color;
		this.sigla = color.substring(0, 3);
	}

	public String getCodigoColor() {
		return codigoColor;
	}

	public void setCodigoColor(String codigoColor) {
		this.codigoColor = codigoColor;
		this.sigla = codigoColor.substring(0, 3);
	}

	public String getSigla() {
		return sigla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoColor == null) ? 0 : codigoColor.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		Celda other = (Celda) obj;
		if (codigoColor == null) {
			if (other.codigoColor != null) {
				return false;
			}
		} else if (!codigoColor.equals(other.codigoColor)) {
			return false;
		}
		if (sigla == null) {
			if (other.sigla != null) {
				return false;
			}
		} else if (!sigla.equals(other.sigla)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return sigla;
	}

}
