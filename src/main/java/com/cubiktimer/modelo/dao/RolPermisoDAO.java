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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cubiktimer.controlador.factories.ConnectionFactory;
import com.cubiktimer.modelo.dto.RolPermisoDTO;
import com.cubiktimer.modelo.dto.RolPermisoPK;
import com.cubiktimer.util.Constantes;

public class RolPermisoDAO extends DAO<RolPermisoPK, RolPermisoDTO> implements Serializable {

	private static final Logger log = LogManager.getLogger(RolPermisoDAO.class);
	private static final long serialVersionUID = 1L;

	public RolPermisoDAO() {
		super(Constantes.TABLA_ROLES_PERMISOS);
	}

	@Override
	public int create(RolPermisoDTO dto) {
		int retorno = 0;
		if (dto == null) {
			return retorno;
		}
		log.trace("inicio create");
		String sql = "INSERT INTO " + getNombreTabla() + " VALUES (?,?,?)";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getRolPermisoPK().getIdRol());
			ps.setObject(2, dto.getRolPermisoPK().getIdPermiso());
			ps.setObject(3, dto.getEstado());
			retorno = ps.executeUpdate();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin create");
		}
		return retorno;
	}

	public int update(RolPermisoDTO dto) {
		int retorno = 0;
		if (dto == null) {
			return retorno;
		}
		log.trace("inicio update");
		String sql = "UPDATE " + getNombreTabla()
				+ " SET id_rol = ?, id_permiso = ?, estado = ? WHERE id_rol = ? AND id_permiso = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getRolPermisoPK().getIdRol());
			ps.setObject(2, dto.getRolPermisoPK().getIdPermiso());
			ps.setObject(3, dto.getEstado());
			ps.setObject(4, dto.getRolPermisoPK().getIdRol());
			ps.setObject(5, dto.getRolPermisoPK().getIdPermiso());
			retorno = ps.executeUpdate();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin update");
		}
		return retorno;
	}

	public int merge(RolPermisoDTO dto) {
		if (dto == null || dto.getRolPermisoPK() == null) {
			return 0;
		}
		if (!existeRolPermiso(dto.getRolPermisoPK())) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public int delete(RolPermisoDTO dto) {
		if (dto == null) {
			return 0;
		}
		return deleteByPK(dto.getRolPermisoPK());
	}

	@Override
	public int deleteByPK(RolPermisoPK pk) {
		if (pk == null) {
			return 0;
		}
		log.trace("inicio delete");
		int retorno = 0;
		String sql = "DELETE FROM " + getNombreTabla() + " WHERE id_rol = ? AND id_permiso = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, pk.getIdRol());
			ps.setObject(2, pk.getIdPermiso());
			retorno = ps.executeUpdate();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin delete");
		}
		return retorno;
	}

	public List<RolPermisoDTO> consultarPermisosPorIdRol(int idRol) {
		log.trace("inicio consultarPermisosPorIdRol");
		List<RolPermisoDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM roles_permisos WHERE id_rol = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idRol);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarPermisosPorIdRol");
		return lista;
	}

	@Override
	public List<RolPermisoDTO> findById(RolPermisoPK pk) {
		List<RolPermisoDTO> lista = new ArrayList<>();
		if (pk == null) {
			return lista;
		}
		log.trace("inicio findById");
		String sql = "SELECT * FROM " + getNombreTabla() + " WHERE id_rol = ? AND id_permiso = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, pk.getIdRol());
			ps.setObject(2, pk.getIdPermiso());
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		} finally {
			log.trace("fin findById");
		}
		return lista;
	}

	public List<RolPermisoDTO> setList(ResultSet rs) throws SQLException {
		List<RolPermisoDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		while (rs.next()) {
			RolPermisoDTO u = new RolPermisoDTO();
			RolPermisoPK pk = new RolPermisoPK(rs.getInt("id_permiso"), rs.getInt("id_rol"));
			u.setRolPermisoPK(pk);
			u.setEstado(rs.getInt("estado"));
			lista.add(u);
		}
		rs.close();
		return lista;
	}

	public boolean existeRolPermiso(RolPermisoPK pk) {
		List<RolPermisoDTO> lista = findById(pk);
		return !lista.isEmpty();
	}

	@Override
	public boolean fixAutoincrement() {
		throw new UnsupportedOperationException("Esta operacion no aplica para esta tabla");
	}

	@Override
	public int consultarMaximoIdPK() {
		throw new UnsupportedOperationException("Esta operacion no aplica para esta tabla");
	}

}
