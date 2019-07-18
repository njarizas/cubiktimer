package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

import com.cubiktimer.modelo.dto.ParametroDTO;

public class TestParametroDAO {
	static ParametroDAO parametroDAO;

	@BeforeClass
	public static void init() {
		parametroDAO = new ParametroDAO();
	}

	@MethodRef(name = "create", signature = "(QParametroDTO;)I")
	@Test
	public void testCreate() {
		assertEquals(0, parametroDAO.create(null));
	}

	@MethodRef(name = "update", signature = "(QParametroDTO;)I")
	@Test
	public void testUpdate() {
		assertEquals(0, parametroDAO.update(null));
	}

	@MethodRef(name = "merge", signature = "(QParametroDTO;)I")
	@Test
	public void testMerge() {
		assertEquals(0, parametroDAO.merge(null));
	}

	@MethodRef(name = "delete", signature = "(QParametroDTO;)I")
	@Test
	public void testDelete() {
		assertEquals(0, parametroDAO.delete(null));
	}

	@MethodRef(name = "obtenerValorParametro", signature = "(QString;)QString;")
	@Test
	public void testObtenerValorParametro() {
		assertEquals("", parametroDAO.obtenerValorParametro(""));
		assertNotNull(parametroDAO.obtenerValorParametro("host_cubiktimer"));
	}

	@MethodRef(name = "obtenerParametro", signature = "(QString;)QList<QParametroDTO;>;")
	@Test
	public void testObtenerParametro() {
		assertTrue(parametroDAO.obtenerParametro("").isEmpty());
		assertTrue(!parametroDAO.obtenerParametro("host_cubiktimer").isEmpty());
		assertEquals("host_cubiktimer", parametroDAO.obtenerParametro("host_cubiktimer").get(0).getCodigo());
		assertNotNull(parametroDAO.obtenerParametro("host_cubiktimer").get(0).getValor());
	}

	@MethodRef(name = "setList", signature = "(QResultSet;)QList<QParametroDTO;>;")
	@Test
	public void testSetList() throws Exception {
		assertTrue(parametroDAO.setList(null).isEmpty());
	}

	@MethodRef(name = "existeParametro", signature = "(QString;)Z")
	@Test
	public void testExisteParametro() {
		assertTrue(!parametroDAO.existeParametro(null));
		assertTrue(!parametroDAO.existeParametro(""));
		assertTrue(parametroDAO.existeParametro("host_cubiktimer"));
	}

	@Test
	public void testIntegral() {
		ParametroDTO dto = new ParametroDTO();
		dto.setCodigo("codigo_prueba");
		dto.setValor("valor_prueba");
		Integer resultado = parametroDAO.merge(dto);
		assertTrue(resultado > 0);
		List<ParametroDTO> lista = parametroDAO.findById(dto.getCodigo());
		if (!lista.isEmpty()) {
			ParametroDTO dto2 = lista.get(0);
			assertEquals(dto.getCodigo(), dto2.getCodigo());
			assertEquals(dto.getCodigo(), dto2.getCodigo());
		}
		dto.setValor("valor_prueba_modificado");
		resultado = parametroDAO.merge(dto);
		assertTrue(resultado > 0);
		lista = parametroDAO.findById(dto.getCodigo());
		if (!lista.isEmpty()) {
			ParametroDTO dto2 = lista.get(0);
			assertEquals(dto.getCodigo(), dto2.getCodigo());
			assertEquals(dto.getCodigo(), dto2.getCodigo());
		}
		resultado = parametroDAO.delete(dto);
		assertTrue(resultado > 0);
		lista = parametroDAO.findById(dto.getCodigo());
		assertTrue(lista.isEmpty());
	}

	@MethodRef(name = "fixAutoincrement", signature = "()Z")
	@Test
	public void testFixAutoincrement() {
		try {
			parametroDAO.fixAutoincrement();
		} catch (UnsupportedOperationException e) {
			return;
		}
		fail("No genero la excepcion esperada");
	}

	@MethodRef(name = "consultarMaximoIdPK", signature = "()I")
	@Test
	public void testConsultarMaximoIdPK() {
		try {
			parametroDAO.consultarMaximoIdPK();
		} catch (UnsupportedOperationException e) {
			return;
		}
		fail("No genero la excepcion esperada");
	}

}
