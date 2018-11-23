package com.cubiktimer.modelo.rubik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.cubiktimer.modelo.dto.SesionRubikDTO;
import com.cubiktimer.util.Util;

public class SesionRubik implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Tiempo> tiempos;
	private SesionRubikDTO sesionRubikDTO;

	public String promedio() {
		Integer acumulado = 0;
		Integer tiemposValidos=0;
		for (Tiempo tiempo : tiempos) {
			if (!tiempo.getTiempoRubikDTO().getDnf()) {
				acumulado += tiempo.getTiempoRubikDTO().getTiempoRealMilisegundos();
				tiemposValidos++;
			}
		}
		return ((tiemposValidos != 0) ? Util.darFormatoTiempo(acumulado / tiemposValidos) : "-:--.--");
	}

	public String ao5actual() {
		Integer acumulado = 0;
		if (tiempos.size() < 5) {
			return "-:--.--";
		} else {
			List<Tiempo> ultimosCincoTiempos = new ArrayList<>(
					tiempos.subList(tiempos.size() - 5, tiempos.size()));
			Collections.sort(ultimosCincoTiempos);
			for (int i = 1; i <= 3; i++) {
				if (ultimosCincoTiempos.get(i).getTiempoRubikDTO().getDnf()) {
					return "DNF";
				}
				Integer esteTiempo = ultimosCincoTiempos.get(i).getTiempoRubikDTO().getTiempoRealMilisegundos();
				acumulado += esteTiempo;
			}
			return ((!ultimosCincoTiempos.isEmpty()) ? Util.darFormatoTiempo(acumulado / 3) : "-:--.--");
		}
	}

	public String ao12actual() {
		Integer acumulado = 0;
		if (tiempos.size() < 12) {
			return "-:--.--";
		} else {
			List<Tiempo> ultimosDoceTiempos = new ArrayList<>(
					tiempos.subList(tiempos.size() - 12, tiempos.size()));
			Collections.sort(ultimosDoceTiempos);
			for (int i = 1; i <= 10; i++) {
				if (ultimosDoceTiempos.get(i).getTiempoRubikDTO().getDnf()) {
					return "DNF";
				}
				Integer esteTiempo = ultimosDoceTiempos.get(i).getTiempoRubikDTO().getTiempoRealMilisegundos();
				acumulado += esteTiempo;
			}
			return ((!ultimosDoceTiempos.isEmpty()) ? Util.darFormatoTiempo(acumulado / 10) : "-:--.--");
		}
	}

	public SesionRubik(Date fecha) {
		super();
		this.sesionRubikDTO = new SesionRubikDTO(fecha);
		tiempos = new ArrayList<>();
	}

	public SesionRubik(Date fecha, Integer idUsuario) {
		this(fecha);
		this.sesionRubikDTO.setIdUsuario(idUsuario);
	}

	public List<Tiempo> getTiempos() {
		return tiempos;
	}

	public void setTiempos(List<Tiempo> tiempos) {
		this.tiempos = tiempos;
	}

	public SesionRubikDTO getSesionRubikDTO() {
		return sesionRubikDTO;
	}

	public void setSesionRubikDTO(SesionRubikDTO sesionRubikDTO) {
		this.sesionRubikDTO = sesionRubikDTO;
	}

}
