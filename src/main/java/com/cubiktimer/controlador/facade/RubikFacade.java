package com.cubiktimer.controlador.facade;

import java.io.Serializable;
import java.util.List;

import com.cubiktimer.modelo.dao.FewestMovesDAO;
import com.cubiktimer.modelo.dao.SesionRubikDAO;
import com.cubiktimer.modelo.dao.TiempoRubikDAO;
import com.cubiktimer.modelo.dto.FewestMovesDTO;
import com.cubiktimer.modelo.dto.SesionRubikDTO;
import com.cubiktimer.modelo.dto.TiempoRubikDTO;
import com.cubiktimer.modelo.rubik.SolucionFewestMoves;
import com.cubiktimer.modelo.rubik.Tiempo;

public class RubikFacade implements Serializable {

	private static final long serialVersionUID = 1L;
	private SesionRubikDAO sesionRubikDAO;
	private TiempoRubikDAO tiempoRubikDAO;
	private FewestMovesDAO fewestMovesDAO;

	public RubikFacade() {
		super();
		sesionRubikDAO = new SesionRubikDAO();
		tiempoRubikDAO = new TiempoRubikDAO();
		fewestMovesDAO = new FewestMovesDAO();
	}

	public int guardarRubik(SesionRubikDTO sesionRubikDTO, List<Tiempo> listaTiempos,
			List<SolucionFewestMoves> listaSoluciones) {
		int retorno = 0;
		int idSesion = guardarSesionRubik(sesionRubikDTO);
		retorno += idSesion;
		sesionRubikDTO.setIdSesion(idSesion);
		for (Tiempo tiempo : listaTiempos) {
			tiempo.getTiempoRubikDTO().setIdSesion(idSesion);
			int idTiempo = guardarTiempoRubik(tiempo.getTiempoRubikDTO());
			retorno += idTiempo;
			tiempo.getTiempoRubikDTO().setIdTiempo(idTiempo);
		}
		for (Tiempo tiempo : listaTiempos) {
			tiempo.getTiempoRubikDTO().setIdSesion(idSesion);
			int idTiempo = guardarTiempoRubik(tiempo.getTiempoRubikDTO());
			retorno += idTiempo;
			tiempo.getTiempoRubikDTO().setIdTiempo(idTiempo);
		}
		for (SolucionFewestMoves solucion : listaSoluciones) {
			solucion.getFewestMoveDTO().setIdSesion(idSesion);
			int idTiempo = guardarSolucionRubik(solucion.getFewestMoveDTO());
			retorno += idTiempo;
			solucion.getFewestMoveDTO().setIdFewestMove(idTiempo);
		}
		return retorno;
	}

	public int guardarSesionRubik(SesionRubikDTO dto) {
		return sesionRubikDAO.merge(dto);
	}

	public int guardarTiempoRubik(TiempoRubikDTO dto) {
		return tiempoRubikDAO.merge(dto);
	}

	public int guardarSolucionRubik(FewestMovesDTO dto) {
		return fewestMovesDAO.merge(dto);
	}

}
