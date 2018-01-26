package xyz.njas.modelo.rubik.estadisticas;

import java.io.Serializable;

public class Promedio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer promedio;
	private String tipoCubo;
	private String fecha;
	public Integer getPromedio() {
		return promedio;
	}
	public void setPromedio(Integer promedio) {
		this.promedio = promedio;
	}
	public String getTipoCubo() {
		return tipoCubo;
	}
	public void setTipoCubo(String tipoCubo) {
		this.tipoCubo = tipoCubo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
