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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cubiktimer.controlador.factories.ConnectionFactory;
import com.cubiktimer.modelo.dto.UsuarioRolDTO;
import com.cubiktimer.modelo.dto.UsuarioRolPK;
import com.cubiktimer.util.Constantes;

public class UsuarioRolDAO extends DAO<UsuarioRolPK, UsuarioRolDTO> implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(UsuarioRolDAO.class);
	private static final long serialVersionUID = 1L;

	public UsuarioRolDAO() {
		super(Constantes.TABLA_USUARIOS_ROLES);
	}

	@Override
	public int create(UsuarioRolDTO dto) {
		int retorno = 0;
		if (dto == null) {
			return retorno;
		}
		log.trace("inicio create");
		String sql = "INSERT INTO usuarios_roles VALUES (?,?,?)";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getUsuarioRolPK().getIdUsuario());
			ps.setObject(2, dto.getUsuarioRolPK().getIdRol());
			ps.setObject(3, dto.getEstado());
			retorno = ps.executeUpdate();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin create");
		}
		return retorno;
	}

	public int update(UsuarioRolDTO dto) {
		int retorno = 0;
		if (dto == null) {
			return retorno;
		}
		log.trace("inicio update");
		String sql = "UPDATE usuarios_roles SET id_usuario = ?, id_rol = ?, estado = ? WHERE id_usuario = ? AND id_rol = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getUsuarioRolPK().getIdUsuario());
			ps.setObject(2, dto.getUsuarioRolPK().getIdRol());
			ps.setObject(3, dto.getEstado());
			ps.setObject(4, dto.getUsuarioRolPK().getIdUsuario());
			ps.setObject(5, dto.getUsuarioRolPK().getIdRol());
			retorno = ps.executeUpdate();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin update");
		}
		return retorno;
	}

	public int merge(UsuarioRolDTO dto) {
		if (dto == null || dto.getUsuarioRolPK() == null) {
			return 0;
		}
		if (!existeUsuarioRol(dto.getUsuarioRolPK())) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public int delete(UsuarioRolDTO dto) {
		if (dto == null) {
			return 0;
		}
		return deleteByPK(dto.getUsuarioRolPK());
	}

	@Override
	public int deleteByPK(UsuarioRolPK pk) {
		if (pk == null) {
			return 0;
		}
		log.trace("inicio delete");
		int retorno = 0;
		String sql = "DELETE FROM " + getNombreTabla() + " WHERE id_usuario = ? AND id_rol = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, pk.getIdUsuario());
			ps.setObject(2, pk.getIdRol());
			retorno = ps.executeUpdate();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin delete");
		}
		return retorno;
	}

	public List<UsuarioRolDTO> consultarRolesPorIdUsuario(int idUsuario) {
		log.trace("inicio consultarRolesPorIdUsuario");
		List<UsuarioRolDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuarios_roles WHERE id_usuario = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarRolesPorIdUsuario");
		return lista;
	}

	@Override
	public List<UsuarioRolDTO> findById(UsuarioRolPK pk) {
		List<UsuarioRolDTO> lista = new ArrayList<>();
		if (pk == null) {
			return lista;
		}
		log.trace("inicio findById");
		String sql = "SELECT * FROM " + getNombreTabla() + " WHERE id_usuario = ? AND id_rol = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, pk.getIdUsuario());
			ps.setObject(2, pk.getIdRol());
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		} finally {
			log.trace("fin findById");
		}
		return lista;
	}

	public List<UsuarioRolDTO> setList(ResultSet rs) throws SQLException {
		List<UsuarioRolDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		while (rs.next()) {
			UsuarioRolDTO u = new UsuarioRolDTO();
			UsuarioRolPK pk = new UsuarioRolPK(rs.getInt("id_usuario"), rs.getInt("id_rol"));
			u.setUsuarioRolPK(pk);
			u.setEstado(rs.getInt("estado"));
			lista.add(u);
		}
		rs.close();
		return lista;
	}

	public boolean existeUsuarioRol(UsuarioRolPK pk) {
		List<UsuarioRolDTO> lista = findById(pk);
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
