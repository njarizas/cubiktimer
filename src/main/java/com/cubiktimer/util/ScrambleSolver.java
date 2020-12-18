/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import com.cubiktimer.error.ExceptionHandler;

public class ScrambleSolver {

	private static final Logger log = Logger.getLogger(ScrambleGenerator.class);

	private ScrambleSolver() {

	}

	public static String generarSecuenciaSolucion(String parametro) {
		Propiedades prop = Propiedades.getInstance();
		log.trace("Va a generar la solucion con el Software Cube Explorer de Herbert Kociemba");
		URL url;
		String linea;
		StringBuilder stringBuilder = new StringBuilder();
		boolean esConnectException = false;
		try {
			// se intenta obtener la conexion por metodo get
			url = new URL(prop.obtenerPropiedad(Constantes.LLAVE_ENDPOINT_TNOODLE_SERVICE) + parametro);
			URLConnection con = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((linea = in.readLine()) != null) {
				log.trace("solucion generada en el primer intento: " + linea);
				stringBuilder.append(linea);
			}
			// si se genera ConnectException tal vez es por que no se esta ejecutando el
			// Cube Explorer
		} catch (ConnectException ce) {
			esConnectException = true;
			ExceptionHandler.manejarExcepcionLeve(ce);
		} catch (MalformedURLException murle) {
			ExceptionHandler.manejarExcepcionGrave(murle);
		} catch (IOException ioe) {
			ExceptionHandler.manejarExcepcionGrave(ioe);
		}
		try {
			if (esConnectException) {
				// se trata de ejecutar el TNoodle
				log.trace("Se hace el llamado para que se ejecute el " + Constantes.PATH_CUBIKTIMER
						+ prop.obtenerPropiedad(Constantes.LLAVE_RUTA_PROGRAMA_CUBE_EXPLORER));
				Runtime.getRuntime().exec(Constantes.PATH_CUBIKTIMER + prop.obtenerPropiedad(Constantes.LLAVE_RUTA_PROGRAMA_CUBE_EXPLORER));
				// Creando un objeto URL
				url = new URL(prop.obtenerPropiedad(Constantes.LLAVE_ENDPOINT_TNOODLE_SERVICE) + parametro);
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
			ExceptionHandler.manejarExcepcionGrave(e);
		}
		return stringBuilder.toString().replaceAll("</HTML>", "").replaceAll("</BODY>", "").replaceAll("<HTML>", "")
				.replaceAll("<BODY>", "");
	}

}
