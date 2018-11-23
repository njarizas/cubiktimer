package com.cubiktimer.modelo.rubik.estadisticas;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class Promedio implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Promedio.class);

	private Integer prom;
	private String tipoCubo;
	private String fecha;
	private Date date;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Integer getProm() {
		return prom;
	}

	public void setProm(Integer prom) {
		this.prom = prom;
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
			log.trace("La fecha obtenida no se pudo convertir: ");
			log.warn(e.getMessage());
		}
	}

	public Date getDate() {
		return date;
	}

	public String getFechaUTC() {
		return (date.getYear() + 1900) + "," + date.getMonth() + "," + date.getDate();
	}

}
