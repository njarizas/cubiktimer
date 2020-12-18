/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestScrambleSolver {

	@Test
	public void testGenerarSecuenciaSolucion() {
		String regex3x3 = "^([B|D|F|L|R|U]{1}['|2]?[ ]{1})+([B|D|F|L|R|U]{1}['|2]?[ ]?){1}$";
		assertTrue("prueba scramble 2x2", ScrambleSolver
				.generarSecuenciaSolucion("ldlduruufdbfdrlbbdrblrfbbfuuurudlbdrdrfrlurllufbfbffld").matches(regex3x3));
		assertTrue("prueba scramble 2x2", ScrambleSolver
				.generarSecuenciaSolucion("llululdrulbrfrdurrlbbufdfdlrlfudfrbddffulbufdbubrbrbdf").matches(regex3x3));
		assertTrue("prueba scramble 2x2", ScrambleSolver
				.generarSecuenciaSolucion("llufuurbubrlrrddurfrruffrflbdbbdludffluulbbldfddbbfdrl").matches(regex3x3));
	}

}
