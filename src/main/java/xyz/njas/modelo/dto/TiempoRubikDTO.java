package xyz.njas.modelo.dto;

import java.io.Serializable;

import xyz.njas.util.Constantes;

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
	private String video;
	private String ip;
	private Integer estado;

	public TiempoRubikDTO() {
		super();
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
			//Cuando los dos son dnf entonces son iguales
			if (this.getDnf()) {
				return 0;
			} else {
				Integer este = this.getTiempoMilisegundos();
				Integer otro = otherTiempo.getTiempoMilisegundos();
				//se aplican penalizaciones si las tienen
				if (this.getPenalizacion()) {
					este += 2000;
				}
				if (otherTiempo.getPenalizacion()) {
					otro += 2000;
				}
				if (este<otro) {
					return -1;
				} else if (este>otro) {
					return 1;
				} else {
					return 0;
				}
			}
		} else { //uno es DNF y el otro no
			if (this.getDnf()) {
				return 1;
			} else {
				return -11;
			}
		}
	}

	@Override
	public String toString() {
		return "TiempoRubikDTO [mezcla=" + mezcla + ", tiempoMilisegundos="
				+ tiempoMilisegundos + ", tiempoTexto=" + tiempoTexto + ", dnf=" + dnf + ", penalizacion="
				+ penalizacion + "]";
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
	 * Metodo que retorna los milisegundos gastados sumandole 2000 en caso de que tenga penalización
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

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
}
