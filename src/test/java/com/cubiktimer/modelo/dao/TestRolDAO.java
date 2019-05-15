package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestRolDAO {

	static RolDAO rolDAO;

	@BeforeClass
	public static void init() {
		rolDAO = new RolDAO();
	}

	@Test
	public void testConsultarRolesPorIdUsuario() {
		assertTrue("por lo menos un rol", !rolDAO.consultarRolesPorIdUsuario(2).isEmpty());
	}

}
