package com.cubiktimer.controlador.facade;

import java.io.Serializable;
import java.util.List;

import com.cubiktimer.modelo.dao.SesionRubikDAO;
import com.cubiktimer.modelo.dao.TiempoRubikDAO;
import com.cubiktimer.modelo.dto.SesionRubikDTO;
import com.cubiktimer.modelo.dto.TiempoRubikDTO;
import com.cubiktimer.modelo.rubik.Tiempo;

public class RubikFacade implements Serializable {

	private static final long serialVersionUID = 1L;
	private SesionRubikDAO sesionRubikDAO;
	private TiempoRubikDAO tiempoRubikDAO;

	public RubikFacade() {
		super();
		sesionRubikDAO = new SesionRubikDAO();
		tiempoRubikDAO = new TiempoRubikDAO();
	}

	public int guardarRubik(SesionRubikDTO sesionRubikDTO, List<Tiempo> listaTiemposDTO) {
		int retorno = 0;
		int idSesion = guardarSesionRubik(sesionRubikDTO);
		retorno += idSesion;
		sesionRubikDTO.setIdSesion(idSesion);
		for (Tiempo tiempo : listaTiemposDTO) {
			tiempo.getTiempoRubikDTO().setIdSesion(idSesion);
			int idTiempo = guardarTiempoRubik(tiempo.getTiempoRubikDTO());
			retorno += idTiempo;
			tiempo.getTiempoRubikDTO().setIdTiempo(idTiempo);
		}
		return retorno;
	}

	public int guardarSesionRubik(SesionRubikDTO dto) {
		return sesionRubikDAO.merge(dto);
	}

	public int guardarTiempoRubik(TiempoRubikDTO dto) {
		return tiempoRubikDAO.merge(dto);
	}

}
