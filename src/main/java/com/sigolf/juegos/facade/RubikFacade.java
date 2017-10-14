package com.sigolf.juegos.facade;

import java.util.List;

import com.sigolf.juegos.dao.SesionRubikDAO;
import com.sigolf.juegos.dao.TiempoRubikDAO;
import com.sigolf.juegos.dto.SesionRubikDTO;
import com.sigolf.juegos.dto.TiempoRubikDTO;
import com.sigolf.juegos.rubik.modelo.Tiempo;

public class RubikFacade {

	private SesionRubikDAO sesionRubikDAO;
	private TiempoRubikDAO tiempoRubikDAO;

	public RubikFacade() {
		super();
		sesionRubikDAO = new SesionRubikDAO();
		tiempoRubikDAO = new TiempoRubikDAO();
	}

	public int guardarRubik(SesionRubikDTO sesionRubikDTO,
			List<Tiempo> listaTiemposDTO){
		int retorno=0;
		int idSesion=guardarSesionRubik(sesionRubikDTO);
			retorno+=idSesion;
		sesionRubikDTO.setIdSesion(idSesion);
		for (Tiempo tiempo : listaTiemposDTO) {
			tiempo.getTiempoRubikDTO().setIdSesion(idSesion);
			int idTiempo=guardarTiempoRubik(tiempo.getTiempoRubikDTO());
			retorno+=idTiempo;
			tiempo.getTiempoRubikDTO().setIdTiempo(idTiempo);
		}
		return retorno;
	}

	public int guardarSesionRubik(SesionRubikDTO dto){
		return sesionRubikDAO.merge(dto);
	}

	public int guardarTiempoRubik(TiempoRubikDTO dto){
		return tiempoRubikDAO.merge(dto);
	}

}
