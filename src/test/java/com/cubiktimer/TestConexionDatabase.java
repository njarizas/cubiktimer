package com.cubiktimer;

import org.junit.Test;

import com.cubiktimer.config.ConexionDatabase;

import junit.framework.TestCase;

public class TestConexionDatabase extends TestCase {

	@Test
	public void testConexion() {
		assertNotNull("Probando conexion JDBC", ConexionDatabase.getInstance());
		ConexionDatabase.close();
		assertNotNull("Probando conexion JDBC", ConexionDatabase.getInstance());
	}

}
