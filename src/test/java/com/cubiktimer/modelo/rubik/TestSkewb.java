package com.cubiktimer.modelo.rubik;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cubiktimer.controlador.factories.RubikFactory;
import com.cubiktimer.modelo.rubik.Puzzle;
import com.cubiktimer.modelo.rubik.TipoCubo;
import com.cubiktimer.util.ScrambleGenerator;

public class TestSkewb {

	@Test
	public void testMezclar() {
		for (int casos = 0; casos <= 2; casos++) {
				Puzzle cubo = RubikFactory.crearCubo(24);
				String secuenciaMezcla = ScrambleGenerator.generarSecuenciaMezcla(cubo.getParametro());
				String[] secuencia = secuenciaMezcla.split(" ");
				assertEquals("mezcla de cubo " + cubo.getNombre(), secuenciaMezcla, cubo.mezclar(secuencia).trim());
				
				Puzzle cubo2 = RubikFactory.crearCubo(TipoCubo.SKEWB);
				secuenciaMezcla = ScrambleGenerator.generarSecuenciaMezcla(cubo2.getParametro());
				secuencia = secuenciaMezcla.split(" ");
				assertEquals("mezcla de cubo " + cubo2.getNombre(), secuenciaMezcla, cubo2.mezclar(secuencia).trim());
				
				secuenciaMezcla = "B I R D F L U";
				secuencia = secuenciaMezcla.split(" ");
				assertEquals("mezcla de cubo " + cubo2.getNombre(), "B R L U", cubo2.mezclar(secuencia).trim());
		}
	}

}
