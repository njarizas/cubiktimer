package com.cubiktimer.config;

import java.sql.Connection;
import java.sql.SQLException;

import com.cubiktimer.util.Propiedades;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class CubikTimerDataSource {

	private static final HikariConfig config;
	private static final HikariDataSource ds;

	private static String url;
	private static String database;
	private static String user;
	private static String password;

	static {
		Propiedades propiedades = Propiedades.getInstance();
		url = propiedades.obtenerPropiedad("conexion.url");
		database = propiedades.obtenerPropiedad("conexion.database");
		user = propiedades.obtenerPropiedad("conexion.user");
		password = propiedades.obtenerPropiedad("conexion.password");
		config = new HikariConfig();
		config.setJdbcUrl(url + database);
		config.setUsername(user);
		config.setPassword(password);
		config.setDriverClassName("com.mysql.jdbc.Driver");
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