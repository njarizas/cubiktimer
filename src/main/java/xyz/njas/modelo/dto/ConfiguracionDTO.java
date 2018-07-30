package xyz.njas.modelo.dto;

import java.io.Serializable;
import java.util.Date;

public class ConfiguracionDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idConfiguracion;
	private Integer idUsuario;
	private Integer idTipo;
	private String valorTexto;
	private Integer valorEntero;
	private Double valorDecimal;
	private Date valorFecha;
	private Integer estado;
	
	public ConfiguracionDTO() {
		super();
		this.estado=1;
	}
	public Integer getIdConfiguracion() {
		return idConfiguracion;
	}
	public void setIdConfiguracion(Integer idConfiguracion) {
		this.idConfiguracion = idConfiguracion;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}
	public String getValorTexto() {
		return valorTexto;
	}
	public void setValorTexto(String valorTexto) {
		this.valorTexto = valorTexto;
	}
	public Integer getValorEntero() {
		return valorEntero;
	}
	public void setValorEntero(Integer valorEntero) {
		this.valorEntero = valorEntero;
	}
	public Double getValorDecimal() {
		return valorDecimal;
	}
	public void setValorDecimal(Double valorDecimal) {
		this.valorDecimal = valorDecimal;
	}
	public Date getValorFecha() {
		return valorFecha;
	}
	public void setValorFecha(Date valorFecha) {
		this.valorFecha = valorFecha;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
