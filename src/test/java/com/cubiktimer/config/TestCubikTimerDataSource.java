package com.cubiktimer.config;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

public class TestCubikTimerDataSource extends TestCase {

	private static final Logger log = Logger.getLogger(TestCubikTimerDataSource.class);

	@Test
	public void testConexion() {
		try {
			assertNotNull("Probando pool de conexiones", CubikTimerDataSource.getConnection());
		} catch (SQLException e) {
			log.debug("Ocurrió un error y no fue posible cerrar la conexion a base de datos");
			log.warn(e.getMessage());
		}

	}

}
