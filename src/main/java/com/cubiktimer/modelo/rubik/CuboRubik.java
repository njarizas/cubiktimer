package com.cubiktimer.modelo.rubik;

import java.io.Serializable;

/**
 *
 * @author Nelson
 */
public abstract class CuboRubik extends Puzzle implements Serializable {

	private static final long serialVersionUID = 1L;

	public CuboRubik(Integer idTipoCubo, String nombre, String parametro) {
		super(idTipoCubo, nombre, parametro);
	}

	/**
	 * Metodo que gira la cara de atrás en sentido de las manecillas del reloj la
	 * cantidad de veces que reciba por parámetro
	 * 
	 * @param cant
	 */
	public abstract void b(int cant);

	/**
	 * Método que gira la cara de atrás una vez en sentido de las manecillas del
	 * reloj
	 */
	public abstract void b();

	/**
	 * Metodo que gira la cara inferior en sentido de las manecillas del reloj la
	 * cantidad de veces que reciba por parámetro
	 *
	 * @param cant
	 */
	public abstract void d(int cant);

	/**
	 * Método que gira la cara inferior una vez en sentido de las manecillas del
	 * reloj
	 */
	public abstract void d();

	/**
	 * Metodo que gira la cara frontal en sentido de las manecillas del reloj la
	 * cantidad de veces que reciba por parámetro
	 *
	 * @param cant
	 */
	public abstract void f(int cant);

	/**
	 * Método que gira la cara frontal una vez en sentido de las manecillas del
	 * reloj
	 */
	public abstract void f();

	/**
	 * Metodo que gira la cara izquierda en sentido de las manecillas del reloj la
	 * cantidad de veces que reciba por parámetro
	 *
	 * @param cant
	 */
	public abstract void l(int cant);

	/**
	 * Método que gira la cara izquierda una vez en sentido de las manecillas del
	 * reloj
	 */
	public abstract void l();

	/**
	 * Metodo que gira la cara derecha en sentido de las manecillas del reloj la
	 * cantidad de veces que reciba por parámetro
	 *
	 * @param cant
	 */
	public abstract void r(int cant);

	/**
	 * Método que gira la cara derecha una vez en sentido de las manecillas del
	 * reloj
	 */
	public abstract void r();

	/**
	 * Metodo que gira la cara superior en sentido de las manecillas del reloj la
	 * cantidad de veces que reciba por parámetro
	 *
	 * @param cant
	 */
	public abstract void u(int cant);

	/**
	 * Método que gira la cara superior una vez en sentido de las manecillas del
	 * reloj
	 */
	public abstract void u();

	/**
	 * Metodo que gira el cubo en sentido de las manecillas del reloj sobre el plano
	 * de la cara izquierda la cantidad de veces recibidas por parámetro
	 *
	 * @param cant
	 */
	public abstract void x(int cant);

	/**
	 * Metodo que gira el cubo una vez en sentido de las manecillas del reloj sobre
	 * el plano de la cara izquierda
	 */
	public abstract void x();

	/**
	 * Metodo que gira el cubo en sentido de las manecillas del reloj sobre el plano
	 * de la cara superior la cantidad de veces recibidas por parámetro
	 *
	 * @param cant
	 */
	public abstract void y(int cant);

	/**
	 * Metodo que gira el cubo una vez en sentido de las manecillas del reloj sobre
	 * el plano de la cara superior
	 */
	public abstract void y();

	/**
	 * Metodo que gira el cubo en sentido de las manecillas del reloj sobre el plano
	 * de la cara frontal la cantidad de veces recibidas por parámetro
	 *
	 * @param cant
	 */
	public abstract void z(int cant);

	/**
	 * Metodo que gira el cubo una vez en sentido de las manecillas del reloj sobre
	 * el plano de la cara frontal
	 */
	public abstract void z();

}
