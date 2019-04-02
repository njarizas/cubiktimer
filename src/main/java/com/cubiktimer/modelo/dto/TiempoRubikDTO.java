package com.cubiktimer.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import com.cubiktimer.util.Constantes;

public class TiempoRubikDTO implements Serializable, Comparable<TiempoRubikDTO> {

	private static final long serialVersionUID = 1L;

	private Integer idTiempo;
	private Integer idSesion;
	private Integer idTipoCubo;
	private String mezcla;
	private Integer tiempoInspeccionSegundos;
	private Integer tiempoInspeccionUsadoMilisegundos;
	private String tiempoInspeccionUsadoTexto;
	private Integer tiempoMilisegundos;
	private String tiempoTexto;
	private Boolean dnf;
	private Boolean penalizacion;
	private String comentario;
	private Date fecha;
	private Integer estado;

	public TiempoRubikDTO() {
		super();
		this.fecha = new Date();
		this.dnf = false;
		this.penalizacion = false;
		this.estado = 1;
	}

	public TiempoRubikDTO(Integer idTipoCubo, String mezcla, Integer tiempoInspeccionSegundos,
			Integer tiempoInspeccionUsadoMilisegundos, String tiempoInspeccionUsadoTexto, Integer tiempoMilisegundos,
			String tiempoTexto, Boolean dnf, Boolean penalizacion) {
		this();
		this.idTipoCubo = idTipoCubo;
		this.mezcla = mezcla;
		this.tiempoInspeccionSegundos = tiempoInspeccionSegundos;
		this.tiempoInspeccionUsadoMilisegundos = tiempoInspeccionUsadoMilisegundos;
		this.tiempoInspeccionUsadoTexto = tiempoInspeccionUsadoTexto;
		this.tiempoMilisegundos = tiempoMilisegundos;
		this.tiempoTexto = tiempoTexto;
		this.dnf = dnf;
		this.penalizacion = penalizacion;
	}

	@Override
	public int compareTo(TiempoRubikDTO otherTiempo) {
		if (this.getDnf().equals(otherTiempo.getDnf())) {
			// Cuando los dos son dnf entonces son iguales
			if (this.getDnf()) {
				return 0;
			} else {
				Integer este = aplicarPenalizacion(this);
				Integer otro = aplicarPenalizacion(otherTiempo);
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
		final int prime = 31;
		int result = 1;
		result = prime * result + aplicarPenalizacion(this);
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
		TiempoRubikDTO other = (TiempoRubikDTO) obj;
		return aplicarPenalizacion(this) == aplicarPenalizacion(other);

	}

	private Integer aplicarPenalizacion(TiempoRubikDTO tiempoRubikDTO) {
		if (tiempoRubikDTO.dnf) {
			return Integer.MAX_VALUE;
		} else if (tiempoRubikDTO.penalizacion) {
			return tiempoRubikDTO.getTiempoMilisegundos() + Constantes.MILISEGUNDOS_PENALIZACION;
		} else {
			return tiempoRubikDTO.getTiempoMilisegundos();
		}
	}

	@Override
	public String toString() {
		return "TiempoRubikDTO [mezcla=" + mezcla + ", tiempoMilisegundos=" + tiempoMilisegundos + ", tiempoTexto="
				+ tiempoTexto + ", dnf=" + dnf + ", penalizacion=" + penalizacion + "]";
	}

	public Integer getIdTiempo() {
		return idTiempo;
	}

	public void setIdTiempo(Integer idTiempo) {
		this.idTiempo = idTiempo;
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

	public Integer getTiempoInspeccionUsadoMilisegundos() {
		return tiempoInspeccionUsadoMilisegundos;
	}

	public void setTiempoInspeccionUsadoMilisegundos(Integer tiempoInspeccionUsadoMilisegundos) {
		this.tiempoInspeccionUsadoMilisegundos = tiempoInspeccionUsadoMilisegundos;
	}

	public String getTiempoInspeccionUsadoTexto() {
		return tiempoInspeccionUsadoTexto;
	}

	public void setTiempoInspeccionUsadoTexto(String tiempoInspeccionUsadoTexto) {
		this.tiempoInspeccionUsadoTexto = tiempoInspeccionUsadoTexto;
	}

	public Integer getTiempoMilisegundos() {
		return tiempoMilisegundos;
	}

	/**
	 * Metodo que retorna los milisegundos gastados sumandole 2000 en caso de que
	 * tenga penalización
	 * 
	 * @return
	 */
	public Integer getTiempoRealMilisegundos() {
		if (this.getPenalizacion()) {
			return tiempoMilisegundos + Constantes.MILISEGUNDOS_PENALIZACION;
		}
		return tiempoMilisegundos;
	}

	public void setTiempoMilisegundos(Integer tiempoMilisegundos) {
		this.tiempoMilisegundos = tiempoMilisegundos;
	}

	public String getTiempoTexto() {
		return tiempoTexto;
	}

	public void setTiempoTexto(String tiempoTexto) {
		this.tiempoTexto = tiempoTexto;
	}

	public Boolean getDnf() {
		return dnf;
	}

	public void setDnf(Boolean dnf) {
		this.dnf = dnf;
	}

	public Boolean getPenalizacion() {
		return penalizacion;
	}

	public void setPenalizacion(Boolean penalizacion) {
		this.penalizacion = penalizacion;
	}

	public Integer getTiempoInspeccionSegundos() {
		return tiempoInspeccionSegundos;
	}

	public void setTiempoInspeccionSegundos(Integer tiempoInspeccionSegundos) {
		this.tiempoInspeccionSegundos = tiempoInspeccionSegundos;
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
