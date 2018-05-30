package xyz.njas;

import java.io.IOException;

public class TestEjecucionJar {
	
	public static void ejecutarJar() {
		try {
			Runtime.getRuntime().exec("java -jar C:\\Disco\\NELSON\\docsJava\\juegos\\src\\main\\webapp\\resources\\jars\\TNoodle-WCA-0.13.3.jar");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
