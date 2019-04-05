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

	public SesionRubik(Date fecha) {
		super();
		this.sesionRubikDTO = new SesionRubikDTO(fecha);
		tiempos = new ArrayList<>();
	}

	public SesionRubik(Date fecha, Integer idUsuario) {
		this(fecha);
		this.sesionRubikDTO.setIdUsuario(idUsuario);
	}

	/**
	 * Método que retorna el promedio aritmetico de los tiempos actuales, no tiene
	 * en cuenta los DNF y cuando tiene penalizacion se añaden 2 segundos al tiempo
	 * 
	 * @author Nelson Ariza
	 * @return El promedio Aritmetico de los tiempos de la sesion actual
	 */
	public String media() {
		Integer acumulado = 0;
		Integer tiemposValidos = 0;
		for (Tiempo tiempo : tiempos) {
			if (!tiempo.getTiempoRubikDTO().getDnf()) {
				acumulado += tiempo.getTiempoRubikDTO().getTiempoRealMilisegundos();
				tiemposValidos++;
			}
		}
		return ((tiemposValidos != 0) ? Util.darFormatoTiempo(acumulado / tiemposValidos) : "-:--.--");
	}

	/**
	 * Método que retorna el Ao5 (Average of 5) de los ultimos 5 tiempos actuales,
	 * tiene en cuenta los DNF y las penalizaciones
	 * 
	 * @author Nelson Ariza
	 * @return El Average of 5 de los ultimos 5 tiempos de la sesion actual
	 */
	public String ao5actual() {
		return aoNactual(5);
	}

	/**
	 * Método que retorna el Ao12 (Average of 12) de los ultimos 12 tiempos
	 * actuales, tiene en cuenta los DNF y las penalizaciones
	 * 
	 * @author Nelson Ariza
	 * @return El Average of 12 de los ultimos 12 tiempos de la sesion actual
	 */
	public String ao12actual() {
		return aoNactual(12);
	}

	public String mo3actual() {
		return moNactual(3);
	}

	/**
	 * Método que retorna el AoN (Average of N) de los ultimos N tiempos actuales,
	 * tiene en cuenta los DNF y las penalizaciones
	 * 
	 * @author Nelson Ariza
	 * @return El Average of N de los ultimos N tiempos de la sesion actual
	 */
	public String aoNactual(int n) {
		Integer acumulado = 0;
		if (n < 3 || tiempos.size() < n) {
			return "-:--.--";
		} else {
			List<Tiempo> ultimosNTiempos = new ArrayList<>(tiempos.subList(tiempos.size() - n, tiempos.size()));
			Collections.sort(ultimosNTiempos);
			for (int i = 1; i <= n - 2; i++) {
				if (ultimosNTiempos.get(i).getTiempoRubikDTO().getDnf()) {
					return "DNF";
				}
				Integer esteTiempo = ultimosNTiempos.get(i).getTiempoRubikDTO().getTiempoRealMilisegundos();
				acumulado += esteTiempo;
			}
			return Util.darFormatoTiempo(acumulado / (n - 2));
		}
	}

	/**
	 * Método que retorna la MoN (Mean of N) de los ultimos N tiempos actuales,
	 * tiene en cuenta los DNF y las penalizaciones
	 * 
	 * @author Nelson Ariza
	 * @return La media aritmetica de los ultimos N tiempos de la sesion actual
	 */
	public String moNactual(int n) {
		Integer acumulado = 0;
		if (tiempos.isEmpty()) {
			return "-:--.--";
		} else {
			List<Tiempo> ultimosNTiempos = new ArrayList<>(tiempos.subList(tiempos.size() - n, tiempos.size()));
			Collections.sort(ultimosNTiempos);
			for (int i = 0; i <= n - 1; i++) {
				if (ultimosNTiempos.get(i).getTiempoRubikDTO().getDnf()) {
					return "DNF";
				}
				Integer esteTiempo = ultimosNTiempos.get(i).getTiempoRubikDTO().getTiempoRealMilisegundos();
				acumulado += esteTiempo;
			}
			return Util.darFormatoTiempo(acumulado / n);
		}
	}

	public String mejor() {
		if (tiempos.isEmpty()) {
			return "-:--.--";
		} else {
			List<Tiempo> ultimosTiempos = new ArrayList<>(tiempos);
			Collections.sort(ultimosTiempos);
			return ultimosTiempos.get(0).getTiempoRubikDTO().getDnf() ? "DNF"
					: Util.darFormatoTiempo(ultimosTiempos.get(0).getTiempoRubikDTO().getTiempoRealMilisegundos());
		}
	}

	public String peor() {
		if (tiempos.isEmpty()) {
			return "-:--.--";
		} else {
			List<Tiempo> ultimosTiempos = new ArrayList<>(tiempos);
			Collections.sort(ultimosTiempos);
			return ultimosTiempos.get(tiempos.size() - 1).getTiempoRubikDTO().getDnf() ? "DNF"
					: Util.darFormatoTiempo(
							ultimosTiempos.get(tiempos.size() - 1).getTiempoRubikDTO().getTiempoRealMilisegundos());
		}
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
