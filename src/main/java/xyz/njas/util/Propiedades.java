package xyz.njas.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Propiedades {

	private static Propiedades instance;
	private static Properties properties = new Properties();
	private static String rutaPropiedades;
	private static final Logger log = Logger.getLogger(Propiedades.class);

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
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
	}

	/**
	 * Realiza el almacenamiento de la ruta donde se encuentra el archivo de
	 * propiedades del sistema
	 * 
	 * @param rutaPropiedades
	 */
	public static void configurarPropiedades(String rutaPropiedades) {
		Propiedades.rutaPropiedades = rutaPropiedades;
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
	public String obtenerPropiedad(String propiedad, Object... params) {

		String message = properties.getProperty(propiedad);
		if (params != null) {
			message = MessageFormat.format(message, params);
		}
		return message;
	}
}