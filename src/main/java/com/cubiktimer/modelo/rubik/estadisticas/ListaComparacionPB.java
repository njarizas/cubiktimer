/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik.estadisticas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cubiktimer.modelo.dao.UsuarioRolDAO;

public class ListaComparacionPB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UsuarioRolDAO.class);

	private String nombreUsuario;
	private String nombreAmigo;

	private List<ComparacionPB> listaComparacionPB;

	public ListaComparacionPB() {
		listaComparacionPB = new ArrayList<>();
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreAmigo() {
		return nombreAmigo;
	}

	public void setNombreAmigo(String nombreAmigo) {
		this.nombreAmigo = nombreAmigo;
	}

	public List<ComparacionPB> getListaComparacionPB() {
		return listaComparacionPB;
	}

	public void setListaComparacionPB(List<ComparacionPB> listaComparacionPB) {
		this.listaComparacionPB = listaComparacionPB;
	}

	public String obtenerNombreCategorias() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < listaComparacionPB.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append("'").append(listaComparacionPB.get(i).getRecordPropio().getNombrePuzzle()).append("'");
		}
		return sb.toString();
	}

	public String obtenerDatosPropios() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < listaComparacionPB.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			try {
				Integer mejorTiempo = listaComparacionPB.get(i).getMejorTiempo();
				Integer peorTiempo = listaComparacionPB.get(i).getPeorTiempo();
				Double valor = 0d;
				if (listaComparacionPB.get(i).getRecordPropio().getPbNumero() > 0) {
					if (peorTiempo != 0 && mejorTiempo != 0 && peorTiempo != mejorTiempo) {
						valor = Math.floor(((-100.0d / (peorTiempo - mejorTiempo))
								* listaComparacionPB.get(i).getRecordPropio().getPbNumero()) + 100
								+ ((mejorTiempo * 100.0d) / (peorTiempo - mejorTiempo)));
					}
				}
				sb.append("" + valor);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return sb.toString();
	}

	public String obtenerDatosAmigo() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < listaComparacionPB.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			try {
				Integer mejorTiempo = listaComparacionPB.get(i).getMejorTiempo();
				Integer peorTiempo = listaComparacionPB.get(i).getPeorTiempo();
				Double valor = 0d;
				if (listaComparacionPB.get(i).getRecordAmigo().getPbNumero() > 0) {
					if (peorTiempo != 0 && mejorTiempo != 0 && peorTiempo != mejorTiempo) {
						valor = Math.floor(((-100.0d / (peorTiempo - mejorTiempo))
								* listaComparacionPB.get(i).getRecordAmigo().getPbNumero()) + 100
								+ ((mejorTiempo * 100.0d) / (peorTiempo - mejorTiempo)));
					}
				}
				sb.append("" + valor);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("<ListaComparacionPB>");
		if (nombreUsuario != null) {
			builder.append("<nombreUsuario>").append(nombreUsuario).append("</nombreUsuario>");
		}
		if (nombreAmigo != null) {
			builder.append("<nombreAmigo>").append(nombreAmigo).append("</nombreAmigo>");
		}
		if (listaComparacionPB != null) {
			builder.append("<listaComparacionPB>");
			for (ComparacionPB comparacionPB : listaComparacionPB) {
				builder.append(comparacionPB);
			}
			builder.append("</listaComparacionPB>");
		}
		builder.append("</ListaComparacionPB>");
		return builder.toString();
	}

}
