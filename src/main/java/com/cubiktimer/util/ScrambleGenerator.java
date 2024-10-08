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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cubiktimer.error.ExceptionHandler;

public class ScrambleGenerator {

	private static final Logger log = LogManager.getLogger(ScrambleGenerator.class);

	private ScrambleGenerator() {

	}

	public static String generarSecuenciaMezcla(String parametro) {
		log.trace("Va a generar la mezcla con el Software oficial de la WCA");
		Propiedades propiedades = Propiedades.getInstance();
		URL url;
		String linea;
		StringBuilder stringBuilder = new StringBuilder();
		boolean esConnectException = false;
		try {
			// se intenta obtener la conexion por metodo get
			url = new URL(propiedades.obtenerPropiedad(Constantes.LLAVE_ENDPOINT_TNOODLE_SERVICE) + parametro);
			URLConnection con = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((linea = in.readLine()) != null) {
				log.trace("mezcla generada en el primer intento: " + linea);
				stringBuilder.append(linea);
			}
			// si se genera ConnectException tal vez es por que no se esta ejecutando el
			// TNoodle
		} catch (ConnectException ce) {
			esConnectException = true;
			ExceptionHandler.manejarExcepcionLeve(ce);
		} catch (MalformedURLException murle) {
			log.warn(murle.getMessage());
			ExceptionHandler.manejarExcepcionGrave(murle);
		} catch (IOException ioe) {
			log.warn(ioe.getMessage());
			ExceptionHandler.manejarExcepcionGrave(ioe);
		}
		try {
			if (esConnectException) {
				// se trata de ejecutar el TNoodle
				log.trace("Se hace el llamado para que se ejecute el jar java -jar " + Constantes.PATH_CUBIKTIMER
						+ propiedades.obtenerPropiedad(Constantes.LLAVE_RUTA_PROGRAMA_TNOODLE));
				Runtime.getRuntime().exec("java -jar " + Constantes.PATH_CUBIKTIMER + propiedades.obtenerPropiedad(Constantes.LLAVE_RUTA_PROGRAMA_TNOODLE));
				// Creando un objeto URL
				url = new URL(propiedades.obtenerPropiedad(Constantes.LLAVE_ENDPOINT_TNOODLE_SERVICE) + parametro);
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
		return stringBuilder.toString();
	}

	public static String[] generarMezcla(String parametro) {
		return generarSecuenciaMezcla(parametro).split(" ");
	}

	public static String[] generarMezclaInversa(String secuenciaMezcla) {
		return generarSecuenciaMezclaInversa(secuenciaMezcla).split(" ");
	}

	public static String generarSecuenciaMezclaInversa(String secuenciaMezcla) {
		return generarSecuenciaMezclaInversa(secuenciaMezcla.split(" "));
	}

	public static String generarSecuenciaMezclaInversa(String[] secuenciaMezcla) {
		StringBuilder stringBuilder = new StringBuilder();
		List<String> listaGiros = Arrays.asList(secuenciaMezcla);
		Collections.reverse(listaGiros);
		for (String string : listaGiros) {
			stringBuilder.append(generarGiroInverso(string) + " ");
		}
		return stringBuilder.toString().trim();
	}

	public static String generarGiroInverso(String giro) {
		if (giro.endsWith("'")) {
			return giro.replace("'", "");
		} else if (giro.endsWith("2")) {
			return giro;
		} else if (giro.equals("/")) {
			return giro;
		} else if (giro.endsWith("++")) {
			return giro.replace("++", "--");
		} else if (giro.endsWith("--")) {
			return giro.replace("--", "++");
		} else if (!giro.contains(",")) {
			return giro.concat("'");
		} else if (giro.matches("^\\([\\-]{0,1}[0-6]{1},[\\-]{0,1}[0-6]{1}\\)$")) {
			return generarGiroInversoSquare1(giro);
		} else {
			return giro;
		}
	}

	public static String generarGiroInversoSquare1(String giro) {
		StringBuilder retorno = new StringBuilder("");
		String[] temp = giro.split(",");
		if (temp.length > 1) {
			String u = temp[0].replaceAll("\\(", "").replaceAll(" ", "");
			String d = temp[1].replaceAll("\\)", "").replaceAll(" ", "");
			try {
				int sup = Integer.parseInt(u);
				int inf = Integer.parseInt(d);
				retorno.append("(");
				if (sup != 0) {
					retorno.append(-sup);
				} else {
					retorno.append(sup);
				}
				retorno.append(",");
				if (inf != 0) {
					retorno.append(-inf);
				} else {
					retorno.append(inf);
				}
				retorno.append(")");
			} catch (Exception e) {
				ExceptionHandler.manejarExcepcionGrave(e);
			}
		} else {
			log.warn("Se encontró una inconsistencia en el giro: " + giro);
		}
		return retorno.toString();
	}

}
