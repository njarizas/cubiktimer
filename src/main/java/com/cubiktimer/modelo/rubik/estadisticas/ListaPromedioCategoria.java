/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik.estadisticas;

import java.io.Serializable;
import java.util.List;

public class ListaPromedioCategoria implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Promedio> lista;

	public List<Promedio> getLista() {
		return lista;
	}

	public void setLista(List<Promedio> lista) {
		this.lista = lista;
	}

	public String obtenerFechas() {
		StringBuilder retorno = new StringBuilder("");
		for (int i = 0; i < lista.size(); i++) {
			if (i != 0) {
				retorno.append(",");
			}
			retorno.append("'").append(lista.get(i).getFecha()).append("'");
		}
		return retorno.toString();
	}

	public String obtenerDatosUTC() {
		StringBuilder retorno = new StringBuilder("{" + " name: '").append(obtenerTipoCubo()).append("'," + " data: [");
		for (int i = 0; i < lista.size(); i++) {
			if (i != 0) {
				retorno.append(",");
			}
			retorno.append("[Date.UTC(").append(lista.get(i).getFechaUTC()).append("),").append(lista.get(i).getProm())
					.append("]");
		}
		retorno.append("]" + "}");
		return retorno.toString();
	}

	public String obtenerPromedios() {
		StringBuilder retorno = new StringBuilder("");
		for (int i = 0; i < lista.size(); i++) {
			if (i != 0) {
				retorno.append(",");
			}
			retorno.append(lista.get(i).getProm());
		}
		return retorno.toString();
	}

	public String obtenerTipoCubo() {
		if (!lista.isEmpty()) {
			return lista.get(0).getTipoCubo();
		}
		return "";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("<ListaPromedioCategoria>");
		if (lista != null) {
			builder.append("<lista>");
			for (Promedio promedio : lista) {
				builder.append(promedio);
			}
			builder.append("</lista>");
		}
		builder.append("</ListaPromedioCategoria>");
		return builder.toString();
	}

}
