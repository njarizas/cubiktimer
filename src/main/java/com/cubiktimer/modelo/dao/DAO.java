package com.cubiktimer.modelo.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cubiktimer.config.CubikTimerDataSource;

public abstract class DAO<P, E> {
	
	private static final Logger log = Logger.getLogger(DAO.class);

	protected Connection conn;

	public void conectar() {
//		conn = ConexionDatabase.getInstance();
		try (Connection c = CubikTimerDataSource.getConnection()){
			this.conn = c;
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	public void desconectar() {
//		conn = null;
//		ConexionDatabase.close();
	}

	public abstract int create(E dto);

}
