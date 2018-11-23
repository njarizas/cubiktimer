package com.cubiktimer.controlador.factories;

import com.cubiktimer.modelo.rubik.CuboRubik2x2;
import com.cubiktimer.modelo.rubik.CuboRubik3x3;
import com.cubiktimer.modelo.rubik.CuboRubik4x4;
import com.cubiktimer.modelo.rubik.CuboRubik5x5;
import com.cubiktimer.modelo.rubik.CuboRubik6x6;
import com.cubiktimer.modelo.rubik.Puzzle;
import com.cubiktimer.modelo.rubik.Skewb;

public class RubikFactory {
	
	RubikFactory(){
		
	}
	
	public static Puzzle crearCubo(Integer tipoCubo){
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
		case 24:
			return new Skewb(tipoCubo, "Skewb");
		default:
			return null;
		}
	}

}
