package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestUsuarioRolDAO {

	static UsuarioRolDAO usuarioRolDAO;

	@BeforeClass
	public static void init() {
		usuarioRolDAO = new UsuarioRolDAO();
	}

	@Test
	public void testFindAll() {
		assertTrue("por lo menos un rol-usuario", usuarioRolDAO.findAll().size() >= 1);
	}

}
