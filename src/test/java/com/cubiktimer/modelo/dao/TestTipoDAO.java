package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

import com.cubiktimer.modelo.dto.TipoDTO;

public class TestTipoDAO {

	static TipoDAO tipoDAO;

	@BeforeClass
	public static void init() {
		tipoDAO = new TipoDAO();
	}

	@MethodRef(name = "listarTiposDeCubo", signature = "()QList<QTipoDTO;>;")
	@Test
	public void testListarTiposDeCubo() {
		assertTrue("mas de 15 tipos de cubo", tipoDAO.listarTiposDeCubo().size() >= 15);
	}

	@MethodRef(name = "listarTiposDeCuboFewest", signature = "()QList<QTipoDTO;>;")
	@Test
	public void testListarTiposDeCuboFewest() {
		List<TipoDTO> lista = tipoDAO.listarTiposDeCuboFewest();
		assertEquals("un solo tipo de fewest", 1, lista.size());
		assertEquals("fewest", "2", lista.get(0).getIdPadre().toString());
	}

	@MethodRef(name = "create", signature = "(QTipoDTO;)I")
	@Test
	public void testCreate() {
		assertEquals(0, tipoDAO.create(null));
	}

	@MethodRef(name = "update", signature = "(QTipoDTO;)I")
	@Test
	public void testUpdate() {
		assertEquals(0, tipoDAO.update(null));
	}

	@MethodRef(name = "merge", signature = "(QTipoDTO;)I")
	@Test
	public void testMerge() {
		assertEquals(0, tipoDAO.merge(null));
	}

	@MethodRef(name = "delete", signature = "(QTipoDTO;)I")
	@Test
	public void testDelete() {
		assertEquals(0, tipoDAO.delete(null));
	}

	@MethodRef(name = "listarTiposDeCuboPorEstado", signature = "(QInteger;)QList<QTipoDTO;>;")
	@Test
	public void testListarTiposDeCuboPorEstado() {
		assertTrue(tipoDAO.listarTiposDeCuboPorEstado(null).isEmpty());
	}

	@MethodRef(name = "setList", signature = "(QResultSet;)QList<QTipoDTO;>;")
	@Test
	public void testSetList() throws Exception {
		assertTrue(tipoDAO.setList(null).isEmpty());
	}

	@Test
	public void testIntegral() {
		assertTrue(tipoDAO.fixAutoincrement());
		TipoDTO dto = new TipoDTO();
		dto.setNombreTipo("Prueba");
		dto.setNameTipo("Test");
		dto.setEstado(2);
		Integer idTipo = tipoDAO.merge(dto);
		assertTrue(idTipo > 0);
		dto.setIdTipo(idTipo);
		List<TipoDTO> lista = tipoDAO.findById(idTipo);
		if (!lista.isEmpty()) {
			TipoDTO dto2 = lista.get(0);
			assertEquals(dto.getNombreTipo(), dto2.getNombreTipo());
			assertEquals(dto.getNameTipo(), dto2.getNameTipo());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		dto.setNombreTipo("Prueba Modificada");
		dto.setNameTipo("Changed Test");
		dto.setEstado(0);
		assertEquals(idTipo.intValue(), tipoDAO.merge(dto));
		lista = tipoDAO.findById(idTipo);
		if (!lista.isEmpty()) {
			TipoDTO dto2 = lista.get(0);
			assertEquals(dto.getNombreTipo(), dto2.getNombreTipo());
			assertEquals(dto.getNameTipo(), dto2.getNameTipo());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		assertEquals(idTipo.intValue(), tipoDAO.delete(dto));
		lista = tipoDAO.findById(idTipo);
		assertTrue(lista.isEmpty());
		assertTrue(tipoDAO.fixAutoincrement());
	}

}
