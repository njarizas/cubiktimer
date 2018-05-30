package xyz.njas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TestPost {

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