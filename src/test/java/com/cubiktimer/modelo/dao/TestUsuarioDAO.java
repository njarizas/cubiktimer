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

import com.cubiktimer.modelo.dto.UsuarioDTO;

public class TestUsuarioDAO {

	static UsuarioDAO usuarioDAO;

	@BeforeClass
	public static void init() {
		usuarioDAO = new UsuarioDAO();
	}

	@Test
	public void testFindAll() {
		assertTrue("por lo menos un usuario", !usuarioDAO.findAll().isEmpty());
	}

	@MethodRef(name = "create", signature = "(QUsuarioDTO;)I")
	@Test
	public void testCreate() {
		assertEquals(0, usuarioDAO.create(null));
	}

	@MethodRef(name = "update", signature = "(QUsuarioDTO;)I")
	@Test
	public void testUpdate() {
		assertEquals(0, usuarioDAO.update(null));
	}

	@MethodRef(name = "merge", signature = "(QUsuarioDTO;)I")
	@Test
	public void testMerge() {
		assertEquals(0, usuarioDAO.merge(null));
	}

	@MethodRef(name = "delete", signature = "(QUsuarioDTO;)I")
	@Test
	public void testDelete() {
		assertEquals(0, usuarioDAO.delete(null));
	}

	@MethodRef(name = "consultarUsuarioPorCorreo", signature = "(QString;)QList<QUsuarioDTO;>;")
	@Test
	public void testConsultarUsuarioPorCorreo() {
		assertTrue(usuarioDAO.consultarUsuarioPorCorreo(null).isEmpty());
	}

	@MethodRef(name = "consultarUsuarioPorIdUsuario", signature = "(I)QList<QUsuarioDTO;>;")
	@Test
	public void testConsultarUsuarioPorIdUsuario() {
		assertTrue(usuarioDAO.consultarUsuarioPorIdUsuario(0).isEmpty());
	}

	@MethodRef(name = "consultarUsuarioPorIdUsuarioYClave", signature = "(IQString;)QList<QUsuarioDTO;>;")
	@Test
	public void testConsultarUsuarioPorIdUsuarioYClave() {
		assertTrue(usuarioDAO.consultarUsuarioPorIdUsuarioYClave(0, "").isEmpty());
		assertTrue(usuarioDAO.consultarUsuarioPorIdUsuarioYClave(0, null).isEmpty());
	}

	@MethodRef(name = "consultarUsuarioPorCorreoYEstado", signature = "(QString;QInteger;)QList<QUsuarioDTO;>;")
	@Test
	public void testConsultarUsuarioPorCorreoYEstado() {
		assertTrue(usuarioDAO.consultarUsuarioPorCorreoYEstado(null, null).isEmpty());
		assertTrue(usuarioDAO.consultarUsuarioPorCorreoYEstado(null, 0).isEmpty());
		assertTrue(usuarioDAO.consultarUsuarioPorCorreoYEstado("", null).isEmpty());
		assertTrue(usuarioDAO.consultarUsuarioPorCorreoYEstado("", 0).isEmpty());
	}

	@MethodRef(name = "consultarUsuarios", signature = "()QList<QUsuarioDTO;>;")
	@Test
	public void testConsultarUsuarios() {
		assertTrue(!usuarioDAO.consultarUsuarios().isEmpty());
	}

	@MethodRef(name = "consultarAmigosPorIdUsuario", signature = "(QInteger;)QList<QUsuarioDTO;>;")
	@Test
	public void testConsultarAmigosPorIdUsuario() {
		assertTrue(usuarioDAO.consultarAmigosPorIdUsuario(0).isEmpty());
	}

	@MethodRef(name = "consultarUsuariosNoAmigos", signature = "(QInteger;)QList<QUsuarioDTO;>;")
	@Test
	public void testConsultarUsuariosNoAmigos() {
		assertTrue(!usuarioDAO.consultarUsuariosNoAmigos(0).isEmpty());
	}

	@MethodRef(name = "consultarSolicitudesDeAmistad", signature = "(QInteger;)QList<QUsuarioDTO;>;")
	@Test
	public void testConsultarSolicitudesDeAmistad() {
		assertTrue(usuarioDAO.consultarSolicitudesDeAmistad(0).isEmpty());
	}

	@MethodRef(name = "consultarUsuariosAmigos", signature = "(QInteger;)QList<QUsuarioDTO;>;")
	@Test
	public void testConsultarUsuariosAmigos() {
		assertTrue(usuarioDAO.consultarUsuariosAmigos(0).isEmpty());
	}

	@MethodRef(name = "consultarSalPorUsuario", signature = "(QString;)QString;")
	@Test
	public void testConsultarSalPorUsuario() {
		assertEquals("", usuarioDAO.consultarSalPorUsuario(""));
	}

	@MethodRef(name = "setList", signature = "(QResultSet;)QList<QUsuarioDTO;>;")
	@Test
	public void testSetList() throws Exception {
		assertTrue(usuarioDAO.setList(null).isEmpty());
	}

	@Test
	public void testIntegral() {
		assertTrue(usuarioDAO.fixAutoincrement());
		UsuarioDTO dto = new UsuarioDTO();
		dto.setCorreo("setCorreo");
		dto.setSal("setSal");
		dto.setClave("setClave");
		dto.setToken("setToken");
		dto.setNombres("setNombres");
		dto.setApellidos("setApellidos");
		dto.setSexo('M');
		dto.setFechaNacimiento(new Date());
		dto.setFechaCreacion(new Date());
		dto.setFechaModificacion(new Date());
		dto.setEstado(2);
		Integer idUsuario = usuarioDAO.merge(dto);
		assertTrue(idUsuario > 0);
		dto.setIdUsuario(idUsuario);
		List<UsuarioDTO> lista = usuarioDAO.findById(idUsuario);
		if (!lista.isEmpty()) {
			UsuarioDTO dto2 = lista.get(0);
			assertEquals(dto.getCorreo(), dto2.getCorreo());
			assertEquals(dto.getSal(), dto2.getSal());
			assertEquals(dto.getClave(), dto2.getClave());
			assertEquals(dto.getNombres(), dto2.getNombres());
			assertEquals(dto.getApellidos(), dto2.getApellidos());
			assertEquals(dto.getSexo(), dto2.getSexo());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		dto.setCorreo("Correo");
		dto.setSal("Sal");
		dto.setClave("Clave");
		dto.setToken("Token");
		dto.setNombres("Nombres");
		dto.setApellidos("Apellidos");
		dto.setSexo('M');
		dto.setFechaNacimiento(new Date());
		dto.setFechaCreacion(new Date());
		dto.setFechaModificacion(new Date());
		dto.setEstado(2);
		assertEquals(idUsuario.intValue(), usuarioDAO.merge(dto));
		lista = usuarioDAO.findById(idUsuario);
		if (!lista.isEmpty()) {
			UsuarioDTO dto2 = lista.get(0);
			assertEquals(dto.getCorreo(), dto2.getCorreo());
			assertEquals(dto.getSal(), dto2.getSal());
			assertEquals(dto.getClave(), dto2.getClave());
			assertEquals(dto.getNombres(), dto2.getNombres());
			assertEquals(dto.getApellidos(), dto2.getApellidos());
			assertEquals(dto.getSexo(), dto2.getSexo());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		assertEquals(idUsuario.intValue(), usuarioDAO.delete(dto));
		lista = usuarioDAO.findById(idUsuario);
		assertTrue(lista.isEmpty());
		assertTrue(usuarioDAO.fixAutoincrement());
	}

}