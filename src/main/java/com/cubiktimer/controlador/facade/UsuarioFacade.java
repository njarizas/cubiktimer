package com.cubiktimer.controlador.facade;

import java.io.Serializable;
import java.util.List;

import com.cubiktimer.modelo.dao.CredencialDAO;
import com.cubiktimer.modelo.dao.UsuarioDAO;
import com.cubiktimer.modelo.dto.CredencialDTO;
import com.cubiktimer.modelo.dto.UsuarioDTO;

public class UsuarioFacade implements Serializable {

	private static final long serialVersionUID = 1L;

	UsuarioDAO usuarioDAO;
	CredencialDAO credencialDAO;

	public UsuarioFacade() {
		super();
		usuarioDAO = new UsuarioDAO();
		credencialDAO = new CredencialDAO();
	}

	public String consultarCorreoActualPorCorreoAntiguo(String correoAntiguo) {
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
		return correoActual;
	}

}
