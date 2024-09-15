/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestScrambleGenerator {

	@Test
	public void testGenerarSecuenciaMezcla() {
		String regex2x2 = "^([F|R|U]{1}['|2]?[ ]{1})+([F|R|U]{1}['|2]?[ ]?){1}$";
		String regex3x3 = "^([B|D|F|L|R|U]{1}['|2]?[ ]{1})+([B|D|F|L|R|U]{1}['|2]?[ ]?){1}$";
		String mezcla =ScrambleGenerator.generarSecuenciaMezcla("222");
		System.out.println(mezcla);
		assertTrue("prueba scramble 2x2", ScrambleGenerator.generarSecuenciaMezcla("222").matches(regex2x2));
		assertTrue("prueba scramble 3x3", ScrambleGenerator.generarSecuenciaMezcla("333").matches(regex3x3));
	}

	@Test
	public void testGenerarSecuenciaMezclaInversa() {
		assertEquals("prueba scramble inverso 2x2", "R' F U2 F2 R' F R U' R' U' R",
				ScrambleGenerator.generarSecuenciaMezclaInversa("R' U R U R' F' R F2 U2 F' R"));
		assertEquals("prueba scramble inverso 3x3", "B L2 D R' L F' R' D' R L' U' B2 U2 B2 U' F2 B2 R2 B2",
				ScrambleGenerator.generarSecuenciaMezclaInversa("B2 R2 B2 F2 U B2 U2 B2 U L R' D R F L' R D' L2 B'"));
		assertEquals("prueba scramble inverso 4x4",
				"R2 F2 D' L2 U' F2 L2 B2 R F' L2 R2 U2 B L2 R2 U' F' Rw2 Fw2 L' Uw2 D U' R2 U' L Fw2 R2 U' R Fw' B' U' R' B L' Rw' Fw' Uw F' Rw Uw2 D",
				ScrambleGenerator.generarSecuenciaMezclaInversa(
						"D' Uw2 Rw' F Uw' Fw Rw L B' R U B Fw R' U R2 Fw2 L' U R2 U D' Uw2 L Fw2 Rw2 F U R2 L2 B' U2 R2 L2 F R' B2 L2 F2 U L2 D F2 R2"));
		assertEquals("prueba scramble inverso 5x5",
				"F' Rw L2 Lw B2 L F U2 R Fw' L' D' Uw B Dw Bw2 Lw' D2 R2 Bw R L2 B2 D' Bw F' Rw Dw2 U2 L2 Bw Rw L' Fw2 D2 Uw L' Bw B U R2 Dw2 B' Uw' Lw2 U2 Fw' L Uw R Uw' Fw2 B D U L' Lw2 Bw' R Fw2",
				ScrambleGenerator.generarSecuenciaMezclaInversa(
						"Fw2 R' Bw Lw2 L U' D' B' Fw2 Uw R' Uw' L' Fw U2 Lw2 Uw B Dw2 R2 U' B' Bw' L Uw' D2 Fw2 L Rw' Bw' L2 U2 Dw2 Rw' F Bw' D B2 L2 R' Bw' R2 D2 Lw Bw2 Dw' B' Uw' D L Fw R' U2 F' L' B2 Lw' L2 Rw' F"));
		assertEquals("prueba scramble inverso 6x6",
				"B R' Uw' Fw' 3Rw' L2 R' Dw2 Lw' Dw F' 3Fw B Uw Rw2 D R Lw 3Fw2 U 3Rw 3Uw Rw B' U 3Uw F' R' Rw2 Dw 3Rw Rw2 Fw Lw2 Dw' Rw' 3Rw' L' 3Fw 3Uw' R Rw' Uw 3Fw Fw B2 Lw' 3Uw' B2 3Fw' D2 U2 3Fw2 L' Rw U' Dw2 Bw' Dw' U2 R Rw' Fw2 F2 3Rw' Rw' 3Fw2 L' D Rw2 3Rw 3Uw Lw2 Fw Lw 3Rw U 3Rw Bw Dw2",
				ScrambleGenerator.generarSecuenciaMezclaInversa(
						"Dw2 Bw' 3Rw' U' 3Rw' Lw' Fw' Lw2 3Uw' 3Rw' Rw2 D' L 3Fw2 Rw 3Rw F2 Fw2 Rw R' U2 Dw Bw Dw2 U Rw' L 3Fw2 U2 D2 3Fw B2 3Uw Lw B2 Fw' 3Fw' Uw' Rw R' 3Uw 3Fw' L 3Rw Rw Dw Lw2 Fw' Rw2 3Rw' Dw' Rw2 R F 3Uw' U' B Rw' 3Uw' 3Rw' U' 3Fw2 Lw' R' D' Rw2 Uw' B' 3Fw' F Dw' Lw Dw2 R L2 3Rw Fw Uw R B'"));
		assertEquals("prueba scramble inverso 7x7",
				"F2 D2 Lw2 3Dw' L' 3Rw' Fw Lw2 U' D2 Lw 3Uw' L 3Lw' Rw' D2 Lw2 3Fw2 Dw' Lw2 B2 Lw2 3Rw 3Uw' Dw 3Dw' Bw B Lw Fw L 3Bw 3Lw' B2 U Uw Dw' 3Uw 3Fw 3Uw' U' Dw 3Fw2 3Lw2 D U2 3Uw2 Dw2 F Lw' Uw' 3Rw' 3Dw' F2 L' 3Lw Rw 3Rw F' Bw2 L2 3Uw 3Lw2 F 3Rw' D2 3Lw B U2 3Uw Fw2 U' Dw' R' 3Fw' R D R' U 3Rw F D' Bw F Rw F D F2 Lw F Rw' L U 3Dw' Rw2 3Bw2 U' Bw U2 Lw2",
				ScrambleGenerator.generarSecuenciaMezclaInversa(
						"Lw2 U2 Bw' U 3Bw2 Rw2 3Dw U' L' Rw F' Lw' F2 D' F' Rw' F' Bw' D F' 3Rw' U' R D' R' 3Fw R Dw U Fw2 3Uw' U2 B' 3Lw' D2 3Rw F' 3Lw2 3Uw' L2 Bw2 F 3Rw' Rw' 3Lw' L F2 3Dw 3Rw Uw Lw F' Dw2 3Uw2 U2 D' 3Lw2 3Fw2 Dw' U 3Uw 3Fw' 3Uw' Dw Uw' U' B2 3Lw 3Bw' L' Fw' Lw' B' Bw' 3Dw Dw' 3Uw 3Rw' Lw2 B2 Lw2 Dw 3Fw2 Lw2 D2 Rw 3Lw L' 3Uw Lw' D2 U Lw2 Fw' 3Rw L 3Dw Lw2 D2 F2"));

		assertEquals("prueba scramble inverso skewb", "R U L B R B L R B U L'",
				ScrambleGenerator.generarSecuenciaMezclaInversa("L U' B' R' L' B' R' B' L' U' R'"));
		assertEquals("prueba scramble inverso megaminx",
				"R++ D-- R++ D-- R-- D-- R++ D++ R++ D++ U R++ D-- R++ D++ R-- D-- R++ D++ R-- D-- U' R++ D++ R++ D++ R-- D++ R++ D-- R++ D-- U' R++ D-- R++ D-- R-- D-- R-- D++ R++ D++ U R++ D++ R++ D-- R-- D++ R++ D++ R-- D-- U' R-- D-- R-- D++ R++ D++ R-- D++ R-- D++ U R-- D++ R-- D++ R-- D-- R-- D++ R-- D-- U'",
				ScrambleGenerator.generarSecuenciaMezclaInversa(
						"U D++ R++ D-- R++ D++ R++ D-- R++ D-- R++ U' D-- R++ D-- R++ D-- R-- D-- R++ D++ R++ U D++ R++ D-- R-- D-- R++ D++ R-- D-- R-- U' D-- R-- D-- R++ D++ R++ D++ R-- D++ R-- U D++ R-- D++ R-- D-- R++ D-- R-- D-- R-- U D++ R++ D-- R-- D++ R++ D-- R-- D++ R-- U' D-- R-- D-- R-- D++ R++ D++ R-- D++ R--"));
		assertEquals("prueba scramble inverso pyraminx", "B R L U B R B' L B R' B' r",
				ScrambleGenerator.generarSecuenciaMezclaInversa("r' B R B' L' B R' B' U' L' R' B'"));
		assertEquals("prueba scramble inverso square1", "(0,-4) / (-3,0) / (-3,0) / (6,-3) / (1,-2) / (0,-1) / (3,0) / (-3,0) / (5,-5) / (2,0) / (0,-2)",
				ScrambleGenerator.generarSecuenciaMezclaInversa("(0,2) / (-2,0) / (-5,5) / (3,0) / (-3,0) / (0,1) / (-1,2) / (-6,3) / (3,0) / (3,0) / (0,4)"));
		
	}
}
