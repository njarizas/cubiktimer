package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

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

	@MethodRef(name = "create", signature = "(QConfiguracionDTO;)I")
	@Test
	public void testCreate() {
		assertEquals(0, configuracionDAO.create(null));
	}

	@MethodRef(name = "update", signature = "(QConfiguracionDTO;)I")
	@Test
	public void testUpdate() {
		assertEquals(0, configuracionDAO.update(null));
	}

	@MethodRef(name = "merge", signature = "(QConfiguracionDTO;)I")
	@Test
	public void testMerge() {
		assertEquals(0, configuracionDAO.merge(null));
	}

	@MethodRef(name = "delete", signature = "(QConfiguracionDTO;)I")
	@Test
	public void testDelete() {
		assertEquals(0, configuracionDAO.delete(null));
	}

	@MethodRef(name = "consultarConfiguracionPorIdUsuarioYEstado", signature = "(QInteger;QInteger;)QList<QConfiguracionDTO;>;")
	@Test
	public void testConsultarConfiguracionPorIdUsuarioYEstado() {
		assertTrue(configuracionDAO.consultarConfiguracionPorIdUsuarioYEstado(null, null).isEmpty());
		assertTrue(configuracionDAO.consultarConfiguracionPorIdUsuarioYEstado(0, 0).isEmpty());
	}

	@MethodRef(name = "existeConfiguracion", signature = "(QInteger;QInteger;)Z")
	@Test
	public void testExisteConfiguracion() {
		assertTrue(!configuracionDAO.existeConfiguracion(null, null));
		assertTrue(!configuracionDAO.existeConfiguracion(0, 0));
	}

	@MethodRef(name = "consultarConfiguracionPorIdUsuarioIdTipoYEstado", signature = "(QInteger;QInteger;QInteger;)QList<QConfiguracionDTO;>;")
	@Test
	public void testConsultarConfiguracionPorIdUsuarioIdTipoYEstado() {
		assertTrue(configuracionDAO.consultarConfiguracionPorIdUsuarioIdTipoYEstado(null, null, null).isEmpty());
		assertTrue(configuracionDAO.consultarConfiguracionPorIdUsuarioIdTipoYEstado(0, 0, 0).isEmpty());
	}

	@MethodRef(name = "setList", signature = "(QResultSet;)QList<QConfiguracionDTO;>;")
	@Test
	public void testSetList() throws Exception {
		assertTrue(configuracionDAO.setList(null).isEmpty());
	}

	@Test
	public void testIntegral() {
		assertTrue(configuracionDAO.fixAutoincrement());
		ConfiguracionDTO dto = new ConfiguracionDTO();
		dto.setIdUsuario(1);
		dto.setIdTipo(23);
		dto.setValorTexto("rubik");
		dto.setEstado(1);
		Integer idConfiguracion = configuracionDAO.merge(dto);
		assertTrue(idConfiguracion > 0);
		dto.setIdConfiguracion(idConfiguracion);
		List<ConfiguracionDTO> lista = configuracionDAO.findById(idConfiguracion);
		if (!lista.isEmpty()) {
			ConfiguracionDTO dto2 = lista.get(0);
			assertEquals(dto.getIdUsuario(), dto2.getIdUsuario());
			assertEquals(dto.getIdTipo(), dto2.getIdTipo());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		dto.setEstado(0);
		assertEquals(idConfiguracion.intValue(), configuracionDAO.merge(dto));
		lista = configuracionDAO.findById(idConfiguracion);
		if (!lista.isEmpty()) {
			ConfiguracionDTO dto2 = lista.get(0);
			assertEquals(dto.getIdUsuario(), dto2.getIdUsuario());
			assertEquals(dto.getIdTipo(), dto2.getIdTipo());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		assertEquals(idConfiguracion.intValue(), configuracionDAO.delete(dto));
		lista = configuracionDAO.findById(idConfiguracion);
		assertTrue(lista.isEmpty());
		assertTrue(configuracionDAO.fixAutoincrement());
	}

}
