package com.cubiktimer.controlador.facade;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.modelo.dao.ConfiguracionDAO;
import com.cubiktimer.modelo.dto.ConfiguracionDTO;

public class ConfiguracionFacade implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ConfiguracionFacade.class);
	private ConfiguracionDAO configuracionDAO;

	public ConfiguracionFacade() {
		super();
		configuracionDAO = new ConfiguracionDAO();
	}

	public ConfiguracionDTO obtenerTiempoDeInspeccionPreferidoPorIdUsuario(Integer idUsuario) {
		List<ConfiguracionDTO> lista = configuracionDAO.consultarConfiguracionPorIdUsuarioIdTipoYEstado(idUsuario, 18,
				1);
		if (lista.isEmpty()) {
			log.trace("El usuario con idUsuario " + idUsuario
					+ " no tiene configurado un tiempo de inspeccion preferido (idTipo : 18)");
			return null;
		} else if (lista.size() > 1) {
			log.trace("El usuario con idUsuario " + idUsuario
					+ " tiene configurado mas de un tiempo de inspeccion preferido (idTipo : 18)");
		}
		return lista.get(0);
	}

	public ConfiguracionDTO obtenerTipoCuboPreferidoPorIdUsuario(Integer idUsuario) {
		List<ConfiguracionDTO> lista = configuracionDAO.consultarConfiguracionPorIdUsuarioIdTipoYEstado(idUsuario, 22,
				1);
		if (lista.isEmpty()) {
			log.trace(
					"El usuario con idUsuario " + idUsuario + " no tiene configurado un cubo preferido (idTipo : 22)");
			return null;
		} else if (lista.size() > 1) {
			log.trace("El usuario con idUsuario " + idUsuario
					+ " tiene configurado mas de un cubo preferido (idTipo : 22)");
		}
		return lista.get(0);
	}

	public ConfiguracionDTO obtenerIdiomaPreferidoPorIdUsuario(Integer idUsuario) {
		List<ConfiguracionDTO> lista = configuracionDAO.consultarConfiguracionPorIdUsuarioIdTipoYEstado(idUsuario, 19,
				1);
		if (lista.isEmpty()) {
			log.trace("El usuario con idUsuario " + idUsuario
					+ " no tiene configurado un idioma preferido (idTipo : 19)");
			return null;
		} else if (lista.size() > 1) {
			log.trace("El usuario con idUsuario " + idUsuario
					+ " tiene configurado mas de un idioma preferido (idTipo : 19)");
		}
		return lista.get(0);
	}

	public ConfiguracionDTO obtenerPaginaInicialPorIdUsuario(Integer idUsuario) {
		List<ConfiguracionDTO> lista = configuracionDAO.consultarConfiguracionPorIdUsuarioIdTipoYEstado(idUsuario, 23,
				1);
		if (lista.isEmpty()) {
			log.trace(
					"El usuario con idUsuario " + idUsuario + " no tiene configurada una página inicial (idTipo : 23)");
			return null;
		} else if (lista.size() > 1) {
			log.trace("El usuario con idUsuario " + idUsuario
					+ " tiene configurada mas de una página inicial (idTipo : 23)");
		}
		return lista.get(0);
	}

	public void guardar(List<ConfiguracionDTO> listaConfiguraciones) {
		for (ConfiguracionDTO configuracionDTO : listaConfiguraciones) {
			guardar(configuracionDTO);
		}
	}

	public void guardar(ConfiguracionDTO configuracionDTO) {
		int idConfiguracion = configuracionDAO.merge(configuracionDTO);
		configuracionDTO.setIdConfiguracion(idConfiguracion);
	}

	public ConfiguracionDAO getConfiguracionDAO() {
		return configuracionDAO;
	}

	public void setConfiguracionDAO(ConfiguracionDAO configuracionDAO) {
		this.configuracionDAO = configuracionDAO;
	}

}
