package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Generated;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

import com.cubiktimer.modelo.dto.RolPermisoDTO;
import com.cubiktimer.modelo.dto.RolPermisoPK;

@Generated(value = "org.junit-tools-1.1.0")
public class TestRolPermisoDAO {

	static RolPermisoDAO rolPermisoDAO;

	@BeforeClass
	public static void init() {
		rolPermisoDAO = new RolPermisoDAO();
	}

	@Test
	public void testFindAll() {
		assertTrue("por lo menos un permiso-rol", !rolPermisoDAO.findAll().isEmpty());
	}

	@MethodRef(name = "create", signature = "(QRolPermisoDTO;)I")
	@Test
	public void testCreate() {
		assertEquals(0, rolPermisoDAO.create(null));
	}

	@MethodRef(name = "update", signature = "(QRolPermisoDTO;)I")
	@Test
	public void testUpdate() {
		assertEquals(0, rolPermisoDAO.update(null));
	}

	@MethodRef(name = "merge", signature = "(QRolPermisoDTO;)I")
	@Test
	public void testMerge() {
		assertEquals(0, rolPermisoDAO.merge(null));
		assertEquals(0, rolPermisoDAO.merge(new RolPermisoDTO()));
	}

	@MethodRef(name = "delete", signature = "(QRolPermisoDTO;)I")
	@Test
	public void testDelete() {
		assertEquals(0, rolPermisoDAO.delete(null));
	}

	@MethodRef(name = "deleteByPK", signature = "(QRolPermisoPK;)I")
	@Test
	public void testDeleteByPK() {
		assertEquals(0, rolPermisoDAO.deleteByPK(null));
	}

	@MethodRef(name = "consultarPermisosPorIdRol", signature = "(I)QList<QRolPermisoDTO;>;")
	@Test
	public void testConsultarPermisosPorIdRol() throws Exception {
		assertTrue(rolPermisoDAO.consultarPermisosPorIdRol(0).isEmpty());
	}

	@MethodRef(name = "findById", signature = "(QRolPermisoPK;)QList<QRolPermisoDTO;>;")
	@Test
	public void testFindById() throws Exception {
		assertTrue(rolPermisoDAO.findById(null).isEmpty());
	}

	@MethodRef(name = "setList", signature = "(QResultSet;)QList<QRolPermisoDTO;>;")
	@Test
	public void testSetList() throws Exception {
		assertTrue(rolPermisoDAO.setList(null).isEmpty());
	}

	@MethodRef(name = "existeRolPermiso", signature = "(QRolPermisoPK;)Z")
	@Test
	public void testExisteRolPermiso() throws Exception {
		assertTrue(!rolPermisoDAO.existeRolPermiso(null));
	}

	@Test
	public void testIntegral() {
		RolPermisoDTO dto = new RolPermisoDTO();
		RolPermisoPK pk = new RolPermisoPK(1, 3);
		dto.setRolPermisoPK(pk);
		dto.setEstado(3);
		assertTrue(rolPermisoDAO.merge(dto) > 0);
		List<RolPermisoDTO> lista = rolPermisoDAO.findById(pk);
		if (!lista.isEmpty()) {
			RolPermisoDTO dto2 = lista.get(0);
			assertEquals(dto.getRolPermisoPK().getIdPermiso(), dto2.getRolPermisoPK().getIdPermiso());
			assertEquals(dto.getRolPermisoPK().getIdRol(), dto2.getRolPermisoPK().getIdRol());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		dto.setEstado(0);
		assertTrue(rolPermisoDAO.merge(dto) > 0);
		lista = rolPermisoDAO.findById(pk);
		if (!lista.isEmpty()) {
			RolPermisoDTO dto2 = lista.get(0);
			assertEquals(dto.getRolPermisoPK().getIdPermiso(), dto2.getRolPermisoPK().getIdPermiso());
			assertEquals(dto.getRolPermisoPK().getIdRol(), dto2.getRolPermisoPK().getIdRol());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		assertTrue(rolPermisoDAO.delete(dto) > 0);
		lista = rolPermisoDAO.findById(pk);
		assertTrue(lista.isEmpty());
	}

	@MethodRef(name = "fixAutoincrement", signature = "()Z")
	@Test
	public void testFixAutoincrement() throws Exception {
		try {
			rolPermisoDAO.fixAutoincrement();
		} catch (UnsupportedOperationException e) {
			return;
		}
		fail("No genero la excepcion esperada");
	}

	@MethodRef(name = "consultarMaximoIdPK", signature = "()I")
	@Test
	public void testConsultarMaximoIdPK() throws Exception {
		try {
			rolPermisoDAO.consultarMaximoIdPK();
		} catch (UnsupportedOperationException e) {
			return;
		}
		fail("No genero la excepcion esperada");
	}
}