package com.cubiktimer.controlador.managedbeans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.cubiktimer.util.Util;

@ManagedBean(name = "utilBean")
@ViewScoped
public class UtilBean {

	/**
	 * Metodo que retorna la fecha actual en formato de fecha corta
	 * 
	 * @return
	 */
	public String getHoyFormatoFechaCorta() {
		Util util = Util.getInstance();
		return util.getFormatoFechaCorta().format(new Date());
	}

}
