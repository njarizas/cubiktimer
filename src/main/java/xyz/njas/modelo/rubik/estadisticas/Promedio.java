package xyz.njas.modelo.rubik.estadisticas;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Promedio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer promedio;
	private String tipoCubo;
	private String fecha;
	private Date date;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
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
		try {
			this.date = sdf.parse(fecha);
		} catch (ParseException e) {
			System.out.println("La fecha obtenida no se pudo convertir: "+e.getMessage());
		}
	}

	public Date getDate() {
		return date;
	}
	
	public String getFechaUTC(){
		return (date.getYear()+1900)+","+date.getMonth()+","+date.getDate();
	}

}
