package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cubiktimer.modelo.dto.SesionRubikDTO;

public class TestSesionRubikDAO {

	static SesionRubikDAO sesionRubikDAO;

	@BeforeClass
	public static void init() {
		sesionRubikDAO = new SesionRubikDAO();
	}

	@Test
	public void testFindAll() {
		List<SesionRubikDTO> lista = sesionRubikDAO.findAll();
		assertTrue("por lo menos una sesion", !lista.isEmpty());
	}

}
