/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
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
