/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.cubiktimer.controlador.factories.RubikFactory;
import com.cubiktimer.modelo.dto.TiempoRubikDTO;

import junit.framework.TestCase;

public class TestSesionRubik extends TestCase {

	private SesionRubik sesionRubik;
	private SesionRubik sesionRubik2;
	private SesionRubik sesionRubik3;
	private SesionRubik sesionRubik4;
	private SesionRubik sesionRubik5;
	private SesionRubik sesionRubik6;

	public TestSesionRubik() {
		sesionRubik = new SesionRubik(new Date());
		sesionRubik4 = new SesionRubik(new Date());
		sesionRubik5 = new SesionRubik(new Date(), 1);
		sesionRubik6 = new SesionRubik(new Date(), 1);
		List<Tiempo> listaTiempos = new ArrayList<>();
		List<Tiempo> listaTotal = new ArrayList<>();
		List<Tiempo> listaTotal2 = new ArrayList<>();
		List<Tiempo> listaTotal3 = new ArrayList<>();

		// 11.13 3.47 8.80 DNF 7.07
		TiempoRubikDTO tiempoRubikDTO = new TiempoRubikDTO();
		tiempoRubikDTO.setTiempoMilisegundos(11130);
		Tiempo tiempo = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoRubikDTO);
		listaTiempos.add(tiempo);
		listaTotal.add(tiempo);
		listaTotal2.add(tiempo);

		TiempoRubikDTO tiempoRubikDTO2 = new TiempoRubikDTO();
		tiempoRubikDTO2.setTiempoMilisegundos(3470);
		Tiempo tiempo2 = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoRubikDTO2);
		listaTiempos.add(tiempo2);
		listaTotal.add(tiempo2);
		listaTotal2.add(tiempo2);

		TiempoRubikDTO tiempoRubikDTO3 = new TiempoRubikDTO();
		tiempoRubikDTO3.setTiempoMilisegundos(8800);
		Tiempo tiempo3 = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoRubikDTO3);
		listaTiempos.add(tiempo3);
		listaTotal.add(tiempo3);
		listaTotal2.add(tiempo3);

		TiempoRubikDTO tiempoRubikDTO4 = new TiempoRubikDTO();
		tiempoRubikDTO4.setDnf(true);
		Tiempo tiempo4 = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoRubikDTO4);
		listaTiempos.add(tiempo4);
		listaTotal.add(tiempo4);

		TiempoRubikDTO tiempoRubikDTO5 = new TiempoRubikDTO();
		tiempoRubikDTO5.setTiempoMilisegundos(7070);
		Tiempo tiempo5 = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoRubikDTO5);
		listaTiempos.add(tiempo5);
		listaTotal.add(tiempo5);
		listaTotal2.add(tiempo5);
		sesionRubik.setTiempos(listaTiempos);

		// -----------------------------------------------------------

		sesionRubik2 = new SesionRubik(new Date());
		List<Tiempo> listaTiempos2 = new ArrayList<>();
		// 11.13 3.47
		TiempoRubikDTO tiempoRubik = new TiempoRubikDTO();
		tiempoRubik.setTiempoMilisegundos(11130);
		Tiempo t = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoRubik);
		listaTiempos2.add(t);
		listaTotal.add(t);
		listaTotal2.add(t);

		TiempoRubikDTO tiempoRubik2 = new TiempoRubikDTO();
		tiempoRubik2.setTiempoMilisegundos(3470);
		Tiempo t2 = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoRubik2);
		listaTiempos2.add(t2);
		listaTotal.add(t2);
		listaTotal2.add(t2);

		sesionRubik2.setTiempos(listaTiempos2);

		// -------------------------------------------------------------------

		sesionRubik3 = new SesionRubik(new Date());
		List<Tiempo> lista = new ArrayList<>();
		// 11.13 3.47 8.80 DNF 7.07
		TiempoRubikDTO tiempoDTO = new TiempoRubikDTO();
		tiempoDTO.setTiempoMilisegundos(11130);
		Tiempo tiemp = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoDTO);
		lista.add(tiemp);
		listaTotal.add(tiemp);
		listaTotal2.add(tiemp);

		TiempoRubikDTO tiempoDTO2 = new TiempoRubikDTO();
		tiempoDTO2.setTiempoMilisegundos(3470);
		Tiempo tiemp2 = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoDTO2);
		lista.add(tiemp2);
		listaTotal.add(tiemp2);
		listaTotal2.add(tiemp2);

		TiempoRubikDTO tiempoDTO3 = new TiempoRubikDTO();
		tiempoDTO3.setTiempoMilisegundos(8800);
		tiempoDTO3.setPenalizacion(true);
		Tiempo tiemp3 = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoDTO3);
		lista.add(tiemp3);
		listaTotal.add(tiemp3);
		listaTotal2.add(tiemp3);

		TiempoRubikDTO tiempoDTO4 = new TiempoRubikDTO();
		tiempoDTO4.setDnf(true);
		Tiempo tiemp4 = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoDTO4);
		lista.add(tiemp4);
		listaTotal.add(tiemp4);

		TiempoRubikDTO tiempoDTO5 = new TiempoRubikDTO();
		tiempoDTO5.setDnf(true);
		Tiempo tiemp5 = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoDTO5);
		lista.add(tiemp5);
		listaTotal.add(tiemp5);

		TiempoRubikDTO tiempoDTO6 = new TiempoRubikDTO();
		tiempoDTO6.setTiempoMilisegundos(2500);
		Tiempo tiemp6 = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoDTO6);
		listaTotal2.add(tiemp6);

		TiempoRubikDTO tiempoDTO7 = new TiempoRubikDTO();
		tiempoDTO7.setTiempoMilisegundos(8930);
		Tiempo tiemp7 = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoDTO7);
		listaTotal2.add(tiemp7);

		TiempoRubikDTO tiempoDTO8 = new TiempoRubikDTO();
		tiempoDTO8.setTiempoMilisegundos(12800);
		Tiempo tiemp8 = new Tiempo(RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3), tiempoDTO8);
		listaTotal2.add(tiemp8);

		sesionRubik3.setTiempos(lista);
		sesionRubik4.setTiempos(listaTotal);
		sesionRubik5.setTiempos(listaTotal2);
		sesionRubik6.setTiempos(listaTotal3);

	}

	@Test
	public void testPromedio() {
		assertEquals("00:07.61", sesionRubik.media());
		assertEquals("00:07.30", sesionRubik2.media());
		assertEquals("00:08.46", sesionRubik3.media());
		assertEquals("00:07.83", sesionRubik4.media());
		assertEquals("00:07.89", sesionRubik5.media());
		assertEquals("-:--.--", sesionRubik6.media());
	}

	@Test
	public void testAo5actual() {
		assertEquals("00:09.00", sesionRubik.ao5actual());
		assertEquals("-:--.--", sesionRubik2.ao5actual());
		assertEquals("DNF", sesionRubik3.ao5actual());
		assertEquals("DNF", sesionRubik4.ao5actual());
		assertEquals("00:07.73", sesionRubik5.ao5actual());
		assertEquals("-:--.--", sesionRubik6.ao5actual());
	}

	@Test
	public void testAo12actual() {
		assertEquals("-:--.--", sesionRubik2.ao12actual());
		assertEquals("DNF", sesionRubik4.ao12actual());
		assertEquals("00:07.94", sesionRubik5.ao12actual());
		assertEquals("-:--.--", sesionRubik6.ao12actual());
	}
	
	@Test
	public void testMejor() {
		assertEquals("00:03.47", sesionRubik.mejor());
		assertEquals("00:03.47", sesionRubik2.mejor());
		assertEquals("00:03.47", sesionRubik3.mejor());
		assertEquals("00:03.47", sesionRubik4.mejor());
		assertEquals("00:02.50", sesionRubik5.mejor());
		assertEquals("-:--.--", sesionRubik6.mejor());
	}
	
	@Test
	public void testPeor() {
		assertEquals("DNF", sesionRubik.peor());
		assertEquals("00:11.13", sesionRubik2.peor());
		assertEquals("DNF", sesionRubik3.peor());
		assertEquals("DNF", sesionRubik4.peor());
		assertEquals("00:12.80", sesionRubik5.peor());
		assertEquals("-:--.--", sesionRubik6.peor());
	}

}
