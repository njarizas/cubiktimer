package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

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

	@MethodRef(name = "create", signature = "(QAmigoDTO;)I")
	@Test
	public void testCreate() {
		assertEquals(0, amigoDAO.create(null));
	}

	@MethodRef(name = "update", signature = "(QAmigoDTO;)I")
	@Test
	public void testUpdate() {
		assertEquals(0, amigoDAO.update(null));
	}

	@MethodRef(name = "merge", signature = "(QAmigoDTO;)I")
	@Test
	public void testMerge() {
		assertEquals(0, amigoDAO.merge(null));
	}

	@MethodRef(name = "delete", signature = "(QAmigoDTO;)I")
	@Test
	public void testDelete() {
		assertEquals(0, amigoDAO.delete(null));
	}

	@MethodRef(name = "consultarAmigosPorIdUsuarioYIdAmigo", signature = "(QInteger;QInteger;)QList<QAmigoDTO;>;")
	@Test
	public void testConsultarAmigosPorIdUsuarioYIdAmigo() {
		assertTrue(amigoDAO.consultarAmigosPorIdUsuarioYIdAmigo(null, null).isEmpty());
	}

	@MethodRef(name = "setList", signature = "(QResultSet;)QList<QAmigoDTO;>;")
	@Test
	public void testSetList() throws Exception {
		assertTrue(amigoDAO.setList(null).isEmpty());
	}

	@Test
	public void testIntegral() {
		AmigoDTO dto = new AmigoDTO();
		dto.setIdUsuario(1);
		dto.setIdAmigo(2);
		dto.setEstado(2);
		assertTrue(amigoDAO.fixAutoincrement());
		Integer idAmistad = amigoDAO.merge(dto);
		assertTrue(idAmistad > 0);
		dto.setIdAmistad(idAmistad);
		List<AmigoDTO> lista = amigoDAO.findById(idAmistad);
		if (!lista.isEmpty()) {
			AmigoDTO dto2 = lista.get(0);
			assertEquals(dto.getIdUsuario(), dto2.getIdUsuario());
			assertEquals(dto.getIdAmigo(), dto2.getIdAmigo());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		dto.setEstado(0);
		assertEquals(idAmistad.intValue(), amigoDAO.merge(dto));
		lista = amigoDAO.findById(idAmistad);
		if (!lista.isEmpty()) {
			AmigoDTO dto2 = lista.get(0);
			assertEquals(dto.getIdUsuario(), dto2.getIdUsuario());
			assertEquals(dto.getIdAmigo(), dto2.getIdAmigo());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		assertEquals(idAmistad.intValue(), amigoDAO.delete(dto));
		lista = amigoDAO.findById(idAmistad);
		assertTrue(lista.isEmpty());
		assertTrue(amigoDAO.fixAutoincrement());
	}

}
