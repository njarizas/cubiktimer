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
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.controlador.factories.ConnectionFactory;
import com.cubiktimer.modelo.dto.RolDTO;
import com.cubiktimer.util.Constantes;

public class RolDAO extends DAO<Integer, RolDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(RolDAO.class);

	public RolDAO() {
		super(Constantes.TABLA_ROLES, Constantes.PK_TABLA_ROLES);
	}

	@Override
	public int create(RolDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		int retorno = 0;
		String sql = "INSERT INTO roles VALUES (?,?,?,?)";
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, dto.getIdRol());
			ps.setObject(2, dto.getNombreRol());
			ps.setObject(3, dto.getDescripcion());
			ps.setObject(4, dto.getEstado());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			try {
				if (rs.next()) {
					retorno = rs.getInt(1);
				}
			} finally {
				rs.close();
			}
			log.trace("fin create");
			return retorno;
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin create");
		return 0;
	}

	public int update(RolDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		String sql = "UPDATE roles SET nombre_rol = ?, descripcion = ?, estado = ? WHERE id_rol = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getNombreRol());
			ps.setObject(2, dto.getDescripcion());
			ps.setObject(3, dto.getEstado());
			ps.setObject(4, dto.getIdRol());
			ps.executeUpdate();
			log.trace("fin update");
			return dto.getIdRol();
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin update");
		return 0;
	}

	public int merge(RolDTO dto) {
		if (dto == null) {
			return 0;
		}
		if (dto.getIdRol() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public int delete(RolDTO dto) {
		if (dto == null) {
			return 0;
		}
		return deleteByPK(dto.getIdRol());
	}

	public List<RolDTO> consultarRolesPorIdUsuario(int idUsuario) {
		log.trace("inicio consultarRolesPorIdUsuario");
		List<RolDTO> lista = new ArrayList<>();
		String sql = "SELECT R.* FROM usuarios_roles UR" + " LEFT JOIN roles R" + " ON UR.id_rol = R.id_rol"
				+ " WHERE id_usuario = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarRolesPorIdUsuario");
		return lista;
	}

	public List<RolDTO> setList(ResultSet rs) throws SQLException {
		List<RolDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		while (rs.next()) {
			RolDTO r = new RolDTO();
			r.setIdRol((Integer) rs.getObject("id_rol"));
			r.setNombreRol(rs.getString("nombre_rol"));
			r.setDescripcion(rs.getString("descripcion"));
			r.setEstado((Integer) rs.getObject("estado"));
			lista.add(r);
		}
		rs.close();
		return lista;
	}

}
