package com.cubiktimer.modelo.dto;

import java.io.Serializable;
import java.util.Date;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idUsuario;
	private String correo;
	private String sal;
	private String clave;
	private String nombres;
	private String apellidos;
	private Character sexo;
	private Date fechaNacimiento;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Integer estado;

	public UsuarioDTO() {
		super();
		Date ahora = new Date();
		this.fechaCreacion = ahora;
		this.fechaModificacion = ahora;
		this.estado = 1;
	}

	public UsuarioDTO(String correo, String clave) {
		super();
		this.correo = correo;
		this.clave = clave;
		Date ahora = new Date();
		this.fechaCreacion = ahora;
		this.fechaModificacion = ahora;
		this.estado = 1;
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

	public String getSal() {
		return sal;
	}

	public void setSal(String sal) {
		this.sal = sal;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
}
