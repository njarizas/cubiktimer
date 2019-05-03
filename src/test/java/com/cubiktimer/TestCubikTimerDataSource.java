package com.cubiktimer;

import java.util.List;

import org.junit.Test;

import com.cubiktimer.modelo.dao.UsuarioDAO;
import com.cubiktimer.modelo.dto.UsuarioDTO;

import junit.framework.TestCase;

public class TestCubikTimerDataSource extends TestCase {

	@Test
	public void testConexion() {
		for (int i = 0; i < 100; i++) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<UsuarioDTO> lista = usuarioDAO.consultarUsuarios();
			System.out.println(" " + lista);
		}
	}

}
