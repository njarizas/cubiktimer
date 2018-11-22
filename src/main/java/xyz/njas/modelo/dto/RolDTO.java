package xyz.njas.modelo.dto;

import java.io.Serializable;

public class RolDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idRol;
	private String nombreRol;
	private String descripcion;
	private Integer estado;

	public RolDTO() {
		super();
	}

	/**
	 * Constructor mínimo
	 * 
	 * @param idRol
	 * @param nombreRol
	 * @param estado
	 */
	public RolDTO(Integer idRol, String nombreRol, Integer estado) {
		super();
		this.idRol = idRol;
		this.nombreRol = nombreRol;
		this.estado = estado;
	}

	/**
	 * Constructor con todos los parametros
	 * 
	 * @param idRol
	 * @param nombreRol
	 * @param descripcion
	 * @param estado
	 */
	public RolDTO(Integer idRol, String nombreRol, String descripcion, Integer estado) {
		super();
		this.idRol = idRol;
		this.nombreRol = nombreRol;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRol == null) ? 0 : idRol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		RolDTO other = (RolDTO) obj;
		return idRol.equals(other.idRol);
	}

	@Override
	public String toString() {
		return this.idRol + " - " + this.nombreRol;
	}
	
	

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
