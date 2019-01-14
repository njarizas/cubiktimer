package com.cubiktimer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cubiktimer.controlador.factories.RubikFactory;
import com.cubiktimer.modelo.rubik.CuboRubik2x2;
import com.cubiktimer.modelo.rubik.CuboRubik3x3;
import com.cubiktimer.modelo.rubik.CuboRubik4x4;
import com.cubiktimer.modelo.rubik.CuboRubik5x5;
import com.cubiktimer.modelo.rubik.CuboRubik6x6;
import com.cubiktimer.modelo.rubik.CuboRubik7x7;
import com.cubiktimer.modelo.rubik.Megaminx;
import com.cubiktimer.modelo.rubik.Pyraminx;
import com.cubiktimer.modelo.rubik.Skewb;
import com.cubiktimer.modelo.rubik.Square1;

public class TestRubikFactory {

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
		assertTrue("creacion de 2x2", RubikFactory.crearCubo(5) instanceof CuboRubik2x2);
		assertTrue("creacion de 3x3", RubikFactory.crearCubo(6) instanceof CuboRubik3x3);
		assertTrue("creacion de 4x4", RubikFactory.crearCubo(7) instanceof CuboRubik4x4);
		assertTrue("creacion de 5x5", RubikFactory.crearCubo(8) instanceof CuboRubik5x5);
		assertTrue("creacion de 6x6", RubikFactory.crearCubo(9) instanceof CuboRubik6x6);
		assertTrue("creacion de 7x7", RubikFactory.crearCubo(10) instanceof CuboRubik7x7);
		assertTrue("creacion de 3x3 BLD", RubikFactory.crearCubo(11) instanceof CuboRubik3x3);
		assertTrue("creacion de 3x3 With Feet", RubikFactory.crearCubo(12) instanceof CuboRubik3x3);
		assertTrue("creacion de 3x3 OH", RubikFactory.crearCubo(13) instanceof CuboRubik3x3);
		assertTrue("creacion de 4x4 BLD", RubikFactory.crearCubo(14) instanceof CuboRubik4x4);
		assertTrue("creacion de 5x5 BLD", RubikFactory.crearCubo(15) instanceof CuboRubik5x5);
		assertTrue("creacion de Skewb", RubikFactory.crearCubo(24) instanceof Skewb);
		assertTrue("creacion de Megaminx", RubikFactory.crearCubo(25) instanceof Megaminx);
		assertTrue("creacion de Pyraminx", RubikFactory.crearCubo(26) instanceof Pyraminx);
		assertTrue("creacion de Square 1", RubikFactory.crearCubo(27) instanceof Square1);
	}

}
