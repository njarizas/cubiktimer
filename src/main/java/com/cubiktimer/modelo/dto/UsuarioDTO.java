/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.dto;

import java.io.Serializable;
import java.util.Date;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idUsuario;
	private String correo;
	private String sal;
	private String clave;
	private String token;
	private String nombres;
	private String apellidos;
	private Character sexo;
	private Date fechaNacimiento;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Integer estado;

	public UsuarioDTO() {
		Date ahora = new Date();
		this.fechaCreacion = ahora;
		this.fechaModificacion = ahora;
		this.estado = 1;
	}

	public UsuarioDTO(String correo, String clave) {
		this.correo = correo;
		this.clave = clave;
		Date ahora = new Date();
		this.fechaCreacion = ahora;
		this.fechaModificacion = ahora;
		this.estado = 1;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("<UsuarioDTO>");
		if (idUsuario != null) {
			builder.append("<idUsuario>").append(idUsuario).append("</idUsuario>");
		}
		if (correo != null) {
			builder.append("<correo>").append(correo).append("</correo>");
		}
		if (sal != null) {
			builder.append("<sal>").append(sal).append("</sal>");
		}
		if (clave != null) {
			builder.append("<clave>").append(clave).append("</clave>");
		}
		if (token != null) {
			builder.append("<token>").append(token).append("</token>");
		}
		if (nombres != null) {
			builder.append("<nombres>").append(nombres).append("</nombres>");
		}
		if (apellidos != null) {
			builder.append("<apellidos>").append(apellidos).append("</apellidos>");
		}
		if (sexo != null) {
			builder.append("<sexo>").append(sexo).append("</sexo>");
		}
		if (fechaNacimiento != null) {
			builder.append("<fechaNacimiento>").append(fechaNacimiento).append("</fechaNacimiento>");
		}
		if (fechaCreacion != null) {
			builder.append("<fechaCreacion>").append(fechaCreacion).append("</fechaCreacion>");
		}
		if (fechaModificacion != null) {
			builder.append("<fechaModificacion>").append(fechaModificacion).append("</fechaModificacion>");
		}
		if (estado != null) {
			builder.append("<estado>").append(estado).append("</estado>");
		}
		builder.append("</UsuarioDTO>");
		return builder.toString();
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
