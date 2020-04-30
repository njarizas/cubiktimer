package com.cubiktimer.modelo.dto;

import java.io.Serializable;

public class UsuarioRedSocialDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	public Integer idRegistro;
	public Integer idUsuario;
	public String idUsuarioRedSocial;
	public String sistema;

	public UsuarioRedSocialDTO() {
	}

	public UsuarioRedSocialDTO(Integer idRegistro, Integer idUsuario, String idUsuarioRedSocial, String sistema) {
		this.idRegistro = idRegistro;
		this.idUsuario = idUsuario;
		this.idUsuarioRedSocial = idUsuarioRedSocial;
		this.sistema = sistema;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("<UsuarioRedSocialDTO>");
		if (idRegistro != null) {
			builder.append("<idRegistro>").append(idRegistro).append("</idRegistro>");
		}
		if (idUsuario != null) {
			builder.append("<idUsuario>").append(idUsuario).append("</idUsuario>");
		}
		if (idUsuarioRedSocial != null) {
			builder.append("<idUsuarioRedSocial>").append(idUsuarioRedSocial).append("</idUsuarioRedSocial>");
		}
		if (sistema != null) {
			builder.append("<sistema>").append(sistema).append("</sistema>");
		}
		builder.append("</UsuarioRedSocialDTO>");
		return builder.toString();
	}

	public Integer getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Integer idRegistro) {
		this.idRegistro = idRegistro;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdUsuarioRedSocial() {
		return idUsuarioRedSocial;
	}

	public void setIdUsuarioRedSocial(String idUsuarioRedSocial) {
		this.idUsuarioRedSocial = idUsuarioRedSocial;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
}
