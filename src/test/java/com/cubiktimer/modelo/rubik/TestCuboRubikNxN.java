/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cubiktimer.controlador.factories.RubikFactory;
import com.cubiktimer.util.ScrambleGenerator;
import com.cubiktimer.util.ScrambleSolver;

public class TestCuboRubikNxN {

	@Test
	public void testMezclar() {
		for (int casos = 0; casos <= 2; casos++) {
			for (int i = 5; i <= 15; i++) {
				Puzzle cubo = RubikFactory.crearCubo(i);
				Puzzle cubo2 = RubikFactory.crearCubo(i);
				String secuenciaMezcla = ScrambleGenerator.generarSecuenciaMezcla(cubo.getParametro());
				String[] secuencia = secuenciaMezcla.split(" ");

				assertEquals("mezcla de cubo " + cubo.getNombre(), secuenciaMezcla, cubo.mezclar(secuencia).trim());
				cubo.mezclar(ScrambleGenerator.generarMezclaInversa(secuenciaMezcla));
				cubo2.mezclar(new String[] { "x" });
				if (cubo instanceof Comprobable) {
					assertTrue("Esta resuelto cubo desarmado y rearmado", ((Comprobable) cubo).estaResuelto());
				}
				if (cubo2 instanceof Comprobable) {
					assertTrue("Esta resuelto cubo rotado", ((Comprobable) cubo2).estaResuelto());
				}
			}
		}
	}

	@Test
	public void testSolucionarDinamico() {
		for (int casos = 0; casos <= 2; casos++) {
			Puzzle cubo = RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3.getId());
			String secuenciaMezcla = ScrambleGenerator.generarSecuenciaMezcla(cubo.getParametro());
			String[] secuencia = secuenciaMezcla.split(" ");
			System.out.println(secuenciaMezcla);
			cubo.mezclar(secuencia);
			String faceletColors = ((FewestMovesSolvable) cubo).faceletToString();
			System.out.println(faceletColors);
			String secuenciaSolucion = ScrambleSolver.generarSecuenciaSolucion(faceletColors);
			System.out.println(secuenciaSolucion);
			String[] secuencia2 = secuenciaSolucion.split(" ");
			cubo.mezclar(secuencia2);
			assertTrue("Esta resuelto con solucion de Cube Explorer", ((Comprobable) cubo).estaResuelto());
		}
	}

	@Test
	public void testSolucionar1() {
		Puzzle cubo = RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3.getId());
		String secuenciaMezcla = "R' U' F U B2 D' F2 R2 D' U2 F2 L2 D' R' D F D L U F2 L F D B2 R' U' F";
		String[] secuencia = secuenciaMezcla.split(" ");
		cubo.mezclar(secuencia);
		String secuenciaSolucion = "D2 F L' D' L D L' F L2 F2 L' F' L F2 D F' D' U L D' L' U' L D L' F2 U' L F' U L' D2 R2 F'";
		String[] secuencia2 = secuenciaSolucion.split(" ");
		cubo.mezclar(secuencia2);
		assertTrue(((Comprobable) cubo).estaResuelto());
	}

	@Test
	public void testSolucionar2() {
		Puzzle cubo = RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3.getId());
		String secuenciaMezcla = "R' U' F R U2 R F2 D2 R' B2 R D2 B D' B' U2 F2 D2 R' B2 D' R' F' R' U' F";
		String[] secuencia = secuenciaMezcla.split(" ");
		cubo.mezclar(secuencia);
		String secuenciaSolucion = "[r2] R' F' [u] U2 [f] F' L F R L D' [f] F D2 F' U2 [r'] U' [u] F2 R2 F2 R2 F2";
		String[] secuencia2 = secuenciaSolucion.split(" ");
		cubo.mezclar(secuencia2);
		assertTrue(((Comprobable) cubo).estaResuelto());
	}

	@Test
	public void testSolucionar3() {
		Puzzle cubo = RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3.getId());
		String secuenciaMezcla = "R' U' F R U2 R F2 D2 R' B2 R D2 B D' B' U2 F2 D2 R' B2 D' R' F' R' U' F";
		String[] secuencia = secuenciaMezcla.split(" ");
		cubo.mezclar(secuencia);
		String secuenciaSolucion = "X2 R' F' Y U2 Z F' L F R L D' Z F D2 F' U2 X' U' Y F2 R2 F2 R2 F2";
		String[] secuencia2 = secuenciaSolucion.split(" ");
		cubo.mezclar(secuencia2);
		assertTrue(((Comprobable) cubo).estaResuelto());
	}

}
