/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
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

import com.cubiktimer.modelo.dto.CredencialDTO;

public class TestCredencialDAO {

	static CredencialDAO credencialDAO;

	@BeforeClass
	public static void init() {
		credencialDAO = new CredencialDAO();
	}

	@Test
	public void testFindAll() {
		List<CredencialDTO> lista = credencialDAO.findAll();
		assertTrue("busca todas las credenciales", !lista.isEmpty());
	}

	@MethodRef(name = "create", signature = "(QCredencialDTO;)I")
	@Test
	public void testCreate() {
		assertEquals(0, credencialDAO.create(null));
	}

	@MethodRef(name = "update", signature = "(QCredencialDTO;)I")
	@Test
	public void testUpdate() {
		assertEquals(0, credencialDAO.update(null));
	}

	@MethodRef(name = "merge", signature = "(QCredencialDTO;)I")
	@Test
	public void testMerge() {
		assertEquals(0, credencialDAO.merge(null));
	}

	@MethodRef(name = "delete", signature = "(QCredencialDTO;)I")
	@Test
	public void testDelete() {
		assertEquals(0, credencialDAO.delete(null));
	}

	@MethodRef(name = "consultarCredencialPorCorreo", signature = "(QString;)QList<QCredencialDTO;>;")
	@Test
	public void testConsultarCredencialPorCorreo() {
		assertTrue(credencialDAO.consultarCredencialPorCorreo(null).isEmpty());
		assertTrue(credencialDAO.consultarCredencialPorCorreo("").isEmpty());
	}

	@MethodRef(name = "consultarCredencialPorCorreoYEstado", signature = "(QString;QInteger;)QList<QCredencialDTO;>;")
	@Test
	public void testConsultarCredencialPorCorreoYEstado() {
		assertTrue(credencialDAO.consultarCredencialPorCorreoYEstado(null, 0).isEmpty());
		assertTrue(credencialDAO.consultarCredencialPorCorreoYEstado("", 0).isEmpty());
	}

	@MethodRef(name = "consultarCredencialPorCorreoClaveYEstado", signature = "(QString;QString;QInteger;)QList<QCredencialDTO;>;")
	@Test
	public void testConsultarCredencialPorCorreoClaveYEstado() {
		assertTrue(credencialDAO.consultarCredencialPorCorreoClaveYEstado(null, null, 0).isEmpty());
		assertTrue(credencialDAO.consultarCredencialPorCorreoClaveYEstado(null, "", 0).isEmpty());
		assertTrue(credencialDAO.consultarCredencialPorCorreoClaveYEstado("", null, 0).isEmpty());
		assertTrue(credencialDAO.consultarCredencialPorCorreoClaveYEstado("", "", 0).isEmpty());
	}

	@MethodRef(name = "obtenerFechaUltimaCredencial", signature = "(QInteger;)QDate;")
	@Test
	public void testObtenerFechaUltimaCredencial() {
		assertNull(credencialDAO.obtenerFechaUltimaCredencial(null));
	}

	@MethodRef(name = "setList", signature = "(QResultSet;)QList<QCredencialDTO;>;")
	@Test
	public void testSetList() throws Exception {
		assertTrue(credencialDAO.setList(null).isEmpty());
	}

	@Test
	public void testIntegral() {
		assertTrue(credencialDAO.fixAutoincrement());
		Date dateinicio = new GregorianCalendar(100, 1, 1, 0, 0, 0).getTime();
		Date datefin = new GregorianCalendar(101, 1, 1, 0, 0, 0).getTime();
		CredencialDTO dto = new CredencialDTO();
		dto.setIdUsuario(1);
		dto.setCorreo("correo");
		dto.setClave("clave");
		dto.setFechaInicio(dateinicio);
		dto.setFechaFin(datefin);
		dto.setEstado(1);
		Integer idCredencial = credencialDAO.merge(dto);
		assertTrue(idCredencial > 0);
		dto.setIdCredencial(idCredencial);
		List<CredencialDTO> lista = credencialDAO.findById(idCredencial);
		if (!lista.isEmpty()) {
			CredencialDTO dto2 = lista.get(0);
			assertEquals(dto.getIdUsuario(), dto2.getIdUsuario());
			assertEquals(dto.getCorreo(), dto2.getCorreo());
			assertEquals(dto.getClave(), dto2.getClave());
			//assertEquals(dto.getFechaInicio(), dto2.getFechaInicio());
			assertEquals(dto.getFechaFin(), dto2.getFechaFin());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		dto.setCorreo("correo_modificado");
		dto.setClave("clave_modificada");
		dto.setEstado(0);
		assertEquals(idCredencial.intValue(), credencialDAO.merge(dto));
		lista = credencialDAO.findById(idCredencial);
		if (!lista.isEmpty()) {
			CredencialDTO dto2 = lista.get(0);
			assertEquals(dto.getIdUsuario(), dto2.getIdUsuario());
			assertEquals(dto.getCorreo(), dto2.getCorreo());
			assertEquals(dto.getClave(), dto2.getClave());
			assertEquals(dto.getFechaInicio(), dto2.getFechaInicio());
			assertEquals(dto.getFechaFin(), dto2.getFechaFin());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		assertEquals(idCredencial.intValue(), credencialDAO.delete(dto));
		lista = credencialDAO.findById(idCredencial);
		assertTrue(lista.isEmpty());
		assertTrue(credencialDAO.fixAutoincrement());
	}

}
