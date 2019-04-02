package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

import com.cubiktimer.util.ScrambleGenerator;

public abstract class Puzzle implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Id que identifica el tipo puzzle y el modo de competición
	 */
	private Integer idTipoCubo;

	/**
	 * Nombre que identifica el tipo puzzle y el modo de competición
	 */
	private String nombre;
	/**
	 * Parametro que se envía a TNoodle para generar mezcla
	 */
	private String parametro;

	public Puzzle(Integer idTipoCubo, String nombre, String parametro) {
		super();
		this.idTipoCubo = idTipoCubo;
		this.nombre = nombre;
		this.parametro = parametro;
	}

	/**
	 * Método que gira el cubo de acuerdo al parámetro recibido
	 * 
	 * @param giro
	 * @return <code>boolean</code> valor que indica si el giro fue válido o no
	 */
	public abstract boolean girar(String giro);

	/**
	 * Método que mezcla el cubo recibiendo por parámetro un array de String con los
	 * movimientos a realizar
	 * 
	 * @param mezcla Array de String con los movimientos a realizar
	 * @return <code>String</code> Secuencia de mezcla eliminando los giros no
	 *         válidos
	 */
	public abstract String mezclar(String[] mezcla);

	public abstract boolean estaResuelto();

	public String[] generarMezcla() {
		return ScrambleGenerator.generarMezcla(this.getParametro());
	}

	@Override
	public abstract boolean equals(Object obj);

	public Integer getIdTipoCubo() {
		return idTipoCubo;
	}

	public void setIdTipoCubo(Integer idTipoCubo) {
		this.idTipoCubo = idTipoCubo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

}
