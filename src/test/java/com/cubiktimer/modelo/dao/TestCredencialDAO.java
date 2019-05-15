package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cubiktimer.modelo.dto.CredencialDTO;

public class TestCredencialDAO {

	static CredencialDAO credencialDAO;

	@BeforeClass
	public static void init() {
		credencialDAO = new CredencialDAO();
	}

	@Test
	public void testFindAll() {
		List<CredencialDTO> lista = credencialDAO.findAll();
		assertTrue("busca todas las credenciales", lista.isEmpty());
	}

}
