package xyz.njas.controlador.facade;

import java.util.List;

import xyz.njas.modelo.dao.CredencialDAO;
import xyz.njas.modelo.dao.UsuarioDAO;
import xyz.njas.modelo.dto.CredencialDTO;
import xyz.njas.modelo.dto.UsuarioDTO;

public class UsuarioFacade {
	
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
		lc=credencialDAO.consultarCredencialPorCorreo(correoAntiguo);
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
