package xyz.njas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Test2 {
	
	public static void main(String[] args) {
		ejecutarJar();
		System.out.println(solicitarScramble());
	}
	
	public static void ejecutarJar() {
		try {
			Runtime.getRuntime().exec("java -jar C:\\Disco\\NELSON\\docsJava\\juegos\\src\\main\\webapp\\resources\\jars\\TNoodle-WCA-0.13.3.jar");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String solicitarScramble() {
		URL url;
		String linea;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			// Creando un objeto URL
			url = new URL("http://localhost:2014/scramble/.txt?=333");
			// Realizando la petición GET
			URLConnection con = url.openConnection();
			// Leyendo el resultado
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			
			while ((linea = in.readLine()) != null) {
				System.out.println(linea);
				stringBuffer.append(linea);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return stringBuffer.toString();
	}
}
