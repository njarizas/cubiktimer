package com.sigolf.juegos.rubik.modelo;

/**
 *
 * @author Nelson
 */
public abstract class CuboRubik {
	
	private Integer idTipoCubo;
	private String nombre;

    public CuboRubik(Integer idTipoCubo, String nombre) {
		super();
		this.idTipoCubo = idTipoCubo;
		this.nombre = nombre;
	}

	/**
     * Metodo que gira la cara de atrás en sentido de las manecillas del reloj
     * la cantidad de veces que reciba por parámetro
     * @param cant
     */
    public abstract void b(int cant);

    /**
     * Método que gira la cara de atrás una vez en sentido de las manecillas del
     * reloj
     */
    public abstract void b();

    /**
     * Metodo que gira la cara inferior en sentido de las manecillas del reloj
     * la cantidad de veces que reciba por parámetro
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
     * Método que gira el cubo de acuerdo al parámetro recibido
     *
     * @param giro
     * @return <code>boolean</code> valor que indica si el giro fue válido o no
     */
    public abstract boolean girar(String giro);

    /**
     * Metodo que gira la cara izquierda en sentido de las manecillas del reloj
     * la cantidad de veces que reciba por parámetro
     *
     * @param cant
     */
    public abstract void l(int cant);

    /**
     * Método que gira la cara izquierda una vez en sentido de las manecillas
     * del reloj
     */
    public abstract void l();

    /**
     * Método que mezcla el cubo recibiendo por parámetro un array de String con
     * los movimientos a realizar
     * @param mezcla Array de String con los movimientos a realizar
     * @return <code>String</code> Secuencia de mezcla eliminando los giros no
     * válidos
     */
    public abstract String mezclar(String[] mezcla);
    
    /**
     * Metdodo que genera un array con movimientos válidos para mezclar el cubo
     * @return
     */
    public abstract String[] generarMezcla();

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
     * Metodo que gira la cara superior en sentido de las manecillas del reloj
     * la cantidad de veces que reciba por parámetro
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
     * Metodo que gira el cubo en sentido de las manecillas del reloj sobre el
     * plano de la cara izquierda la cantidad de veces recibidas por parámetro
     *
     * @param cant
     */
    public abstract void x(int cant);

    /**
     * Metodo que gira el cubo una vez en sentido de las manecillas del reloj
     * sobre el plano de la cara izquierda
     */
    public abstract void x();

    /**
     * Metodo que gira el cubo en sentido de las manecillas del reloj sobre el
     * plano de la cara superior la cantidad de veces recibidas por parámetro
     *
     * @param cant
     */
    public abstract void y(int cant);

    /**
     * Metodo que gira el cubo una vez en sentido de las manecillas del reloj
     * sobre el plano de la cara superior
     */
    public abstract void y();

    /**
     * Metodo que gira el cubo en sentido de las manecillas del reloj sobre el
     * plano de la cara frontal la cantidad de veces recibidas por parámetro
     *
     * @param cant
     */
    public abstract void z(int cant);

    /**
     * Metodo que gira el cubo una vez en sentido de las manecillas del reloj
     * sobre el plano de la cara frontal
     */
    public abstract void z();

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
    
}
