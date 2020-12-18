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

import com.cubiktimer.modelo.dto.TiempoRubikDTO;

public class TestTiempoRubikDAO {

	static TiempoRubikDAO tiempoRubikDAO;

	@BeforeClass
	public static void init() {
		tiempoRubikDAO = new TiempoRubikDAO();
	}

	@Test
	public void testFindAll() {
		List<TiempoRubikDTO> lista = tiempoRubikDAO.findAll();
		assertTrue("busca todos los tiempos", !lista.isEmpty());
	}

	@MethodRef(name = "create", signature = "(QTiempoRubikDTO;)I")
	@Test
	public void testCreate() {
		assertEquals(0, tiempoRubikDAO.create(null));
	}

	@MethodRef(name = "update", signature = "(QTiempoRubikDTO;)I")
	@Test
	public void testUpdate() {
		assertEquals(0, tiempoRubikDAO.update(null));
	}

	@MethodRef(name = "merge", signature = "(QTiempoRubikDTO;)I")
	@Test
	public void testMerge() {
		assertEquals(0, tiempoRubikDAO.merge(null));
	}

	@MethodRef(name = "delete", signature = "(QTiempoRubikDTO;)I")
	@Test
	public void testDelete() {
		assertEquals(0, tiempoRubikDAO.delete(null));
	}

	@MethodRef(name = "setList", signature = "(QResultSet;)QList<QTiempoRubikDTO;>;")
	@Test
	public void testSetList() throws Exception {
		assertTrue(tiempoRubikDAO.setList(null).isEmpty());
	}

	@Test
	public void testIntegral() {
		assertTrue(tiempoRubikDAO.fixAutoincrement());
		TiempoRubikDTO dto = new TiempoRubikDTO();
		dto.setIdTipoCubo(5);
		dto.setMezcla("mezcla");
		dto.setTiempoInspeccionSegundos(15);
		dto.setTiempoInspeccionUsadoMilisegundos(10000);
		dto.setTiempoInspeccionUsadoTexto("00:10:00");
		dto.setTiempoMilisegundos(20000);
		dto.setTiempoTexto("00:20:00");
		dto.setDnf(false);
		dto.setPenalizacion(false);
		dto.setComentario("prueba");
		dto.setFecha(new Date());
		dto.setEstado(2);
		Integer idTiempoRubik = tiempoRubikDAO.merge(dto);
		assertTrue(idTiempoRubik > 0);
		dto.setIdTiempo(idTiempoRubik);
		List<TiempoRubikDTO> lista = tiempoRubikDAO.findById(idTiempoRubik);
		if (!lista.isEmpty()) {
			TiempoRubikDTO dto2 = lista.get(0);
			assertEquals(dto.getIdTipoCubo(), dto2.getIdTipoCubo());
			assertEquals(dto.getMezcla(), dto2.getMezcla());
			assertEquals(dto.getTiempoInspeccionSegundos(), dto2.getTiempoInspeccionSegundos());
			assertEquals(dto.getTiempoInspeccionUsadoMilisegundos(), dto2.getTiempoInspeccionUsadoMilisegundos());
			assertEquals(dto.getTiempoInspeccionUsadoTexto(), dto2.getTiempoInspeccionUsadoTexto());
			assertEquals(dto.getTiempoMilisegundos(), dto2.getTiempoMilisegundos());
			assertEquals(dto.getTiempoTexto(), dto2.getTiempoTexto());
			assertEquals(dto.getDnf(), dto2.getDnf());
			assertEquals(dto.getPenalizacion(), dto2.getPenalizacion());
			assertEquals(dto.getComentario(), dto2.getComentario());
			assertEquals(dto.getEstado(), dto2.getEstado());

		}
		dto.setIdTipoCubo(6);
		dto.setMezcla("mezcla modificada");
		dto.setTiempoInspeccionSegundos(12);
		dto.setTiempoInspeccionUsadoMilisegundos(9000);
		dto.setTiempoInspeccionUsadoTexto("00:09:00");
		dto.setTiempoMilisegundos(25000);
		dto.setTiempoTexto("00:25:00");
		dto.setDnf(true);
		dto.setPenalizacion(true);
		dto.setComentario("prueba modificada");
		dto.setFecha(new Date());
		dto.setEstado(0);
		assertEquals(idTiempoRubik.intValue(), tiempoRubikDAO.merge(dto));
		lista = tiempoRubikDAO.findById(idTiempoRubik);
		if (!lista.isEmpty()) {
			TiempoRubikDTO dto2 = lista.get(0);
			assertEquals(dto.getIdTipoCubo(), dto2.getIdTipoCubo());
			assertEquals(dto.getMezcla(), dto2.getMezcla());
			assertEquals(dto.getTiempoInspeccionSegundos(), dto2.getTiempoInspeccionSegundos());
			assertEquals(dto.getTiempoInspeccionUsadoMilisegundos(), dto2.getTiempoInspeccionUsadoMilisegundos());
			assertEquals(dto.getTiempoInspeccionUsadoTexto(), dto2.getTiempoInspeccionUsadoTexto());
			assertEquals(dto.getTiempoMilisegundos(), dto2.getTiempoMilisegundos());
			assertEquals(dto.getTiempoTexto(), dto2.getTiempoTexto());
			assertEquals(dto.getDnf(), dto2.getDnf());
			assertEquals(dto.getPenalizacion(), dto2.getPenalizacion());
			assertEquals(dto.getComentario(), dto2.getComentario());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		assertEquals(idTiempoRubik.intValue(), tiempoRubikDAO.delete(dto));
		lista = tiempoRubikDAO.findById(idTiempoRubik);
		assertTrue(lista.isEmpty());
		assertTrue(tiempoRubikDAO.fixAutoincrement());
	}

}
