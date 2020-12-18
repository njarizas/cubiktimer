/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

import com.cubiktimer.modelo.dto.RolDTO;

public class TestRolDAO {

	static RolDAO rolDAO;

	@BeforeClass
	public static void init() {
		rolDAO = new RolDAO();
	}

	@MethodRef(name = "consultarRolesPorIdUsuario", signature = "(I)QList<QRolDTO;>;")
	@Test
	public void testConsultarRolesPorIdUsuario() {
		assertTrue("por lo menos un rol", !rolDAO.consultarRolesPorIdUsuario(2).isEmpty());
		assertTrue(rolDAO.consultarRolesPorIdUsuario(0).isEmpty());
	}

	@MethodRef(name = "create", signature = "(QRolDTO;)I")
	@Test
	public void testCreate() {
		assertEquals(0, rolDAO.create(null));
	}

	@MethodRef(name = "update", signature = "(QRolDTO;)I")
	@Test
	public void testUpdate() {
		assertEquals(0, rolDAO.update(null));
	}

	@MethodRef(name = "merge", signature = "(QRolDTO;)I")
	@Test
	public void testMerge() {
		assertEquals(0, rolDAO.merge(null));
	}

	@MethodRef(name = "delete", signature = "(QRolDTO;)I")
	@Test
	public void testDelete() {
		assertEquals(0, rolDAO.delete(null));
	}

	@MethodRef(name = "setList", signature = "(QResultSet;)QList<QRolDTO;>;")
	@Test
	public void testSetList() throws Exception {
		assertTrue(rolDAO.setList(null).isEmpty());
	}

	@Test
	public void testIntegral() {
		assertTrue(rolDAO.fixAutoincrement());
		RolDTO dto = new RolDTO();
		dto.setNombreRol("Rol prueba");
		dto.setDescripcion("Descripcion prueba");
		dto.setEstado(2);
		Integer idRol = rolDAO.merge(dto);
		assertTrue(idRol > 0);
		dto.setIdRol(idRol);
		List<RolDTO> lista = rolDAO.findById(idRol);
		if (!lista.isEmpty()) {
			RolDTO dto2 = lista.get(0);
			assertEquals(dto.getIdRol(), dto2.getIdRol());
			assertEquals(dto.getDescripcion(), dto2.getDescripcion());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		dto.setNombreRol("Rol modificado");
		dto.setDescripcion("Descripcion modificada");
		dto.setEstado(0);
		assertEquals(idRol.intValue(), rolDAO.merge(dto));
		lista = rolDAO.findById(idRol);
		if (!lista.isEmpty()) {
			RolDTO dto2 = lista.get(0);
			assertEquals(dto.getIdRol(), dto2.getIdRol());
			assertEquals(dto.getDescripcion(), dto2.getDescripcion());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		assertEquals(idRol.intValue(), rolDAO.delete(dto));
		lista = rolDAO.findById(idRol);
		assertTrue(lista.isEmpty());
		assertTrue(rolDAO.fixAutoincrement());
	}

}
