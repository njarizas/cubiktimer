package xyz.njas.modelo.dao;

import java.sql.Connection;

import xyz.njas.config.ConexionDatabase;

public abstract class DAO<P, E> {

	protected Connection conn;

	public void conectar() {
		conn = ConexionDatabase.getInstance();
	}

	public void desconectar() {
		conn = null;
		ConexionDatabase.close();
	}

	public abstract int create(E dto);

}
