/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik.estadisticas;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cubiktimer.error.ExceptionHandler;

public class Promedio implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(Promedio.class);

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
			ExceptionHandler.manejarExcepcionGrave(e);
		}
	}

	public Date getDate() {
		return date;
	}

	public String getFechaUTC() {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR) + "," + c.get(Calendar.MONTH) + "," + c.get(Calendar.DAY_OF_MONTH);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("<Promedio>");
		if (prom != null) {
			builder.append("<prom>").append(prom).append("</prom>");
		}
		if (tipoCubo != null) {
			builder.append("<tipoCubo>").append(tipoCubo).append("</tipoCubo>");
		}
		if (fecha != null) {
			builder.append("<fecha>").append(getFechaUTC()).append("</fecha>");
		}
		builder.append("</Promedio>");
		return builder.toString();
	}
	
	

}
