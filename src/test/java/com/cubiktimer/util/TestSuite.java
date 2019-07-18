package com.cubiktimer.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(

{ TestEmailSenderService.class, TestScrambleGenerator.class, TestTestScrambleSolver.class, TestEncriptService.class,
		TestScrambleSolver.class, TestUtil.class })
public class TestSuite { // nothing
}
