package xyz.njas.modelo.rubik;

import java.io.Serializable;

public abstract class Puzzle implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idTipoCubo;
	private String nombre;
	
	public Puzzle(Integer idTipoCubo, String nombre) {
		super();
		this.idTipoCubo = idTipoCubo;
		this.nombre = nombre;
	}
	
    /**
     * Método que gira el cubo de acuerdo al parámetro recibido
     * @param giro
     * @return <code>boolean</code> valor que indica si el giro fue válido o no
     */
    public abstract boolean girar(String giro);
    
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
