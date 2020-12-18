/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

import com.cubiktimer.modelo.dto.SesionRubikDTO;

public class TestSesionRubikDAO {

	static SesionRubikDAO sesionRubikDAO;

	@BeforeClass
	public static void init() {
		sesionRubikDAO = new SesionRubikDAO();
	}

	@Test
	public void testFindAll() {
		List<SesionRubikDTO> lista = sesionRubikDAO.findAll();
		assertTrue("por lo menos una sesion", !lista.isEmpty());
	}

	@MethodRef(name = "create", signature = "(QSesionRubikDTO;)I")
	@Test
	public void testCreate() {
		assertEquals(0, sesionRubikDAO.create(null));
	}

	@MethodRef(name = "update", signature = "(QSesionRubikDTO;)I")
	@Test
	public void testUpdate() {
		assertEquals(0, sesionRubikDAO.update(null));
	}

	@MethodRef(name = "merge", signature = "(QSesionRubikDTO;)I")
	@Test
	public void testMerge() {
		assertEquals(0, sesionRubikDAO.merge(null));
	}

	@MethodRef(name = "delete", signature = "(QSesionRubikDTO;)I")
	@Test
	public void testDelete() {
		assertEquals(0, sesionRubikDAO.delete(null));
	}

	@MethodRef(name = "setList", signature = "(QResultSet;)QList<QSesionRubikDTO;>;")
	@Test
	public void testSetList() throws Exception {
		assertTrue(sesionRubikDAO.setList(null).isEmpty());
	}

	@Test
	public void testIntegral() {
		assertTrue(sesionRubikDAO.fixAutoincrement());
		SesionRubikDTO dto = new SesionRubikDTO();
		dto.setIdUsuario(2);
		dto.setFecha(new Date());
		dto.setIp("127.0.0.1");
		dto.setEstado(2);
		Integer idSesionRubik = sesionRubikDAO.merge(dto);
		assertTrue(idSesionRubik > 0);
		dto.setIdSesion(idSesionRubik);
		List<SesionRubikDTO> lista = sesionRubikDAO.findById(idSesionRubik);
		if (!lista.isEmpty()) {
			SesionRubikDTO dto2 = lista.get(0);
			assertEquals(dto.getIdUsuario(), dto2.getIdUsuario());
			assertEquals(dto.getIp(), dto2.getIp());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		dto.setIdUsuario(1);
		dto.setFecha(new Date());
		dto.setIp("localhost");
		dto.setEstado(0);
		assertEquals(idSesionRubik.intValue(), sesionRubikDAO.merge(dto));
		lista = sesionRubikDAO.findById(idSesionRubik);
		if (!lista.isEmpty()) {
			SesionRubikDTO dto2 = lista.get(0);
			assertEquals(dto.getIdUsuario(), dto2.getIdUsuario());
			assertEquals(dto.getIp(), dto2.getIp());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		assertEquals(idSesionRubik.intValue(), sesionRubikDAO.delete(dto));
		lista = sesionRubikDAO.findById(idSesionRubik);
		assertTrue(lista.isEmpty());
		assertTrue(sesionRubikDAO.fixAutoincrement());
	}

}
