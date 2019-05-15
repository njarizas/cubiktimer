package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cubiktimer.modelo.dto.ConfiguracionDTO;

public class TestConfiguracionDAO {

	static ConfiguracionDAO configuracionDAO;

	@BeforeClass
	public static void init() {
		configuracionDAO = new ConfiguracionDAO();
	}

	@Test
	public void testFindAll() {
		List<ConfiguracionDTO> lista = configuracionDAO.findAll();
		assertTrue("busca todas las configuraciones", !lista.isEmpty());
	}

}
