package xyz.njas.dto;

import java.io.Serializable;
import java.util.Date;

public class CredencialDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idCredencial;
	private Integer idUsuario;
	private String correo;
	private String clave;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer estado;

	public Integer getIdCredencial() {
		return idCredencial;
	}
	public void setIdCredencial(Integer idCredencial) {
		this.idCredencial = idCredencial;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
