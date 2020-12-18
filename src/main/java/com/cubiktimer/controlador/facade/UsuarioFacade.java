/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.controlador.facade;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.modelo.dao.CredencialDAO;
import com.cubiktimer.modelo.dao.UsuarioDAO;
import com.cubiktimer.modelo.dao.UsuarioRedSocialDAO;
import com.cubiktimer.modelo.dao.UsuarioRolDAO;
import com.cubiktimer.modelo.dto.CredencialDTO;
import com.cubiktimer.modelo.dto.RolDTO;
import com.cubiktimer.modelo.dto.UsuarioDTO;
import com.cubiktimer.modelo.dto.UsuarioRedSocialDTO;
import com.cubiktimer.modelo.dto.UsuarioRolDTO;
import com.cubiktimer.modelo.dto.UsuarioRolPK;

public class UsuarioFacade implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UsuarioFacade.class);

	UsuarioDAO usuarioDAO;
	CredencialDAO credencialDAO;
	UsuarioRolDAO usuarioRolDAO;
	UsuarioRedSocialDAO usuarioRedSocialDAO;

	public UsuarioFacade() {
		super();
		usuarioDAO = new UsuarioDAO();
		credencialDAO = new CredencialDAO();
		usuarioRolDAO = new UsuarioRolDAO();
		usuarioRedSocialDAO = new UsuarioRedSocialDAO();
	}

	public String consultarCorreoActualPorCorreoAntiguo(String correoAntiguo) {
		log.debug("inicio consultarCorreoActualPorCorreoAntiguo");
		int idUsuario;
		String correoActual = null;
		List<CredencialDTO> lc;
		lc = credencialDAO.consultarCredencialPorCorreo(correoAntiguo);
		if (!lc.isEmpty()) {
			idUsuario = lc.get(0).getIdUsuario();
			List<UsuarioDTO> lu = usuarioDAO.consultarUsuarioPorIdUsuario(idUsuario);
			if (!lu.isEmpty()) {
				UsuarioDTO u = lu.get(0);
				correoActual = u.getCorreo();
			}
		}
		log.debug("fin consultarCorreoActualPorCorreoAntiguo");
		return correoActual;
	}

	public int create(UsuarioDTO dto, List<RolDTO> listaRoles, UsuarioRedSocialDTO ursDTO) {
		if (dto == null || listaRoles == null) {
			return 0;
		}
		log.debug("inicio create");
		Integer idUsuario = usuarioDAO.merge(dto);
		if (idUsuario != 0) {
			for (RolDTO rol : listaRoles) {
				UsuarioRolDTO usuarioRolDTO = new UsuarioRolDTO();
				UsuarioRolPK pk = new UsuarioRolPK(idUsuario, rol.getIdRol());
				usuarioRolDTO.setUsuarioRolPK(pk);
				usuarioRolDTO.setEstado(1);
				usuarioRolDAO.create(usuarioRolDTO);
			}
			if (ursDTO != null) {
				ursDTO.setIdUsuario(idUsuario);
				Integer idRegistro = usuarioRedSocialDAO.create(ursDTO);
				if (idRegistro == 0) {
					log.warn("falló al crear o actualizar el usuario de red social:\n" + ursDTO);
				}
			}
		} else {
			log.warn("falló al crear o actualizar el usuario:\n" + dto);
		}
		log.debug("fin create");
		return idUsuario.intValue();
	}

}
