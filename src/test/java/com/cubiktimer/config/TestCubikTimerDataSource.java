package com.cubiktimer.config;

import java.sql.SQLException;

import org.junit.Test;

import junit.framework.TestCase;

public class TestCubikTimerDataSource extends TestCase {

	@Test
	public void testConexion() {
		try {
			assertNotNull("Probando pool de conexiones", CubikTimerDataSource.getConnection());
		} catch (SQLException e) {
			System.out.println("Ocurrio un error y no fue posible cerrar la conexion a base de datos");
			System.out.println(e.getMessage());
		}

	}

}
