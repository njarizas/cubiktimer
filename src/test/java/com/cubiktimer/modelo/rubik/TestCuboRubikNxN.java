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
				cubo.mezclar(ScrambleGenerator.generarMezclaInversa(secuenciaMezcla)).trim();
				cubo2.mezclar(new String[] { "x" });
				assertTrue("Esta resuelto cubo desarmado y rearmado", cubo.estaResuelto());
				assertTrue("Esta resuelto cubo rotado", cubo2.estaResuelto());
			}
		}
	}

	@Test
	public void testSolucionar() {
		for (int casos = 0; casos <= 2; casos++) {
			Puzzle cubo = RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3.getId());
			String secuenciaMezcla = ScrambleGenerator.generarSecuenciaMezcla(cubo.getParametro());
			String[] secuencia = secuenciaMezcla.split(" ");
			System.out.println(secuenciaMezcla);
			cubo.mezclar(secuencia).trim();
			String faceletColors = cubo.faceletToString();
			System.out.println(faceletColors);
			String secuenciaSolucion = ScrambleSolver.generarSecuenciaSolucion(faceletColors);
			System.out.println(secuenciaSolucion);
			String[] secuencia2 = secuenciaSolucion.split(" ");
			cubo.mezclar(secuencia2).trim();
			assertTrue("Esta resuelto con solucion de Cube Explorer", cubo.estaResuelto());
		}
	}

}
