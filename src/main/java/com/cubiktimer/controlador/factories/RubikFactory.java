/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.controlador.factories;

import com.cubiktimer.modelo.rubik.CuboRubik2x2;
import com.cubiktimer.modelo.rubik.CuboRubik3x3;
import com.cubiktimer.modelo.rubik.CuboRubik4x4;
import com.cubiktimer.modelo.rubik.CuboRubik5x5;
import com.cubiktimer.modelo.rubik.CuboRubik6x6;
import com.cubiktimer.modelo.rubik.CuboRubik7x7;
import com.cubiktimer.modelo.rubik.Megaminx;
import com.cubiktimer.modelo.rubik.Puzzle;
import com.cubiktimer.modelo.rubik.Pyraminx;
import com.cubiktimer.modelo.rubik.Skewb;
import com.cubiktimer.modelo.rubik.Square1;
import com.cubiktimer.modelo.rubik.TipoCubo;

public class RubikFactory {

	private RubikFactory() {

	}

	public static Puzzle crearCubo(TipoCubo tipoCubo) {
		return crearCubo(tipoCubo.getId());
	}

	public static Puzzle crearCubo(Integer tipoCubo) {
		switch (tipoCubo) {
		case 5:
			return new CuboRubik2x2(tipoCubo, "2X2X2");
		case 6:
			return new CuboRubik3x3(tipoCubo, "3X3X3");
		case 7:
			return new CuboRubik4x4(tipoCubo, "4X4X4");
		case 8:
			return new CuboRubik5x5(tipoCubo, "5X5X5");
		case 9:
			return new CuboRubik6x6(tipoCubo, "6X6X6");
		case 10:
			return new CuboRubik7x7(tipoCubo, "7X7X7");
		case 11:
			return new CuboRubik3x3(tipoCubo, "3X3X3 BLD");
		case 12:
			return new CuboRubik3x3(tipoCubo, "3X3X3 With Feet");
		case 13:
			return new CuboRubik3x3(tipoCubo, "3X3X3 OH");
		case 14:
			return new CuboRubik4x4(tipoCubo, "4X4X4 BLD");
		case 15:
			return new CuboRubik5x5(tipoCubo, "5X5X5 BLD");
		case 17:
			return new CuboRubik3x3(tipoCubo, "3X3X3 Fewest Moves");
		case 24:
			return new Skewb(tipoCubo, "Skewb");
		case 25:
			return new Megaminx(tipoCubo, "Megaminx");
		case 26:
			return new Pyraminx(tipoCubo, "Pyraminx");
		case 27:
			return new Square1(tipoCubo, "Square 1");
		default:
			return null;
		}
	}

}
