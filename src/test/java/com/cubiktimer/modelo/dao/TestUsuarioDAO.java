package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestUsuarioDAO {

	static UsuarioDAO usuarioDAO;

	@BeforeClass
	public static void init() {
		usuarioDAO = new UsuarioDAO();
	}

	@Test
	public void testFindAll() {
		assertTrue("por lo menos un usuario", !usuarioDAO.findAll().isEmpty());
	}

}
