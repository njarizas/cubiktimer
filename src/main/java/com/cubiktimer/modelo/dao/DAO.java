/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.controlador.factories.ConnectionFactory;

public abstract class DAO<P, E> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(DAO.class);

	private String nombreTabla;
	private String nombreLlavePrimaria;

	public DAO() {
	}

	public DAO(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public DAO(String nombreTabla, String nombreLlavePrimaria) {
		this.nombreTabla = nombreTabla;
		this.nombreLlavePrimaria = nombreLlavePrimaria;
	}

	public abstract int create(E dto);

	public abstract int update(E dto);

	public abstract int merge(E dto);

	public abstract int delete(E dto);

	public abstract List<E> setList(ResultSet rs) throws SQLException;

	public List<E> findAll() {
		log.trace("inicio findAll");
		List<E> lista = new ArrayList<>();
		String sql = "SELECT * FROM " + nombreTabla;
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		} finally {
			log.trace("fin findAll");
		}
		return lista;
	}

	public List<E> findById(P pk) {
		List<E> lista = new ArrayList<>();
		if (pk == null) {
			return lista;
		}
		log.trace("inicio findById");
		String sql = "SELECT * FROM " + nombreTabla + " WHERE " + nombreLlavePrimaria + "=?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, pk);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		} finally {
			log.trace("fin findById");
		}
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

	public int deleteByPK(P pk) {
		if (pk == null) {
			return 0;
		}
		log.trace("inicio delete");
		int retorno = 0;
		String sql = "DELETE FROM " + nombreTabla + " WHERE " + nombreLlavePrimaria + " = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, pk);
			int aux = ps.executeUpdate();
			retorno = (pk instanceof Integer) ? (Integer) pk : aux;
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin delete");
		}
		return retorno;
	}

	public boolean fixAutoincrement() {
		log.trace("inicio fixAutoincrement");
		boolean retorno = false;
		String sql2 = "ALTER TABLE " + getNombreTabla() + " AUTO_INCREMENT=?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql2)) {
			int autoincrement = consultarMaximoIdPK() + 1;
			ps.setObject(1, autoincrement);
			ps.execute();
			retorno = true;
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin fixAutoincrement");
		}
		return retorno;
	}

	public int consultarMaximoIdPK() {
		log.trace("inicio consultarMaximoIdPK");
		int retorno = 0;
		String sql = "SELECT MAX(" + getNombreLlavePrimaria() + ") maximo FROM " + getNombreTabla();
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				retorno = rs.getInt("maximo");
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin consultarMaximoIdPK");
		}
		return retorno;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public String getNombreLlavePrimaria() {
		return nombreLlavePrimaria;
	}

	public void setNombreLlavePrimaria(String nombreLlavePrimaria) {
		this.nombreLlavePrimaria = nombreLlavePrimaria;
	}

}
