package xyz.njas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class Test2 {
	
	private static final Logger log = Logger.getLogger(Test2.class);
	
	public static void main(String[] args) {
		ejecutarJar();
		System.out.println(solicitarScramble());
	}
	
	public static void ejecutarJar() {
		try {
			Runtime.getRuntime().exec("java -jar C:\\cubiktimer\\TNoodle-WCA-0.13.5.jar");
		} catch (IOException e) {
			log.warn(e.getMessage());
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
