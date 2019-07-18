package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

import com.cubiktimer.modelo.dto.UsuarioRolDTO;
import com.cubiktimer.modelo.dto.UsuarioRolPK;

public class TestUsuarioRolDAO {

	static UsuarioRolDAO usuarioRolDAO;

	@BeforeClass
	public static void init() {
		usuarioRolDAO = new UsuarioRolDAO();
	}

	@Test
	public void testFindAll() {
		assertTrue("por lo menos un rol-usuario", !usuarioRolDAO.findAll().isEmpty());
	}

	@MethodRef(name = "create", signature = "(QUsuarioRolDTO;)I")
	@Test
	public void testCreate() {
		assertEquals(0, usuarioRolDAO.create(null));
	}

	@MethodRef(name = "update", signature = "(QUsuarioRolDTO;)I")
	@Test
	public void testUpdate() {
		assertEquals(0, usuarioRolDAO.update(null));
	}

	@MethodRef(name = "merge", signature = "(QUsuarioRolDTO;)I")
	@Test
	public void testMerge() {
		assertEquals(0, usuarioRolDAO.merge(null));
		assertEquals(0, usuarioRolDAO.merge(new UsuarioRolDTO()));
	}

	@MethodRef(name = "delete", signature = "(QUsuarioRolDTO;)I")
	@Test
	public void testDelete() {
		assertEquals(0, usuarioRolDAO.delete(null));
	}

	@MethodRef(name = "consultarRolesPorIdUsuario", signature = "(I)QList<QUsuarioRolDTO;>;")
	@Test
	public void testConsultarRolesPorIdUsuario() {
		assertTrue(usuarioRolDAO.consultarRolesPorIdUsuario(0).isEmpty());
	}

	@MethodRef(name = "setList", signature = "(QResultSet;)QList<QUsuarioRolDTO;>;")
	@Test
	public void testSetList() throws Exception {
		assertTrue(usuarioRolDAO.setList(null).isEmpty());
	}

	@MethodRef(name = "deleteByPK", signature = "(QUsuarioRolPK;)I")
	@Test
	public void testDeleteByPK() {
		assertEquals(0, usuarioRolDAO.deleteByPK(null));
	}

	@MethodRef(name = "findById", signature = "(QUsuarioRolPK;)QList<QUsuarioRolDTO;>;")
	@Test
	public void testFindById() {
		assertTrue(usuarioRolDAO.findById(null).isEmpty());
	}

	@MethodRef(name = "existeUsuarioRol", signature = "(QUsuarioRolPK;)Z")
	@Test
	public void testExisteUsuarioRol() {
		assertTrue(!usuarioRolDAO.existeUsuarioRol(null));
	}

	@Test
	public void testIntegral() {
		UsuarioRolDTO dto = new UsuarioRolDTO();
		UsuarioRolPK pk = new UsuarioRolPK(1, 2);
		dto.setUsuarioRolPK(pk);
		dto.setEstado(2);
		assertTrue(usuarioRolDAO.merge(dto) > 0);
		List<UsuarioRolDTO> lista = usuarioRolDAO.findById(pk);
		if (!lista.isEmpty()) {
			UsuarioRolDTO dto2 = lista.get(0);
			assertEquals(dto.getUsuarioRolPK().getIdUsuario(), dto2.getUsuarioRolPK().getIdUsuario());
			assertEquals(dto.getUsuarioRolPK().getIdRol(), dto2.getUsuarioRolPK().getIdRol());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		dto.setEstado(0);
		assertTrue(usuarioRolDAO.merge(dto) > 0);
		lista = usuarioRolDAO.findById(pk);
		if (!lista.isEmpty()) {
			UsuarioRolDTO dto2 = lista.get(0);
			assertEquals(dto.getUsuarioRolPK().getIdUsuario(), dto2.getUsuarioRolPK().getIdUsuario());
			assertEquals(dto.getUsuarioRolPK().getIdRol(), dto2.getUsuarioRolPK().getIdRol());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		assertTrue(usuarioRolDAO.delete(dto) > 0);
		lista = usuarioRolDAO.findById(pk);
		assertTrue(lista.isEmpty());
	}

	@MethodRef(name = "fixAutoincrement", signature = "()Z")
	@Test
	public void testFixAutoincrement() {
		try {
			usuarioRolDAO.fixAutoincrement();
		} catch (UnsupportedOperationException e) {
			return;
		}
		fail("No genero la excepcion esperada");
	}

	@MethodRef(name = "consultarMaximoIdPK", signature = "()I")
	@Test
	public void testConsultarMaximoIdPK() {
		try {
			usuarioRolDAO.consultarMaximoIdPK();
		} catch (UnsupportedOperationException e) {
			return;
		}
		fail("No genero la excepcion esperada");
	}

}
