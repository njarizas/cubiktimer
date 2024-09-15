/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.controlador.facade;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cubiktimer.config.ConexionDatabase;
import com.cubiktimer.modelo.dao.ConfiguracionDAO;
import com.cubiktimer.modelo.dto.ConfiguracionDTO;

public class ConfiguracionFacade implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(ConexionDatabase.class);
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
	
	public ConfiguracionDTO obtenerEstiloVisualPreferidoPorIdUsuario(Integer idUsuario) {
		List<ConfiguracionDTO> lista = configuracionDAO.consultarConfiguracionPorIdUsuarioIdTipoYEstado(idUsuario, 28,
				1);
		if (lista.isEmpty()) {
			log.trace("El usuario con idUsuario " + idUsuario
					+ " no tiene configurado un estilo visual preferido (idTipo : 28)");
			return null;
		} else if (lista.size() > 1) {
			log.trace("El usuario con idUsuario " + idUsuario
					+ " tiene configurado mas de un estilo visual preferido (idTipo : 28)");
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
	
	public ConfiguracionDTO obtenerParametroGrabarVideoPorIdUsuario(Integer idUsuario) {
		List<ConfiguracionDTO> lista = configuracionDAO.consultarConfiguracionPorIdUsuarioIdTipoYEstado(idUsuario, 30,
				1);
		if (lista.isEmpty()) {
			log.trace(
					"El usuario con idUsuario " + idUsuario + " no tiene configurada un parametro de grabar video (idTipo : 30)");
			return null;
		} else if (lista.size() > 1) {
			log.trace("El usuario con idUsuario " + idUsuario
					+ " tiene configurado mas de un parametro de grabar video (idTipo : 30)");
		}
		return lista.get(0);
	}
	
	public ConfiguracionDTO obtenerParametroCapturarPantallaPorIdUsuario(Integer idUsuario) {
		List<ConfiguracionDTO> lista = configuracionDAO.consultarConfiguracionPorIdUsuarioIdTipoYEstado(idUsuario, 31,
				1);
		if (lista.isEmpty()) {
			log.trace(
					"El usuario con idUsuario " + idUsuario + " no tiene configurada un parametro de grabar video (idTipo : 31)");
			return null;
		} else if (lista.size() > 1) {
			log.trace("El usuario con idUsuario " + idUsuario
					+ " tiene configurado mas de un parametro de grabar video (idTipo : 31)");
		}
		return lista.get(0);
	}
	
	public ConfiguracionDTO obtenerParametroNotificarPBPorIdUsuario(Integer idUsuario) {
		List<ConfiguracionDTO> lista = configuracionDAO.consultarConfiguracionPorIdUsuarioIdTipoYEstado(idUsuario, 32,
				1);
		if (lista.isEmpty()) {
			log.trace(
					"El usuario con idUsuario " + idUsuario + " no tiene configurada un parametro de notificar PB (idTipo : 32)");
			return null;
		} else if (lista.size() > 1) {
			log.trace("El usuario con idUsuario " + idUsuario
					+ " tiene configurado mas de un parametro de notificar PB (idTipo : 32)");
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
