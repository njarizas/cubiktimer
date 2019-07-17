package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

import com.cubiktimer.modelo.dto.FewestMovesDTO;

public class TestFewestMovesDAO {
	static FewestMovesDAO fewestMovesDAO;

	@BeforeClass
	public static void init() {
		fewestMovesDAO = new FewestMovesDAO();
	}

	@Test
	public void testFindAll() {
		assertNotNull("registros en Fewest moves", fewestMovesDAO.findAll());
	}

	@MethodRef(name = "create", signature = "(QFewestMovesDTO;)I")
	@Test
	public void testCreate() {
		assertEquals(0, fewestMovesDAO.create(null));
	}

	@MethodRef(name = "update", signature = "(QFewestMovesDTO;)I")
	@Test
	public void testUpdate() {
		assertEquals(0, fewestMovesDAO.update(null));
	}

	@MethodRef(name = "merge", signature = "(QFewestMovesDTO;)I")
	@Test
	public void testMerge() {
		assertEquals(0, fewestMovesDAO.merge(null));
	}

	@MethodRef(name = "delete", signature = "(QFewestMovesDTO;)I")
	@Test
	public void testDelete() {
		assertEquals(0, fewestMovesDAO.delete(null));
	}

	@MethodRef(name = "setList", signature = "(QResultSet;)QList<QFewestMovesDTO;>;")
	@Test
	public void testSetList() throws Exception {
		assertTrue(fewestMovesDAO.setList(null).isEmpty());
	}

	@Test
	public void testIntegral() {
		assertTrue(fewestMovesDAO.fixAutoincrement());
		FewestMovesDTO dto = new FewestMovesDTO();
		dto.setIdTipoCubo(5);
		dto.setMezcla("mezcla");
		dto.setTiempoUsadoMilisegundos(10000);
		dto.setTiempoRestanteTexto("30:00");
		dto.setSolucion("solucion");
		dto.setLongitudSolucion(6);
		dto.setSolucionValida(true);
		dto.setDnf(false);
		dto.setEstado(1);
		Integer idFewestMove = fewestMovesDAO.merge(dto);
		assertTrue(idFewestMove > 0);
		dto.setIdFewestMove(idFewestMove);
		List<FewestMovesDTO> lista = fewestMovesDAO.findById(idFewestMove);
		if (!lista.isEmpty()) {
			FewestMovesDTO dto2 = lista.get(0);
			assertEquals(dto.getIdTipoCubo(), dto2.getIdTipoCubo());
			assertEquals(dto.getMezcla(), dto2.getMezcla());
			assertEquals(dto.getTiempoUsadoMilisegundos(), dto2.getTiempoUsadoMilisegundos());
			assertEquals(dto.getTiempoRestanteTexto(), dto2.getTiempoRestanteTexto());
			assertEquals(dto.getSolucion(), dto2.getSolucion());
			assertEquals(dto.getSolucionValida(), dto2.getSolucionValida());
			assertEquals(dto.getDnf(), dto2.getDnf());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		dto.setIdTipoCubo(5);
		dto.setMezcla("mezcla_modificada");
		dto.setTiempoUsadoMilisegundos(100);
		dto.setTiempoRestanteTexto("12:00");
		dto.setSolucion("solucion_modificada");
		dto.setLongitudSolucion(4);
		dto.setSolucionValida(false);
		dto.setDnf(true);
		dto.setEstado(0);
		assertEquals(idFewestMove.intValue(), fewestMovesDAO.merge(dto));
		lista = fewestMovesDAO.findById(idFewestMove);
		if (!lista.isEmpty()) {
			FewestMovesDTO dto2 = lista.get(0);
			assertEquals(dto.getIdTipoCubo(), dto2.getIdTipoCubo());
			assertEquals(dto.getMezcla(), dto2.getMezcla());
			assertEquals(dto.getTiempoUsadoMilisegundos(), dto2.getTiempoUsadoMilisegundos());
			assertEquals(dto.getTiempoRestanteTexto(), dto2.getTiempoRestanteTexto());
			assertEquals(dto.getSolucion(), dto2.getSolucion());
			assertEquals(dto.getSolucionValida(), dto2.getSolucionValida());
			assertEquals(dto.getDnf(), dto2.getDnf());
			assertEquals(dto.getEstado(), dto2.getEstado());
		}
		assertEquals(idFewestMove.intValue(), fewestMovesDAO.delete(dto));
		lista = fewestMovesDAO.findById(idFewestMove);
		assertTrue(lista.isEmpty());
		assertTrue(fewestMovesDAO.fixAutoincrement());
	}

}
