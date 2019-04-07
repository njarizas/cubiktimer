package com.cubiktimer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import com.cubiktimer.modelo.rubik.TipoCubo;

public class TestRubikFactory {

	@Test
	public void testCrearCubo() {
		assertTrue("creacion de 2x2", RubikFactory.crearCubo(TipoCubo.CUBO_2X2X2) instanceof CuboRubik2x2);
		assertTrue("creacion de 3x3", RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3) instanceof CuboRubik3x3);
		assertTrue("creacion de 4x4", RubikFactory.crearCubo(TipoCubo.CUBO_4X4X4) instanceof CuboRubik4x4);
		assertTrue("creacion de 5x5", RubikFactory.crearCubo(TipoCubo.CUBO_5X5X5) instanceof CuboRubik5x5);
		assertTrue("creacion de 6x6", RubikFactory.crearCubo(TipoCubo.CUBO_6X6X6) instanceof CuboRubik6x6);
		assertTrue("creacion de 7x7", RubikFactory.crearCubo(TipoCubo.CUBO_7X7X7) instanceof CuboRubik7x7);
		assertTrue("creacion de 3x3 BLD", RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3_BLD) instanceof CuboRubik3x3);
		assertTrue("creacion de 3x3 With Feet",
				RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3_WITH_FEET) instanceof CuboRubik3x3);
		assertTrue("creacion de 3x3 OH", RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3_OH) instanceof CuboRubik3x3);
		assertTrue("creacion de 4x4 BLD", RubikFactory.crearCubo(TipoCubo.CUBO_4X4X4_BLD) instanceof CuboRubik4x4);
		assertTrue("creacion de 5x5 BLD", RubikFactory.crearCubo(TipoCubo.CUBO_5X5X5_BLD) instanceof CuboRubik5x5);
		assertTrue("creacion de 3x3 Fewest Moves",
				RubikFactory.crearCubo(TipoCubo.CUBO_3X3X3_FEWEST_MOVES) instanceof CuboRubik3x3);
		assertTrue("creacion de Skewb", RubikFactory.crearCubo(TipoCubo.SKEWB) instanceof Skewb);
		assertTrue("creacion de Megaminx", RubikFactory.crearCubo(TipoCubo.MEGAMINX) instanceof Megaminx);
		assertTrue("creacion de Pyraminx", RubikFactory.crearCubo(TipoCubo.PYRAMINX) instanceof Pyraminx);
		assertTrue("creacion de Square 1", RubikFactory.crearCubo(TipoCubo.SQUARE_1) instanceof Square1);
		assertEquals("creacion tipo cubo no valido", null, RubikFactory.crearCubo(1));
	}

}
