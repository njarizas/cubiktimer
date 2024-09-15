/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cubiktimer.error.ExceptionHandler;

public class Propiedades {

	private static Propiedades instance;
	private static Properties properties = new Properties();
	private static String rutaPropiedades = Constantes.PATH_CUBIKTIMER + Constantes.CUBIKTIMER_PROPERTIES_FILENAME;
	private static final Logger log = LoggerFactory.getLogger(Propiedades.class);

	/**
	 * Constuctor de la clase. Carga las propiedades del Sistema
	 */
	private Propiedades() {
		cargarPropiedadesPorArchivo();
	}

	/**
	 * Carga las propiedades por medio del archivo de configuracion
	 */
	private void cargarPropiedadesPorArchivo() {
		InputStream entrada = null;
		try {
			entrada = new FileInputStream(Propiedades.rutaPropiedades);
			properties.load(entrada);
			log.info("cargó propiedades del archivo");
		} catch (Exception e) {
			ExceptionHandler.manejarExcepcionGrave(e);
		}
	}

	public static void main(String[] args) {
		Propiedades propiedades = Propiedades.getInstance();
		System.out.println(propiedades.obtenerPropiedad("conexion.user"));
	}

	/**
	 * Retorna la instancia del objeto Propiedades
	 * 
	 * @return Un objeto propiedades con las propiedades del jar
	 */
	public static Propiedades getInstance() {
		synchronized (Propiedades.class) {
			if (instance == null) {
				instance = new Propiedades();
			}
		}
		return instance;
	}

	/**
	 * Obtiene la propiedad ingresada como parametro, y formatea las variables con
	 * los parametros ingresados
	 * 
	 * @param propiedad Propiedad de la cual se desea obtener el valor
	 * @param params    Valor de los parametros para formatear la propiedad
	 * @return El valor de la propiedad solicitada
	 */
	public String obtenerPropiedad(String propiedad) {
		return properties.getProperty(propiedad);
	}
}