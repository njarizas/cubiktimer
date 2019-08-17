package com.cubiktimer.controlador.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.surebets.MainWplay;

@ManagedBean
@ViewScoped
public class BeatsManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public BeatsManagedBean() {

	}

	public String obtenerSurebets() {
		return "".equals(MainWplay.generarSurebets(100, false)) ? "No hay surebets"
				: MainWplay.generarSurebets(100, false);
	}

}
