package com.cubiktimer.modelo.dto;

import java.io.Serializable;
import java.util.Date;

public class AhorcadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idAhorcado;
	private Integer idUsuario;
	private Date fecha;
	private String palabra;
	private String letrasUsadas;
	private Integer intentosSobrantes;
	private Boolean gano;
	private String ip;
	private Integer estado;

	public AhorcadoDTO() {
		super();
		this.estado=1;
	}

	public AhorcadoDTO(Date fecha, String palabra, Boolean gano, String letrasUsadas, Integer intentosSobrantes) {
		this();
		this.fecha = fecha;
		this.palabra = palabra;
		this.letrasUsadas = letrasUsadas;
		this.intentosSobrantes = intentosSobrantes;
		this.gano = gano;
	}

	public Integer getIdAhorcado() {
		return idAhorcado;
	}
	public void setIdAhorcado(Integer idAhorcado) {
		this.idAhorcado = idAhorcado;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getPalabra() {
		return palabra;
	}
	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}
	public Boolean getGano() {
		return gano;
	}
	public void setGano(Boolean gano) {
		this.gano = gano;
	}
	public String getLetrasUsadas() {
		return letrasUsadas;
	}
	public void setLetrasUsadas(String letrasUsadas) {
		this.letrasUsadas = letrasUsadas;
	}
	public Integer getIntentosSobrantes() {
		return intentosSobrantes;
	}
	public void setIntentosSobrantes(Integer intentosSobrantes) {
		this.intentosSobrantes = intentosSobrantes;
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
