package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

import com.cubiktimer.modelo.dto.PermisoDTO;

public class TestPermisosDAO {

	static PermisosDAO permisosDAO;

	@BeforeClass
	public static void init() {
		permisosDAO = new PermisosDAO();
	}

	@Test
	public void testFindAll() {
		assertTrue("por lo menos un permiso", !permisosDAO.findAll().isEmpty());
	}

	@MethodRef(name = "create", signature = "(QPermisoDTO;)I")
	@Test
	public void testCreate() {
		assertEquals(0, permisosDAO.create(null));
	}

	@MethodRef(name = "update", signature = "(QPermisoDTO;)I")
	@Test
	public void testUpdate() {
		assertEquals(0, permisosDAO.update(null));
	}

	@MethodRef(name = "merge", signature = "(QPermisoDTO;)I")
	@Test
	public void testMerge() {
		assertEquals(0, permisosDAO.merge(null));
	}

	@MethodRef(name = "delete", signature = "(QPermisoDTO;)I")
	@Test
	public void testDelete() {
		assertEquals(0, permisosDAO.delete(null));
	}

	@MethodRef(name = "consultarPermisosPorIdUsuario", signature = "(I)QList<QPermisoDTO;>;")
	@Test
	public void testConsultarPermisosPorIdUsuario() {
		assertTrue(permisosDAO.consultarPermisosPorIdUsuario(0).isEmpty());

	}

	@MethodRef(name = "listarPermisos", signature = "(I)QList<QPermisoDTO;>;")
	@Test
	public void testListarPermisos() {
		assertTrue(permisosDAO.listarPermisos(0).isEmpty());
	}

	@MethodRef(name = "consultarPermisosPorIdPadre", signature = "(I)QList<QPermisoDTO;>;")
	@Test
	public void testConsultarPermisosPorIdPadre() {
		assertTrue(permisosDAO.consultarPermisosPorIdPadre(0).isEmpty());
	}

	@MethodRef(name = "contarPermisosIdPadre", signature = "(QInteger;)QInteger;")
	@Test
	public void testContarPermisosIdPadre() {
		assertEquals(Integer.valueOf(0), permisosDAO.contarPermisosIdPadre(0));
	}

	@MethodRef(name = "setList", signature = "(QResultSet;)QList<QPermisoDTO;>;")
	@Test
	public void testSetList() throws Exception {
		assertTrue(permisosDAO.setList(null).isEmpty());
	}

	@Test
	public void testIntegral() {
		assertTrue(permisosDAO.fixAutoincrement());
		PermisoDTO dto = new PermisoDTO();
		dto.setNombrePermiso("Nombre");
		dto.setDescripcionPermiso("Descripcion");
		dto.setNamePermiso("Name");
		dto.setDescriptionPermiso("Description");
		dto.setEstado(2);
		Integer idPermiso = permisosDAO.merge(dto);
		assertTrue(idPermiso > 0);
		dto.setIdPermiso(idPermiso);
		List<PermisoDTO> lista = permisosDAO.findById(idPermiso);
		if (!lista.isEmpty()) {
			PermisoDTO dto2 = lista.get(0);
			assertEquals(dto.getIdPermiso(), dto2.getIdPermiso());
			assertEquals(dto.getNombrePermiso(), dto2.getNombrePermiso());
			assertEquals(dto.getDescripcionPermiso(), dto2.getDescripcionPermiso());
			assertEquals(dto.getDescriptionPermiso(), dto2.getDescriptionPermiso());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		dto.setNombrePermiso("Nombre_modificado");
		dto.setDescripcionPermiso("Descripcion_modificado");
		dto.setNamePermiso("Name_modificado");
		dto.setDescriptionPermiso("Description_modificado");
		dto.setEstado(0);
		assertEquals(idPermiso.intValue(), permisosDAO.merge(dto));
		lista = permisosDAO.findById(idPermiso);
		if (!lista.isEmpty()) {
			PermisoDTO dto2 = lista.get(0);
			assertEquals(dto.getIdPermiso(), dto2.getIdPermiso());
			assertEquals(dto.getNombrePermiso(), dto2.getNombrePermiso());
			assertEquals(dto.getDescripcionPermiso(), dto2.getDescripcionPermiso());
			assertEquals(dto.getDescriptionPermiso(), dto2.getDescriptionPermiso());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		assertEquals(idPermiso.intValue(), permisosDAO.delete(dto));
		lista = permisosDAO.findById(idPermiso);
		assertTrue(lista.isEmpty());
		assertTrue(permisosDAO.fixAutoincrement());
	}

}
