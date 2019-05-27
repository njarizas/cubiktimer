package com.cubiktimer.modelo.rubik;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.cubiktimer.modelo.dto.SesionRubikDTO;
import com.cubiktimer.util.Util;

public class SesionRubik implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Tiempo> tiempos;
	private List<SolucionFewestMoves> soluciones;
	private SesionRubikDTO sesionRubikDTO;

	public SesionRubik(Date fecha) {
		super();
		this.sesionRubikDTO = new SesionRubikDTO(fecha);
		tiempos = new ArrayList<>();
		soluciones = new ArrayList<>();
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
		if (tiempos.size() < n) {
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

	public String mediaFewest() {
		Integer acumulado = 0;
		Integer solucionesValidas = 0;
		String retorno = "--.--";
		for (SolucionFewestMoves solucion : soluciones) {
			if (!solucion.getFewestMoveDTO().getDnf()) {
				acumulado += solucion.getFewestMoveDTO().getLongitudSolucion();
				solucionesValidas++;
			}
		}
		if ((solucionesValidas != 0)) {
			try {
				retorno = Util.getDf().format(new BigDecimal(acumulado).divide(new BigDecimal(solucionesValidas)));
			} catch (ArithmeticException e) {
				retorno = Util.getDf().format(acumulado.doubleValue() / solucionesValidas.doubleValue());
			}
		}
		return retorno;
	}

	public String mo3FewestActual() {
		return moNFewestActual(3);
	}

	public String moNFewestActual(int n) {
		Integer acumulado = 0;
		if (soluciones.size() < n) {
			return "--.--";
		} else {
			List<SolucionFewestMoves> ultimasNSoluciones = new ArrayList<>(
					soluciones.subList(soluciones.size() - n, soluciones.size()));
			Collections.sort(ultimasNSoluciones);
			for (int i = 0; i <= n - 1; i++) {
				if (ultimasNSoluciones.get(i).getFewestMoveDTO().getDnf()) {
					return "DNF";
				}
				Integer estaSolucion = ultimasNSoluciones.get(i).getFewestMoveDTO().getLongitudSolucion();
				acumulado += estaSolucion;
			}
			return Util.getDf().format(acumulado / n);
		}
	}

	public String mejorFewest() {
		if (soluciones.isEmpty()) {
			return "--";
		} else {
			List<SolucionFewestMoves> ultimasSoluciones = new ArrayList<>(soluciones);
			Collections.sort(ultimasSoluciones);
			return ultimasSoluciones.get(0).getFewestMoveDTO().getDnf() ? "DNF"
					: ultimasSoluciones.get(0).getFewestMoveDTO().getLongitudSolucion().toString();
		}
	}

	public String peorFewest() {
		if (soluciones.isEmpty()) {
			return "--";
		} else {
			List<SolucionFewestMoves> ultimasSoluciones = new ArrayList<>(soluciones);
			Collections.sort(ultimasSoluciones);
			return ultimasSoluciones.get(soluciones.size() - 1).getFewestMoveDTO().getDnf() ? "DNF"
					: ultimasSoluciones.get(soluciones.size() - 1).getFewestMoveDTO().getLongitudSolucion().toString();
		}
	}

	public List<Tiempo> getTiempos() {
		return tiempos;
	}

	public void setTiempos(List<Tiempo> tiempos) {
		this.tiempos = tiempos;
	}

	public List<SolucionFewestMoves> getSoluciones() {
		return soluciones;
	}

	public void setSoluciones(List<SolucionFewestMoves> soluciones) {
		this.soluciones = soluciones;
	}

	public SesionRubikDTO getSesionRubikDTO() {
		return sesionRubikDTO;
	}

	public void setSesionRubikDTO(SesionRubikDTO sesionRubikDTO) {
		this.sesionRubikDTO = sesionRubikDTO;
	}

}
