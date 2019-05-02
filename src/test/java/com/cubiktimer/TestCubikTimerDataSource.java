package com.cubiktimer;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.cubiktimer.config.CubikTimerDataSource;
import com.cubiktimer.modelo.dao.UsuarioDAO;
import com.cubiktimer.modelo.dto.UsuarioDTO;

import junit.framework.TestCase;

public class TestCubikTimerDataSource extends TestCase {
	
	private static final Logger log = Logger.getLogger(TestCubikTimerDataSource.class);

	@Test
	public void testConexion() {
		for (int i = 0; i < 100; i++) {
			try (java.sql.Connection con = CubikTimerDataSource.getConnection()) {
				assertNotNull("Probando conexion JDBC", con);
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				List<UsuarioDTO> lista = usuarioDAO.consultarUsuarios();
				System.out.println(con + " " + lista);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
	}

}
