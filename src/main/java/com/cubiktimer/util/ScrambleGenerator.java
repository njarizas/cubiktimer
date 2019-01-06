package com.cubiktimer.util;

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

	private ScrambleGenerator() {

	}

	public static String[] generarMezcla(String parametro) {
		log.trace("Va a generar la mezcla con el Software oficial de la WCA");
		URL url;
		String linea;
		StringBuilder stringBuilder = new StringBuilder();
		boolean esConnectException = false;
		try {
			// se intenta obtener la conexion por metodo get
			url = new URL("http://localhost:2014/scramble/.txt?=" + parametro);
			URLConnection con = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			stringBuilder = new StringBuilder();
			while ((linea = in.readLine()) != null) {
				log.trace("mezcla generada en el primer intento: " + linea);
				stringBuilder.append(linea);
			}
			// si se genera ConnectException tal vez es por que no se esta ejecutando el
			// TNoodle
		} catch (ConnectException ce) {
			esConnectException = true;
			log.warn(ce.getMessage());
		} catch (MalformedURLException murle) {
			log.warn(murle.getMessage());
		} catch (IOException ioe) {
			log.warn(ioe.getMessage());
		}
		try {
			if (esConnectException) {
				// se trata de ejecutar el TNoodle
				log.trace("Se hace el llamado para que se ejecute el jar java -jar "+Constantes.PATH_CUBIKTIMER+"TNoodle-WCA-0.14.0.jar");
				Runtime.getRuntime().exec("java -jar "+Constantes.PATH_CUBIKTIMER+"TNoodle-WCA-0.14.0.jar");
				// Creando un objeto URL
				url = new URL("http://localhost:2014/scramble/.txt?=" + parametro);
				// Realizando la petición GET
				URLConnection con = url.openConnection();
				// Leyendo el resultado
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				stringBuilder = new StringBuilder();
				while ((linea = in.readLine()) != null) {
					log.trace("mezcla generada en el segundo intento: " + linea);
					stringBuilder.append(linea);
				}
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		return stringBuilder.toString().split(" ");
	}

}
