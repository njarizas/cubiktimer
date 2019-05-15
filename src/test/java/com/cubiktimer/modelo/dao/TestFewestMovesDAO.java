package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestFewestMovesDAO {
	static FewestMovesDAO fewestMovesDAO;

	@BeforeClass
	public static void init() {
		fewestMovesDAO = new FewestMovesDAO();
	}

	@Test
	public void testFindAll() {
		assertNotNull("registros en Fewest moves", fewestMovesDAO.findAll());
	}

}
