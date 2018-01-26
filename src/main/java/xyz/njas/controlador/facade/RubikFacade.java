package xyz.njas.controlador.facade;

import java.util.List;

import xyz.njas.modelo.dao.SesionRubikDAO;
import xyz.njas.modelo.dao.TiempoRubikDAO;
import xyz.njas.modelo.dto.SesionRubikDTO;
import xyz.njas.modelo.dto.TiempoRubikDTO;
import xyz.njas.modelo.rubik.Tiempo;

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
