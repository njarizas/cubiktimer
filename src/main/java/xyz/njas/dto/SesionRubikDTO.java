package xyz.njas.dto;

import java.io.Serializable;
import java.util.Date;

public class SesionRubikDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idSesion;
	private Integer idUsuario;
	private Date fecha;
	private String ip;
	private Integer estado;
	
	public SesionRubikDTO() {
		super();
		this.estado=1;
	}
	
	public SesionRubikDTO(Date fecha) {
		this();
		this.fecha = fecha;
	}

	public Integer getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(Integer idSesion) {
		this.idSesion = idSesion;
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
