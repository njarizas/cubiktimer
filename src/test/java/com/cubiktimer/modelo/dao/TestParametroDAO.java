package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestParametroDAO {
	static ParametroDAO parametroDAO;

	@BeforeClass
	public static void init() {
		parametroDAO = new ParametroDAO();
	}

	@Test
	public void testObtenerParametro() {
		assertNotNull("parametro host_cubiktimer", parametroDAO.obtenerParametro("host_cubiktimer"));
	}

}
