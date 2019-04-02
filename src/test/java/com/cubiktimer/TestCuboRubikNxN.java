package com.cubiktimer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cubiktimer.controlador.factories.RubikFactory;
import com.cubiktimer.modelo.rubik.Puzzle;
import com.cubiktimer.util.ScrambleGenerator;

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
				assertEquals("cubo desarmado y rearmado " + cubo.getNombre(), cubo, cubo2);
			}
		}
	}

}
