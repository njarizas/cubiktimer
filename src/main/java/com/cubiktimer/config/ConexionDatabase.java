package com.cubiktimer.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cubiktimer.error.ExceptionHandler;
import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.Propiedades;

/**
 * Clase con patron singleton para la conexion a base de datos
 * @author Nelson Ariza
 *
 */
public class ConexionDatabase {

	private static final Logger log = Logger.getLogger(ConexionDatabase.class);
	private static Connection conn;

	private static String url;
	private static String database;
	private static String properties;
	private static String user;
	private static String password;

	static {
		Propiedades propiedades = Propiedades.getInstance();
		url = propiedades.obtenerPropiedad(Constantes.LLAVE_CONEXION_URL);
		database = propiedades.obtenerPropiedad(Constantes.LLAVE_CONEXION_DATABASE);
		properties = propiedades.obtenerPropiedad(Constantes.LLAVE_CONEXION_PROPIEDADES);
		user = propiedades.obtenerPropiedad(Constantes.LLAVE_CONEXION_USUARIO);
		password = propiedades.obtenerPropiedad(Constantes.LLAVE_CONEXION_CLAVE);
	}

	private ConexionDatabase() {

	}

	private static void connect() {
		try {
			Class.forName(Constantes.DRIVER_MYSQL).newInstance();
			conn = DriverManager.getConnection(url + database + properties, user, password);
			log.trace("Se conectó a base de datos");
		} catch (Exception e) {
			log.info("Ocurrió un error y no fue posible conectarse a base de datos");
			ExceptionHandler.manejarExcepcionGrave(e);
		}
	}

	/**
	 * Metodo que retorna la conexion a base de datos
	 * @return <code>Connection</code> Conexion a la base de datos
	 */
	public static Connection getInstance() {
		if (conn == null) {
			connect();
		}
		return conn;
	}

	/**
	 * Metodo que cierra la conexion a la base de datos
	 */
	public static void close() {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
			log.trace("se desconectó de base de datos");
		} catch (SQLException e) {
			log.info("Ocurrió un error y no fue posible cerrar la conexion a base de datos");
			ExceptionHandler.manejarExcepcionModerada(e);
		}
	}

}
