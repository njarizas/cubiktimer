package xyz.njas.modelo.rubik;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xyz.njas.modelo.dto.SesionRubikDTO;
import xyz.njas.util.Util;

public class SesionRubik {
	
	private List<Tiempo> tiempos;
	private SesionRubikDTO sesionRubikDTO;
	
	public String Promedio(){
		Integer acumulado=0;
		for (Tiempo tiempo : tiempos) {
			acumulado += tiempo.getTiempoRubikDTO().getTiempoMilisegundos();
		}
		return (String) ((tiempos.size()!=0)?Util.darFormatoTiempo(acumulado/tiempos.size()):"-:--.--");
	}
	
	public SesionRubik(Date fecha) {
		super();
		this.sesionRubikDTO = new SesionRubikDTO(fecha);
		tiempos = new ArrayList<Tiempo>();
	}
	
	public SesionRubik(Date fecha,Integer idUsuario) {
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
