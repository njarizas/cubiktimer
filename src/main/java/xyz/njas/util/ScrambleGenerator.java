package xyz.njas.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class ScrambleGenerator {
	
	private static final Logger log = Logger.getLogger(ScrambleGenerator.class);

	public static String[] generarMezcla(String parametro) {
		System.out.println("Va a generar la mezcla con el Software oficial de la WCA");
		URL url;
		String linea;
		StringBuffer stringBuffer = new StringBuffer();
		boolean esConnectException=false;
		try {
			//se intenta obtener la conexion por metodo get
			url = new URL("http://localhost:2014/scramble/.txt?="+parametro);
			URLConnection con = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			stringBuffer = new StringBuffer();
			while ((linea = in.readLine()) != null) {
				System.out.println("mezcla generada en el primer intento: " + linea);
				stringBuffer.append(linea);
			}
			//si se genera ConnectException tal vez es por que no se esta ejecutando el TNoodle
		} catch (ConnectException ce) {
			esConnectException = true;
			log.warn(ce.getMessage());
		} catch (MalformedURLException murle) {
			log.warn(murle.getMessage());
		} catch (IOException ioe) {
			log.warn(ioe.getMessage());
		}
		try {
			if(esConnectException) {
				//se trata de ejecutar el TNoodle
				System.out.println("Se hace el llamado para que se ejecute el jar java -jar C:\\TNoodle-WCA-0.13.5.jar");
				Runtime.getRuntime().exec("java -jar C:\\cubiktimer\\TNoodle-WCA-0.13.5.jar");
				// Creando un objeto URL
				url = new URL("http://localhost:2014/scramble/.txt?="+parametro);
				// Realizando la petición GET
				URLConnection con = url.openConnection();
				// Leyendo el resultado
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				stringBuffer = new StringBuffer();
				while ((linea = in.readLine()) != null) {
					System.out.println("mezcla generada en el segundo intento: " + linea);
					stringBuffer.append(linea);
				}
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		return stringBuffer.toString().split(" ");
	}
	
}
