package com.cubiktimer;

import java.sql.SQLException;

import org.junit.Test;

import com.cubiktimer.config.ConexionDatabase;
import com.cubiktimer.config.CubikTimerDataSource;

import junit.framework.TestCase;

public class TestCubikTimerDataSource extends TestCase {

	@Test
	public void testConexion() {
		try {
			assertNotNull("Probando conexion JDBC", CubikTimerDataSource.getConnection());
			ConexionDatabase.close();
			assertNotNull("Probando conexion JDBC", CubikTimerDataSource.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
