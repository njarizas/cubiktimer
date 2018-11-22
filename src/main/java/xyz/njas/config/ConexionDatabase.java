package xyz.njas.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import xyz.njas.util.Propiedades;

public class ConexionDatabase {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final Logger log = Logger.getLogger(ConexionDatabase.class);
	private static Connection conn;

	private static String url;
	private static String database;
	private static String properties;
	private static String user;
	private static String password;

	static {
		Propiedades.configurarPropiedades("C:\\cubiktimer\\conexion.properties");
		Propiedades propiedades = Propiedades.getInstance();
		url = propiedades.obtenerPropiedad("url");
		database = propiedades.obtenerPropiedad("database");
		properties = propiedades.obtenerPropiedad("properties");
		user = propiedades.obtenerPropiedad("user");
		password = propiedades.obtenerPropiedad("password");
	}

	private ConexionDatabase() {

	}

	public static void connect() {
		try {
			Class.forName(DRIVER).newInstance();
			conn = DriverManager.getConnection(url + database + properties, user, password);
			log.trace("Se conectó a base de datos");
		} catch (Exception e) {
			log.debug("Ocurrió un error y no fue posible conectarse a base de datos");
			log.warn(e.getMessage());
		}
	}

	public static Connection getInstance() {
		if (conn == null) {
			connect();
		}
		return conn;
	}

	public static void close() {
		try {
			conn.close();
			conn = null;
			log.trace("se desconectó de base de datos");
		} catch (SQLException e) {
			log.debug("Ocurrió un error y no fue posible cerrar la conexion a base de datos");
			log.warn(e.getMessage());
		}
	}

}
