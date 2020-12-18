/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik;

import org.junit.Test;

import com.cubiktimer.util.Constantes;

import junit.framework.TestCase;

public class TestCaraRubik extends TestCase {

	@Test
	public void testGirarCara() {
		CaraRubik cara = new CaraRubik(10, Constantes.COLOR_BLANCO);
		for (int i = 0; i < cara.getN(); i++) {
			for (int j = 0; j < cara.getN(); j++) {
				cara.getCara()[i][j] = new Celda(String.valueOf((i * 10) + j + "   "));
			}
		}
		cara.girarCara(4);
		CaraRubik cara2 = new CaraRubik(10, Constantes.COLOR_BLANCO);
		for (int i = 0; i < cara2.getN(); i++) {
			for (int j = 0; j < cara2.getN(); j++) {
				cara2.getCara()[i][j] = new Celda(String.valueOf((i * 10) + j + "   "));
			}
		}
		assertEquals("Prueba de cuatro giros a una cara", cara, cara2);
		CaraRubik cara1234 = new CaraRubik(2, Constantes.COLOR_BLANCO);
		cara1234.getCara()[0][0] = new Celda(String.valueOf(1 + "   "));
		cara1234.getCara()[0][1] = new Celda(String.valueOf(2 + "   "));
		cara1234.getCara()[1][0] = new Celda(String.valueOf(3 + "   "));
		cara1234.getCara()[1][1] = new Celda(String.valueOf(4 + "   "));
		cara1234.girarCara();
		CaraRubik cara3142 = new CaraRubik(2, Constantes.COLOR_BLANCO);
		cara3142.getCara()[0][0] = new Celda(String.valueOf(3 + "   "));
		cara3142.getCara()[0][1] = new Celda(String.valueOf(1 + "   "));
		cara3142.getCara()[1][0] = new Celda(String.valueOf(4 + "   "));
		cara3142.getCara()[1][1] = new Celda(String.valueOf(2 + "   "));
		assertEquals("Prueba de cuatro giros a una cara", cara1234, cara3142);

		CaraRubik cara123 = new CaraRubik(3, Constantes.COLOR_BLANCO);
		cara123.getCara()[0][0] = new Celda(String.valueOf(1 + "   "));
		cara123.getCara()[0][1] = new Celda(String.valueOf(2 + "   "));
		cara123.getCara()[0][2] = new Celda(String.valueOf(3 + "   "));
		cara123.getCara()[1][0] = new Celda(String.valueOf(4 + "   "));
		cara123.getCara()[1][1] = new Celda(String.valueOf(5 + "   "));
		cara123.getCara()[1][2] = new Celda(String.valueOf(6 + "   "));
		cara123.getCara()[2][0] = new Celda(String.valueOf(7 + "   "));
		cara123.getCara()[2][1] = new Celda(String.valueOf(8 + "   "));
		cara123.getCara()[2][2] = new Celda(String.valueOf(9 + "   "));
		cara123.girarCara(2);
		CaraRubik cara987 = new CaraRubik(3, Constantes.COLOR_BLANCO);
		cara987.getCara()[0][0] = new Celda(String.valueOf(9 + "   "));
		cara987.getCara()[0][1] = new Celda(String.valueOf(8 + "   "));
		cara987.getCara()[0][2] = new Celda(String.valueOf(7 + "   "));
		cara987.getCara()[1][0] = new Celda(String.valueOf(6 + "   "));
		cara987.getCara()[1][1] = new Celda(String.valueOf(5 + "   "));
		cara987.getCara()[1][2] = new Celda(String.valueOf(4 + "   "));
		cara987.getCara()[2][0] = new Celda(String.valueOf(3 + "   "));
		cara987.getCara()[2][1] = new Celda(String.valueOf(2 + "   "));
		cara987.getCara()[2][2] = new Celda(String.valueOf(1 + "   "));
		assertEquals("Prueba de cuatro giros a una cara", cara123, cara987);
	}

}
