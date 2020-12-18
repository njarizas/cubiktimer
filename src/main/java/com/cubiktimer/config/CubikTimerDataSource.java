/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.config;

import java.sql.Connection;
import java.sql.SQLException;

import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.Propiedades;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class CubikTimerDataSource {

	private static final HikariConfig config;
	private static final HikariDataSource ds;

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
		config = new HikariConfig();
		config.setJdbcUrl(url + database + properties);
		config.setUsername(user);
		config.setPassword(password);
		config.setDriverClassName(Constantes.DRIVER_MYSQL);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		ds = new HikariDataSource(config);
	}

	private CubikTimerDataSource() {

	}

	public static final Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}