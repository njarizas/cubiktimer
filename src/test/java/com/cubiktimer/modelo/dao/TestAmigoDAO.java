package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cubiktimer.modelo.dto.AmigoDTO;

public class TestAmigoDAO {

	static AmigoDAO amigoDAO;

	@BeforeClass
	public static void init() {
		amigoDAO = new AmigoDAO();
	}

	@Test
	public void testFindAll() {
		List<AmigoDTO> lista = amigoDAO.findAll();
		assertTrue("busca todos los amigos", !lista.isEmpty());
	}

}
