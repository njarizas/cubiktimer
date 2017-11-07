package com.sigolf.juegos.rubik.util;

import com.sigolf.juegos.rubik.modelo.CuboRubik2x2;
import com.sigolf.juegos.rubik.modelo.CuboRubik3x3;
import com.sigolf.juegos.rubik.modelo.CuboRubik4x4;
import com.sigolf.juegos.rubik.modelo.Puzzle;

public class RubikFactory {
	
	public static Puzzle crearCubo(Integer tipoCubo){
		switch (tipoCubo) {
		case 5:
			return new CuboRubik2x2(tipoCubo, "2X2X2");
		case 6:
			return new CuboRubik3x3(tipoCubo, "3X3X3");
		case 7:
			return new CuboRubik4x4(tipoCubo, "4X4X4");
		case 11:
			return new CuboRubik3x3(tipoCubo, "3X3X3 BLD");
		case 12:
			return new CuboRubik3x3(tipoCubo, "3X3X3 With Feet");
		case 13:
			return new CuboRubik3x3(tipoCubo, "3X3X3 OH");
		default:
			return null;
		}
	}

}
