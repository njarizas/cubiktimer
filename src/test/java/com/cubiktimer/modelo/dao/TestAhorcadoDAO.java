package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

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

	@MethodRef(name = "create", signature = "(QAhorcadoDTO;)I")
	@Test
	public void testCreate() {
		assertEquals(0, ahorcadoDAO.create(null));
	}

	@MethodRef(name = "update", signature = "(QAhorcadoDTO;)I")
	@Test
	public void testUpdate() {
		assertEquals(0, ahorcadoDAO.update(null));
	}

	@MethodRef(name = "merge", signature = "(QAhorcadoDTO;)I")
	@Test
	public void testMerge() {
		assertEquals(0, ahorcadoDAO.merge(null));
	}

	@MethodRef(name = "delete", signature = "(QAhorcadoDTO;)I")
	@Test
	public void testDelete() {
		assertEquals(0, ahorcadoDAO.delete(null));
	}

	@MethodRef(name = "setList", signature = "(QResultSet;)QList<QAhorcadoDTO;>;")
	@Test
	public void testSetList() throws Exception {
		assertTrue(ahorcadoDAO.setList(null).isEmpty());
	}

	@Test
	public void testIntegral() {
		assertTrue(ahorcadoDAO.fixAutoincrement());
		Date date = new GregorianCalendar(100, 1, 1, 0, 0, 0).getTime();
		AhorcadoDTO dto = new AhorcadoDTO(date, "prueba", true, "[P, R, U, E, B, A]", 5);
		Integer idAhorcado = ahorcadoDAO.merge(dto);
		assertTrue(idAhorcado > 0);
		dto.setIdAhorcado(idAhorcado);
		List<AhorcadoDTO> lista = ahorcadoDAO.findById(idAhorcado);
		if (!lista.isEmpty()) {
			AhorcadoDTO dto2 = lista.get(0);
			assertEquals(idAhorcado, dto2.getIdAhorcado());
			assertNull(dto2.getIdUsuario());
			assertEquals(date, dto2.getFecha());
			assertEquals("prueba", dto2.getPalabra());
			assertEquals("[P, R, U, E, B, A]", dto2.getLetrasUsadas());
			assertEquals(Integer.valueOf(5), dto2.getIntentosSobrantes());
			assertTrue(dto2.getGano());
			assertNull(dto2.getIp());
			assertEquals(Integer.valueOf(1), dto2.getEstado());
		}
		dto.setPalabra("prueba_modificada");
		dto.setLetrasUsadas("[ ]");
		dto.setIntentosSobrantes(2);
		dto.setIp("192.1.2.3");
		dto.setEstado(0);
		assertEquals(idAhorcado.intValue(), ahorcadoDAO.merge(dto));
		lista = ahorcadoDAO.findById(idAhorcado);
		if (!lista.isEmpty()) {
			AhorcadoDTO dto2 = lista.get(0);
			assertEquals(idAhorcado, dto2.getIdAhorcado());
			assertNull(dto2.getIdUsuario());
			assertEquals(date, dto2.getFecha());
			assertEquals("prueba_modificada", dto2.getPalabra());
			assertEquals("[ ]", dto2.getLetrasUsadas());
			assertEquals(Integer.valueOf(2), dto2.getIntentosSobrantes());
			assertTrue(dto2.getGano());
			assertEquals("192.1.2.3", dto2.getIp());
			assertEquals(Integer.valueOf(0), dto2.getEstado());
		}
		assertEquals(idAhorcado.intValue(), ahorcadoDAO.delete(dto));
		lista = ahorcadoDAO.findById(idAhorcado);
		assertTrue(lista.isEmpty());
		assertTrue(ahorcadoDAO.fixAutoincrement());
	}

}
