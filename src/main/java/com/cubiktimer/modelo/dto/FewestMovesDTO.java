/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class FewestMovesDTO implements Serializable, Comparable<FewestMovesDTO> {

	private static final long serialVersionUID = 1L;

	private Integer idFewestMove;
	private Integer idSesion;
	private Integer idTipoCubo;
	private String mezcla;

	private Integer tiempoUsadoMilisegundos;
	private String tiempoRestanteTexto;
	private String solucion;
	private Integer longitudSolucion;
	private Boolean solucionValida;

	private Boolean dnf;
	private String comentario;
	private Date fecha;
	private Integer estado;

	public FewestMovesDTO() {
		super();
		this.fecha = new Date();
		this.dnf = false;
		this.estado = 1;
		this.longitudSolucion = 0;
		this.solucionValida = false;
	}

	public FewestMovesDTO(Integer idTipoCubo, String mezcla, Boolean dnf) {
		this();
		this.idTipoCubo = idTipoCubo;
		this.mezcla = mezcla;
		this.dnf = dnf;
	}

	public FewestMovesDTO(Integer idTipoCubo, String mezcla, Integer tiempoUsadoMilisegundos,
			String tiempoRestanteTexto, String solucion, Boolean dnf, String comentario) {
		this(idTipoCubo, mezcla, dnf);

		this.tiempoUsadoMilisegundos = tiempoUsadoMilisegundos;
		this.tiempoRestanteTexto = tiempoRestanteTexto;
		this.solucion = solucion;
		this.comentario = comentario;
	}

	/**
	 * Note: this class has a natural ordering that is inconsistent with equals.
	 */
	@Override
	public int compareTo(FewestMovesDTO otherFewestMove) {
		if (this.getDnf().equals(otherFewestMove.getDnf())) {
			// Cuando los dos son dnf entonces son iguales
			if (this.getDnf()) {
				return 0;
			} else {
				Integer este = this.longitudSolucion;
				Integer otro = otherFewestMove.longitudSolucion;
				if (este < otro) {
					return -1;
				} else if (este > otro) {
					return 1;
				} else {
					return 0;
				}
			}
		} else if (this.getDnf()) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(comentario, dnf, estado, fecha, idFewestMove, idSesion, idTipoCubo, longitudSolucion,
				mezcla, solucion, solucionValida, tiempoRestanteTexto, tiempoUsadoMilisegundos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FewestMovesDTO other = (FewestMovesDTO) obj;
		return Objects.equals(comentario, other.comentario) && Objects.equals(dnf, other.dnf)
				&& Objects.equals(estado, other.estado) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(idFewestMove, other.idFewestMove) && Objects.equals(idSesion, other.idSesion)
				&& Objects.equals(idTipoCubo, other.idTipoCubo)
				&& Objects.equals(longitudSolucion, other.longitudSolucion) && Objects.equals(mezcla, other.mezcla)
				&& Objects.equals(solucion, other.solucion) && Objects.equals(solucionValida, other.solucionValida)
				&& Objects.equals(tiempoRestanteTexto, other.tiempoRestanteTexto)
				&& Objects.equals(tiempoUsadoMilisegundos, other.tiempoUsadoMilisegundos);
	}

	@Override
	public String toString() {
		return "FewestMoveDTO [idFewestMove=" + idFewestMove + ", idSesion=" + idSesion + ", idTipoCubo=" + idTipoCubo
				+ ", mezcla=" + mezcla + ", solucion=" + solucion + ", tiempoUsadoMilisegundos="
				+ tiempoUsadoMilisegundos + ", tiempoRestanteTexto=" + tiempoRestanteTexto + ", solucionValida="
				+ solucionValida + ", dnf=" + dnf + ", comentario=" + comentario + ", fecha=" + fecha + ", estado="
				+ estado + "]";
	}

	public Integer getIdFewestMove() {
		return idFewestMove;
	}

	public void setIdFewestMove(Integer idFewestMove) {
		this.idFewestMove = idFewestMove;
	}

	public Integer getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(Integer idSesion) {
		this.idSesion = idSesion;
	}

	public Integer getIdTipoCubo() {
		return idTipoCubo;
	}

	public void setIdTipoCubo(Integer idTipoCubo) {
		this.idTipoCubo = idTipoCubo;
	}

	public String getMezcla() {
		return mezcla;
	}

	public void setMezcla(String mezcla) {
		this.mezcla = mezcla;
	}

	public Integer getTiempoUsadoMilisegundos() {
		return tiempoUsadoMilisegundos;
	}

	public void setTiempoUsadoMilisegundos(Integer tiempoUsadoMilisegundos) {
		this.tiempoUsadoMilisegundos = tiempoUsadoMilisegundos;
	}

	public String getTiempoRestanteTexto() {
		return tiempoRestanteTexto;
	}

	public void setTiempoRestanteTexto(String tiempoRestanteTexto) {
		this.tiempoRestanteTexto = tiempoRestanteTexto;
	}

	public String getSolucion() {
		return solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
		this.longitudSolucion = solucion.split(" ").length;
	}

	public Integer getLongitudSolucion() {
		return longitudSolucion;
	}

	public void setLongitudSolucion(Integer longitudSolucion) {
		this.longitudSolucion = longitudSolucion;
	}

	public Boolean getSolucionValida() {
		return solucionValida;
	}

	public void setSolucionValida(Boolean solucionValida) {
		this.solucionValida = solucionValida;
	}

	public Boolean getDnf() {
		return dnf;
	}

	public void setDnf(Boolean dnf) {
		this.dnf = dnf;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
