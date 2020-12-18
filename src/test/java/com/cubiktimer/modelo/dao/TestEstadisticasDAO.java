/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestEstadisticasDAO {
	static EstadisticasDAO estadisticasDAO;

	@BeforeClass
	public static void init() {
		estadisticasDAO = new EstadisticasDAO();
	}

	@Test
	public void testConsultarIdPuzzleMasPracticado() {
		assertNotNull("consultarIdPuzzleMasPracticado", estadisticasDAO.consultarIdPuzzleMasPracticado(2));
	}

	@Test
	public void testObtenerIdCategoriasRegistradas() {
		assertNotNull("parametro host_cubiktimer", estadisticasDAO.obtenerIdCategoriasRegistradas(2));
	}

	@Test
	public void testObtenerListaConteoPuzzles() {
		assertNotNull("obtenerListaConteoPuzzles", estadisticasDAO.obtenerListaConteoPuzzles(2));
	}

	@Test
	public void testObtenerListaPBSingle() {
		assertNotNull("obtenerListaPBSingle", estadisticasDAO.obtenerListaPBSingle(2));
	}

	@Test
	public void testObtenerListaPromediosCategoria() {
		assertNotNull("obtenerListaPromediosCategoria", estadisticasDAO.obtenerListaPromediosCategoria(2, 5));
	}

	@Test
	public void testObtenerListaPromediosCategoriaComparacion() {
		assertNotNull("obtenerListaPromediosCategoriaComparacion",
				estadisticasDAO.obtenerListaPromediosCategoriaComparacion(2, 5));
	}

	@Test
	public void testObtenerListaPromediosComparacion() {
		assertNotNull("obtenerListaPromediosComparacion", estadisticasDAO.obtenerListaPromediosComparacion(2, 3));
	}

	@Test
	public void testObtenerListaPromediosTotales() {
		assertNotNull("obtenerListaPromediosTotales", estadisticasDAO.obtenerListaPromediosTotales(2));
	}

}
