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
		String regex = "^([R|F|U]{1}['|2]?[ ]?)+$";
		assertTrue("prueba scramble 2x2", ScrambleGenerator.generarSecuenciaMezcla("222").matches(regex));
		;
	}
}
