package com.cubiktimer.config;

import org.junit.Test;

import junit.framework.TestCase;

public class TestConexionDatabase extends TestCase {

	@Test
	public void testConexion() {
		assertNotNull("Probando conexion JDBC", ConexionDatabase.getInstance());
		ConexionDatabase.close();
		assertNotNull("Probando conexion JDBC", ConexionDatabase.getInstance());
	}

}
