package xyz.njas.rubik.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xyz.njas.dto.SesionRubikDTO;

public class SesionRubik {
	
	private List<Tiempo> tiempos;
	private SesionRubikDTO sesionRubikDTO;
	
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
