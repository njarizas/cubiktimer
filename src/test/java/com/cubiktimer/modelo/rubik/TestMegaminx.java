/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cubiktimer.controlador.factories.RubikFactory;
import com.cubiktimer.util.ScrambleGenerator;

public class TestMegaminx {

	@Test
	public void testMezclar() {
		for (int casos = 0; casos <= 2; casos++) {
				Puzzle cubo = RubikFactory.crearCubo(25);
				String secuenciaMezcla = ScrambleGenerator.generarSecuenciaMezcla(cubo.getParametro());
				String[] secuencia = secuenciaMezcla.split(" ");
				assertEquals("mezcla de cubo " + cubo.getNombre(), secuenciaMezcla, cubo.mezclar(secuencia).trim());
				
				Puzzle cubo2 = RubikFactory.crearCubo(TipoCubo.MEGAMINX);
				secuenciaMezcla = ScrambleGenerator.generarSecuenciaMezcla(cubo2.getParametro());
				secuencia = secuenciaMezcla.split(" ");
				assertEquals("mezcla de cubo " + cubo2.getNombre(), secuenciaMezcla, cubo2.mezclar(secuencia).trim());
				
				secuenciaMezcla = "B I R D F L U";
				secuencia = secuenciaMezcla.split(" ");
				assertEquals("mezcla de cubo " + cubo2.getNombre(), "U", cubo2.mezclar(secuencia).trim());
		}
	}

}
