package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cubiktimer.modelo.dto.TiempoRubikDTO;

public class TestTiempoRubikDAO {

	static TiempoRubikDAO tiempoRubikDAO;

	@BeforeClass
	public static void init() {
		tiempoRubikDAO = new TiempoRubikDAO();
	}

	@Test
	public void testFindAll() {
		List<TiempoRubikDTO> lista = tiempoRubikDAO.findAll();
		assertTrue("busca todos los tiempos", lista.size() >= 1);
	}

}
