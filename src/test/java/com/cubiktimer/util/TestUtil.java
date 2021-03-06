/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.util;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import org.junit.Test;

import com.cubiktimer.error.ExceptionHandler;

import junit.framework.TestCase;

public class TestUtil extends TestCase {

	private Random random;

	public TestUtil() {
		try {
			random = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No se pudo generar variable aleatoria");
			ExceptionHandler.manejarExcepcionGrave(e);
		}
	}

	@Test
	public void testDarFormatoTiempo() {
		assertEquals("Cero milisegundos:", Util.darFormatoTiempo(0), "00:00.00");
		assertEquals("milisegundos exactos:", Util.darFormatoTiempo(20), "00:00.02");
		assertEquals("segundos exactos:", Util.darFormatoTiempo(2000), "00:02.00");
		assertEquals("minutos exactos:", Util.darFormatoTiempo(120000), "02:00.00");
		assertEquals("segundos y milisegundos exactos:", Util.darFormatoTiempo(5050), "00:05.05");
		assertEquals("una hora:", Util.darFormatoTiempo(3600000), "60:00.00");
		assertEquals("maximo permitido sin agregar un nuevo digito:", Util.darFormatoTiempo(5999990), "99:59.99");
		for (int i = 0; i < 10; i++) {
			assertEquals("milisegundos no exactos: " + (i + 1), Util.darFormatoTiempo(i), "00:00.00");
		}
		assertEquals("dos horas:", Util.darFormatoTiempo(7200000), "120:00.00");
		assertEquals("un dia:", Util.darFormatoTiempo(86400000), "1440:00.00");
	}

	@Test
	public void testCalcularMilesimasDeSegundos() {
		assertEquals("Cero milisegundos:", Util.calcularMilesimasDeSegundos("00:00.00"), 0);
		assertEquals("milisegundos exactos:", Util.calcularMilesimasDeSegundos("00:00.02"), 20);
		assertEquals("segundos exactos:", Util.calcularMilesimasDeSegundos("00:02.00"), 2000);
		assertEquals("minutos exactos:", Util.calcularMilesimasDeSegundos("02:00.00"), 120000);
		assertEquals("segundos y milisegundos exactos:", Util.calcularMilesimasDeSegundos("00:05.05"), 5050);
		assertEquals("una hora:", Util.calcularMilesimasDeSegundos("60:00.00"), 3600000);
		assertEquals("maximo permitido sin agregar un nuevo digito:", Util.calcularMilesimasDeSegundos("99:59.99"),
				5999990);
	}

	@Test
	public void testCalcularSegundos() {
		assertEquals("Cero milisegundos:", BigDecimal.valueOf(0.0), Util.calcularSegundos("106:00.00"));
		assertEquals("Cero milisegundos:", BigDecimal.valueOf(0.0), Util.calcularSegundos("00:00.00"));
		assertEquals("milisegundos exactos:", BigDecimal.valueOf(0.02), Util.calcularSegundos("00:00.02"));
		assertEquals("segundos exactos:", BigDecimal.valueOf(2.0), Util.calcularSegundos("00:02.00"));
		assertEquals("minutos exactos:", BigDecimal.valueOf(120.0), Util.calcularSegundos("02:00.00"));
		assertEquals("segundos y milisegundos exactos:", BigDecimal.valueOf(5.05), Util.calcularSegundos("00:05.05"));
		assertEquals("una hora:", BigDecimal.valueOf(3600.0), Util.calcularSegundos("60:00.00"));
		assertEquals("maximo permitido sin agregar un nuevo digito:", BigDecimal.valueOf(5999.99),
				Util.calcularSegundos("99:59.99"));
	}

	@Test
	public void testDoble() {
		for (int i = 0; i < 10; i++) {
			int numero = random.nextInt(5999990);
			assertEquals("prueba doble: " + (i + 1) + ", valor de entrada: " + numero, numero - (numero % 10),
					Util.calcularMilesimasDeSegundos(Util.darFormatoTiempo(numero)));
		}
	}

	@Test
	public void testEncriptarClave() {
		for (int i = 0; i < 10; i++) {
			assertTrue("Prueba de generacion de clave aleatoria expresion regular:" + (i + 1),
					Pattern.matches("[0-9a-zA-Z|!|#|\\$|%|\\+|\\-|\\*|~|\\^|@]{12}", Util.generarClaveAleatoria()));
		}
	}

	@Test
	public void testTraducirSecuenciaWCA() {
		assertEquals("prueba traduciendo mezcla 6x6", Util.traducirSecuenciaWCA(9,
				"D2 Lw Fw2 B' U' R2 D2 3Fw Rw 3Rw2 3Uw' Fw' 3Uw2 R 3Fw Bw2 3Uw2 3Fw Uw2 Rw2 3Uw R' Bw 3Fw2 F 3Uw 3Rw2 D2 R' 3Rw' Fw' F' B' Dw D' L2 R' Lw Uw' Bw 3Fw' L' B2 3Rw 3Uw2 U F R Bw2 R2 Fw B Rw2 Dw2 R2 3Uw Uw L2 3Uw2 F' 3Uw' R D2 Bw Uw2 L' Uw Dw' Fw2 Dw' Bw L' 3Rw Dw2 3Rw2 U2 F' 3Fw Fw2 L"),
				"D2 l f2 B' U' R2 D2 3f r 3r2 3u' f' 3u2 R 3f b2 3u2 3f u2 r2 3u R' b 3f2 F 3u 3r2 D2 R' 3r' f' F' B' d D' L2 R' l u' b 3f' L' B2 3r 3u2 U F R b2 R2 f B r2 d2 R2 3u u L2 3u2 F' 3u' R D2 b u2 L' u d' f2 d' b L' 3r d2 3r2 U2 F' 3f f2 L");
		assertEquals("prueba traduciendo mezcla 7x7", Util.traducirSecuenciaWCA(10,
				"Dw' F' 3Uw2 R 3Dw2 Bw2 3Dw2 3Bw' 3Dw' D 3Uw Bw2 3Lw' 3Uw' R' D' Dw2 3Uw' Fw2 3Dw Lw2 Bw 3Rw' Uw2 3Lw 3Rw' 3Bw R2 D' 3Lw Lw F2 R' Rw Uw2 L 3Uw Bw Lw2 D Bw' Dw R2 B' F' 3Dw2 Fw' Bw2 U' D F 3Dw Bw2 D2 Lw' Fw' 3Rw R2 3Dw2 R 3Dw2 3Lw2 3Fw B Bw' 3Bw 3Dw' 3Bw D' B' Uw2 3Dw' 3Lw Uw 3Dw' Bw 3Dw' Lw' 3Fw' Dw 3Uw L2 3Rw' Bw2 Dw 3Fw2 Uw2 3Bw' Bw 3Dw' Dw L' U Dw 3Rw2 R' Bw2 B2 3Lw2 Uw2"),
				"d' F' 3u2 R 3d2 b2 3d2 3b' 3d' D 3u b2 3l' 3u' R' D' d2 3u' f2 3d l2 b 3r' u2 3l 3r' 3b R2 D' 3l l F2 R' r u2 L 3u b l2 D b' d R2 B' F' 3d2 f' b2 U' D F 3d b2 D2 l' f' 3r R2 3d2 R 3d2 3l2 3f B b' 3b 3d' 3b D' B' u2 3d' 3l u 3d' b 3d' l' 3f' d 3u L2 3r' b2 d 3f2 u2 3b' b 3d' d L' U d 3r2 R' b2 B2 3l2 u2");
		assertEquals("prueba traduciendo mezcla skewb", Util.traducirSecuenciaWCA(24, "L B L R' B' R U L' R' B' L"),
				"L B' L R B R' b L' R B L");
		assertEquals("prueba traduciendo mezcla megaminx", Util.traducirSecuenciaWCA(25,
				"R-- D++ R-- D++ R-- D++ R-- D++ R++ D-- U' R-- D++ R-- D++ R-- D++ R++ D++ R++ D-- U' R-- D++ R-- D++ R++ D++ R++ D-- R++ D-- U' R++ D-- R-- D-- R-- D-- R-- D++ R-- D++ U R++ D-- R++ D-- R++ D-- R++ D-- R-- D++ U R++ D-- R-- D-- R++ D-- R-- D++ R-- D-- U' R++ D-- R++ D-- R++ D-- R++ D-- R++ D-- U'"),
				"br2' d2 br2' d2 br2' d2 br2' d2 br2 d2' U' br2' d2 br2' d2 br2' d2 br2 d2 br2 d2' U' br2' d2 br2' d2 br2 d2 br2 d2' br2 d2' U' br2 d2' br2' d2' br2' d2' br2' d2 br2' d2 U br2 d2' br2 d2' br2 d2' br2 d2' br2' d2 U br2 d2' br2' d2' br2 d2' br2' d2 br2' d2' U' br2 d2' br2 d2' br2 d2' br2 d2' br2 d2' U'");
		assertEquals("prueba traduciendo mezcla square 1", Util.traducirSecuenciaWCA(27,
				"(0,2) / (4,-5) / (2,-1) / (3,0) / (-2,-5) / (-4,-3) / (3,0) / (1,0) / (-4,0) / (-4,-4) / (3,0) / (-2,0) / (3,0)"),
				"D2 / U4 D5' / U2 D' / U3 / U2' D5' / U4' D3' / U3 / U / U4' / U4' D4' / U3 / U2' / U3");
	}

	@Test
	public void testFechaHoraMysql() {
		String regex = "^([0-9]{2,4})-([0-1][0-9])-([0-3][0-9])(?:( [0-2][0-9]):([0-5][0-9]):([0-5][0-9]))?$";
		assertTrue("prueba fechaHoraMysql", Util.getInstance().getFechaMysql().format(new Date()).matches(regex));
		assertTrue("prueba fechaHoraMysql", Util.getInstance().getFechaHoraMysql().format(new Date()).matches(regex));
	}

	@Test
	public void testObtenerLetraDeCaraPorColor() {
		assertEquals("prueba cara B", Util.obtenerLetraDeCaraPorColor(Constantes.COLOR_CARA_B),
				Constantes.LETRA_CARA_B);
		assertEquals("prueba cara D", Util.obtenerLetraDeCaraPorColor(Constantes.COLOR_CARA_D),
				Constantes.LETRA_CARA_D);
		assertEquals("prueba cara F", Util.obtenerLetraDeCaraPorColor(Constantes.COLOR_CARA_F),
				Constantes.LETRA_CARA_F);
		assertEquals("prueba cara L", Util.obtenerLetraDeCaraPorColor(Constantes.COLOR_CARA_L),
				Constantes.LETRA_CARA_L);
		assertEquals("prueba cara R", Util.obtenerLetraDeCaraPorColor(Constantes.COLOR_CARA_R),
				Constantes.LETRA_CARA_R);
		assertEquals("prueba cara U", Util.obtenerLetraDeCaraPorColor(Constantes.COLOR_CARA_U),
				Constantes.LETRA_CARA_U);
	}

}
