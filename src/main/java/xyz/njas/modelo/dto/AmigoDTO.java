package xyz.njas.modelo.dto;

import java.io.Serializable;

public class AmigoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idAmistad;
	private Integer idUsuario;
	private Integer idAmigo;
	private Integer estado;

	public AmigoDTO() {
		super();
	}

	public Integer getIdAmistad() {
		return idAmistad;
	}

	public void setIdAmistad(Integer idAmistad) {
		this.idAmistad = idAmistad;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdAmigo() {
		return idAmigo;
	}

	public void setIdAmigo(Integer idAmigo) {
		this.idAmigo = idAmigo;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
