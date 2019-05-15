package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestPermisosDAO {

	static PermisosDAO permisosDAO;

	@BeforeClass
	public static void init() {
		permisosDAO = new PermisosDAO();
	}

	@Test
	public void testFindAll() {
		assertTrue("por lo menos un permiso", permisosDAO.findAll().size() >= 1);
	}

}
