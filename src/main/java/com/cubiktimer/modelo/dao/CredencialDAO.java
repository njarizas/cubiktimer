/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cubiktimer.controlador.factories.ConnectionFactory;
import com.cubiktimer.modelo.dto.CredencialDTO;
import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.Util;

public class CredencialDAO extends DAO<Integer, CredencialDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CredencialDAO.class);

	public CredencialDAO() {
		super(Constantes.TABLA_CREDENCIALES, Constantes.PK_TABLA_CREDENCIALES);
	}

	@Override
	public int create(CredencialDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		int retorno = 0;
		String sql = "INSERT INTO credenciales VALUES (?,?,?,?,?,?,?)";
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, dto.getIdCredencial());
			ps.setObject(2, dto.getIdUsuario());
			ps.setObject(3, dto.getCorreo());
			ps.setObject(4, dto.getClave());
			ps.setObject(5, dto.getFechaInicio());
			ps.setObject(6, dto.getFechaFin());
			ps.setObject(7, dto.getEstado());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			try {
				if (rs.next()) {
					retorno = rs.getInt(1);
				}
			} finally {
				rs.close();
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin create");
		}
		return retorno;
	}

	public int update(CredencialDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		int retorno = 0;
		String sql = "UPDATE credenciales SET id_credencial = ?, id_usuario = ?, correo = ?, clave = ?, fecha_inicio = ?, fecha_fin = ?, estado = ? WHERE id_credencial = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdCredencial());
			ps.setObject(2, dto.getIdUsuario());
			ps.setObject(3, dto.getCorreo());
			ps.setObject(4, dto.getClave());
			ps.setObject(5, dto.getFechaInicio());
			ps.setObject(6, dto.getFechaFin());
			ps.setObject(7, dto.getEstado());
			ps.setObject(8, dto.getIdCredencial());
			ps.executeUpdate();
			retorno = dto.getIdCredencial();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin update");
		}
		return retorno;
	}

	public int merge(CredencialDTO dto) {
		if (dto == null) {
			return 0;
		}
		if (dto.getIdCredencial() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public int delete(CredencialDTO dto) {
		if (dto == null) {
			return 0;
		}
		return deleteByPK(dto.getIdCredencial());
	}

	public List<CredencialDTO> consultarCredencialPorCorreo(String correo) {
		log.trace("inicio consultarCredencialPorCorreo");
		List<CredencialDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM credenciales WHERE correo = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, correo);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarCredencialPorCorreo");
		return lista;
	}

	public List<CredencialDTO> consultarCredencialPorCorreoYEstado(String correo, Integer estado) {
		log.trace("inicio consultarCredencialPorCorreoYEstado");
		List<CredencialDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM credenciales WHERE correo = ? AND estado = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, correo);
			ps.setInt(2, estado);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarCredencialPorCorreoYEstado");
		return lista;
	}

	public List<CredencialDTO> consultarCredencialPorCorreoClaveYEstado(String correo, String clave, Integer estado) {
		log.trace("inicio consultarCredencialPorCorreoClaveYEstado");
		List<CredencialDTO> lista = new ArrayList<>();

		String sql = "SELECT * FROM credenciales WHERE correo = ? AND clave = ? AND estado = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, correo);
			ps.setString(2, clave);
			ps.setInt(3, estado);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarCredencialPorCorreoClaveYEstado");
		return lista;
	}

	public Date obtenerFechaUltimaCredencial(Integer idUsuario) {
		log.trace("inicio obtenerFechaUltimaCredencial");
		Date retorno = null;
		String sql = "SELECT max(DATE_FORMAT(fecha_fin, \"%Y-%m-%d %H:%i:%s\")) fecha_ultimo_cambio FROM credenciales WHERE id_usuario=?";
		try (PreparedStatement ps = ConnectionFactory.getConnection().prepareCall(sql)) {
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			try {
				if (rs.next()) {
					Util util = Util.getInstance();
					retorno = util.getFechaHoraMysql().parse(rs.getString("fecha_ultimo_cambio"));
					log.trace("encontro: " + rs.getString("fecha_ultimo_cambio"));
				}
			} finally {
				rs.close();
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin obtenerFechaUltimaCredencial");
		return retorno;
	}

	public List<CredencialDTO> setList(ResultSet rs) throws SQLException {
		List<CredencialDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		Util util = Util.getInstance();
		while (rs.next()) {
			CredencialDTO c = new CredencialDTO();
			c.setIdCredencial(rs.getInt("id_credencial"));
			c.setIdUsuario(rs.getInt("id_usuario"));
			c.setCorreo(rs.getString("correo"));
			c.setClave(rs.getString("clave"));
			try {
				c.setFechaInicio(util.getFechaHoraMysql().parse(rs.getString("fecha_inicio")));
				c.setFechaFin(util.getFechaHoraMysql().parse(rs.getString("fecha_fin")));
			} catch (ParseException e) {
				log.warn(e.getMessage());
			}
			c.setEstado(rs.getInt("estado"));
			lista.add(c);
		}
		rs.close();
		return lista;
	}

}
