package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cubiktimer.modelo.dto.AhorcadoDTO;

public class TestAhorcadoDAO {

	static AhorcadoDAO ahorcadoDAO;

	@BeforeClass
	public static void init() {
		ahorcadoDAO = new AhorcadoDAO();
	}

	@Test
	public void testFindAll() {
		List<AhorcadoDTO> lista = ahorcadoDAO.findAll();
		assertTrue("busca todos los registros de ahorcado", !lista.isEmpty());
	}

}
