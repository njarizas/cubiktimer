package com.cubiktimer.config;

import java.sql.SQLException;

import org.junit.Test;

import com.cubiktimer.error.ExceptionHandler;

import junit.framework.TestCase;

public class TestCubikTimerDataSource extends TestCase {

	@Test
	public void testConexion() {
		try {
			assertNotNull("Probando pool de conexiones", CubikTimerDataSource.getConnection());
		} catch (SQLException e) {
			System.out.println("Ocurrio un error y no fue posible conectarse a base de datos");
			ExceptionHandler.manejarExcepcionModerada(e);
		}

	}

}
