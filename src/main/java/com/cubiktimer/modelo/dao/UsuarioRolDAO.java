package com.cubiktimer.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.config.CubikTimerDataSource;
import com.cubiktimer.modelo.dto.UsuarioRolDTO;

public class UsuarioRolDAO extends DAO<Integer, UsuarioRolDTO> {

	private static final Logger log = Logger.getLogger(UsuarioRolDAO.class);

	@Override
	public int create(UsuarioRolDTO dto) {
		log.trace("inicio create");
		conectar();
		String sql = "INSERT INTO usuarios_roles VALUES (?,?,?)";
		try (PreparedStatement ps = CubikTimerDataSource.getConnection().prepareStatement(sql)) {
			ps.setObject(1, dto.getIdUsuario());
			ps.setObject(2, dto.getIdRol());
			ps.setObject(3, dto.getEstado());
			ps.executeUpdate();
			desconectar();
			log.trace("fin create");
			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
		log.trace("fin create");
		return 0;
	}

	public int update(UsuarioRolDTO dto) {
		log.trace("inicio update");
		conectar();
		String sql = "UPDATE usuarios_roles SET estado = ? WHERE id_usuario = ? AND id_rol = ?";
		try (PreparedStatement ps = CubikTimerDataSource.getConnection().prepareStatement(sql)) {
			ps.setObject(1, dto.getEstado());
			ps.setObject(2, dto.getIdUsuario());
			ps.setObject(3, dto.getIdRol());
			ps.executeUpdate();
			desconectar();
			log.trace("fin update");
			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
		log.trace("fin update");
		return 0;
	}

	public int merge(UsuarioRolDTO dto) {
		if (dto.getIdUsuario() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public List<UsuarioRolDTO> consultarRolesPorIdUsuario(int idUsuario) {
		log.trace("inicio consultarRolesPorIdUsuario");
		List<UsuarioRolDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuarios_roles WHERE id_usuario = ?";
		try (PreparedStatement ps = CubikTimerDataSource.getConnection().prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarRolesPorIdUsuario");
		return lista;
	}

	public List<UsuarioRolDTO> findList(PreparedStatement ps) {
		log.trace("inicio findList");
		List<UsuarioRolDTO> lista = new ArrayList<>();
		try {
			conectar();
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					UsuarioRolDTO u = new UsuarioRolDTO();
					u.setIdUsuario(rs.getInt("id_usuario"));
					u.setIdRol(rs.getInt("id_rol"));
					u.setEstado(rs.getInt("estado"));
					lista.add(u);
				}
			} finally {
				rs.close();
			}
			desconectar();
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
			desconectar();
		}
		log.trace("inicio findList");
		return lista;
	}

}
