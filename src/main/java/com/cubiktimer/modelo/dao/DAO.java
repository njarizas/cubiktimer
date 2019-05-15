package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.config.CubikTimerDataSource;

public abstract class DAO<P, E> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(DAO.class);

	private String nombreTabla;

//	public DAO() {
//	}

	public DAO(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public abstract int create(E dto);

	public List<E> findAll() {
		log.trace("inicio findAll");
		List<E> lista = new ArrayList<>();
		String sql = "SELECT * FROM " + this.nombreTabla;
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin findAll");
		return lista;
	}
	
	public List<E> findList(PreparedStatement ps) {
		log.trace("inicio findList");
		List<E> lista = new ArrayList<>();
		try {
			ResultSet rs = ps.executeQuery();
			lista = setList(rs);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin findList");
		return lista;
	}
	
	protected abstract List<E> setList(ResultSet rs) throws SQLException;

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

}
