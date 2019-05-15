package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cubiktimer.modelo.dto.TipoDTO;

public class TestTipoDAO {

	static TipoDAO tipoDAO;

	@BeforeClass
	public static void init() {
		tipoDAO = new TipoDAO();
	}

	@Test
	public void testListarTiposDeCubo() {
		assertTrue("mas de 15 tipos de cubo", tipoDAO.listarTiposDeCubo().size() >= 15);
	}

	@Test
	public void testListarTiposDeCuboFewest() {
		List<TipoDTO> lista = tipoDAO.listarTiposDeCuboFewest();
		assertEquals("un solo tipo de fewest", 1, lista.size());
		assertEquals("fewest", "2", lista.get(0).getIdPadre().toString());
	}

}
