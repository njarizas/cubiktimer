package com.cubiktimer.controlador.facade;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.modelo.dao.CredencialDAO;
import com.cubiktimer.modelo.dao.UsuarioDAO;
import com.cubiktimer.modelo.dao.UsuarioRolDAO;
import com.cubiktimer.modelo.dto.CredencialDTO;
import com.cubiktimer.modelo.dto.RolDTO;
import com.cubiktimer.modelo.dto.UsuarioDTO;
import com.cubiktimer.modelo.dto.UsuarioRolDTO;
import com.cubiktimer.modelo.dto.UsuarioRolPK;

public class UsuarioFacade implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UsuarioFacade.class);

	UsuarioDAO usuarioDAO;
	CredencialDAO credencialDAO;
	UsuarioRolDAO usuarioRolDAO;

	public UsuarioFacade() {
		super();
		usuarioDAO = new UsuarioDAO();
		credencialDAO = new CredencialDAO();
		usuarioRolDAO = new UsuarioRolDAO();
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

	public int create(UsuarioDTO dto, List<RolDTO> listaRoles) {
		if (dto == null || listaRoles == null) {
			return 0;
		}
		log.debug("inicio create");
		Integer idUsuario = usuarioDAO.merge(dto);
		for (RolDTO rol : listaRoles) {
			UsuarioRolDTO usuarioRolDTO = new UsuarioRolDTO();
			UsuarioRolPK pk = new UsuarioRolPK(idUsuario, rol.getIdRol());
			usuarioRolDTO.setUsuarioRolPK(pk);
			usuarioRolDTO.setEstado(1);
			usuarioRolDAO.create(usuarioRolDTO);
		}
		log.debug("fin create");
		return idUsuario.intValue();
	}

}
