package com.cubiktimer.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cubiktimer.error.ExceptionHandler;
import com.cubiktimer.util.Propiedades;

/**
 * Clase con patron singleton para la conexion a base de datos
 * @author Nelson Ariza
 *
 */
public class ConexionDatabase {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final Logger log = Logger.getLogger(ConexionDatabase.class);
	private static Connection conn;

	private static String url;
	private static String database;
	private static String properties;
	private static String user;
	private static String password;

	static {
		Propiedades propiedades = Propiedades.getInstance();
		url = propiedades.obtenerPropiedad("conexion.url");
		database = propiedades.obtenerPropiedad("conexion.database");
		properties = propiedades.obtenerPropiedad("conexion.properties");
		user = propiedades.obtenerPropiedad("conexion.user");
		password = propiedades.obtenerPropiedad("conexion.password");
	}

	private ConexionDatabase() {

	}

	private static void connect() {
		try {
			Class.forName(DRIVER).newInstance();
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
