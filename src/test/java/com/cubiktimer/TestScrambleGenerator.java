package com.cubiktimer;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cubiktimer.util.ScrambleGenerator;

public class TestScrambleGenerator {

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
	public void testGenerarMezcla() {
		String regex2x2 = "^([F|R|U]{1}['|2]?[ ]{1})+([F|R|U]{1}['|2]?[ ]?){1}$";
		String regex3x3 = "^([B|D|F|L|R|U]{1}['|2]?[ ]{1})+([B|D|F|L|R|U]{1}['|2]?[ ]?){1}$";
		assertTrue("prueba scramble 2x2", ScrambleGenerator.generarSecuenciaMezcla("222").matches(regex2x2));
		assertTrue("prueba scramble 3x3", ScrambleGenerator.generarSecuenciaMezcla("333").matches(regex3x3));
	}
}
