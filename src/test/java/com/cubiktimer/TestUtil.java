package com.cubiktimer;

import java.util.Random;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cubiktimer.util.Util;

import junit.framework.TestCase;

public class TestUtil extends TestCase {

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
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
		assertEquals("maximo permitido sin agregar un nuevo digito:", Util.calcularMilesimasDeSegundos("99:59.99"), 5999990);
	}
	
	@Test
	public void testCalcularSegundos() {
		assertEquals("Cero milisegundos:", Util.calcularSegundos("00:00.00"), 0.0);
		assertEquals("milisegundos exactos:", Util.calcularSegundos("00:00.02"), 0.02);
		assertEquals("segundos exactos:", Util.calcularSegundos("00:02.00"), 2.0);
		assertEquals("minutos exactos:", Util.calcularSegundos("02:00.00"), 120.0);
		assertEquals("segundos y milisegundos exactos:", Util.calcularSegundos("00:05.05"), 5.05);
		assertEquals("una hora:", Util.calcularSegundos("60:00.00"), 3600.0);
		assertEquals("maximo permitido sin agregar un nuevo digito:", Util.calcularSegundos("99:59.99"), 5999.99);
	}
	
	public void testDoble() {
		Random r = new Random();
		for (int i=0;i<10;i++) {
			int numero = r.nextInt(5999990);
			assertEquals("prueba doble: "+(i+1) +", valor de entrada: "+numero, numero-(numero%10),Util.calcularMilesimasDeSegundos(Util.darFormatoTiempo(numero)));
		}
	}

	@Test
	public void testEncriptarClave() {
		for (int i = 0; i < 10; i++) {
			assertTrue("Prueba de generacion de clave aleatoria expresion regular:" + (i + 1),
					Pattern.matches("[0-9a-zA-Z|!|#|\\$|%|\\+|\\-|\\*|~|\\^|@]{12}", Util.generarClaveAleatoria()));
		}
	}

}
