package com.cubiktimer;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cubiktimer.controlador.factories.RubikFactory;
import com.cubiktimer.modelo.rubik.Puzzle;
import com.cubiktimer.util.ScrambleGenerator;

public class TestCuboRubikNxN {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		for (int i = 5; i <= 15; i++) {
			Puzzle cubo = RubikFactory.crearCubo(i);
			String secuenciaMezcla = ScrambleGenerator.generarSecuenciaMezcla(cubo.getParametro());
			String[] secuencia = secuenciaMezcla.split(" ");
			
			assertEquals("mezcla de cubo "+cubo.getNombre(), secuenciaMezcla, cubo.mezclar(secuencia).trim());
		}
		for (int i = 24; i <= 27; i++) {
			Puzzle cubo = RubikFactory.crearCubo(i);
			String secuenciaMezcla = ScrambleGenerator.generarSecuenciaMezcla(cubo.getParametro());
			String[] secuencia = secuenciaMezcla.split(" ");
			
			assertEquals("mezcla de cubo "+cubo.getNombre(), secuenciaMezcla, cubo.mezclar(secuencia).trim());
		}
	}

}
