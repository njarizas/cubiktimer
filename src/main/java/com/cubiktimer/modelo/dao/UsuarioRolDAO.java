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
import com.cubiktimer.modelo.dto.UsuarioRolDTO;
import com.cubiktimer.util.Constantes;

public class UsuarioRolDAO extends DAO<Integer, UsuarioRolDTO> implements Serializable {

	private static final Logger log = Logger.getLogger(UsuarioRolDAO.class);
	private static final long serialVersionUID = 1L;

	public UsuarioRolDAO() {
		super(Constantes.TABLA_USUARIOS_ROLES, Constantes.PK_TABLA_USUARIOS_ROLES);
	}

	@Override
	public int create(UsuarioRolDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		String sql = "INSERT INTO usuarios_roles VALUES (?,?,?)";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdUsuario());
			ps.setObject(2, dto.getIdRol());
			ps.setObject(3, dto.getEstado());
			ps.executeUpdate();
			log.trace("fin create");
			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin create");
		return 0;
	}

	public int update(UsuarioRolDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		String sql = "UPDATE usuarios_roles SET estado = ? WHERE id_usuario = ? AND id_rol = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getEstado());
			ps.setObject(2, dto.getIdUsuario());
			ps.setObject(3, dto.getIdRol());
			ps.executeUpdate();
			log.trace("fin update");
			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin update");
		return 0;
	}

	public int merge(UsuarioRolDTO dto) {
		if (dto == null) {
			return 0;
		}
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
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarRolesPorIdUsuario");
		return lista;
	}

	public List<UsuarioRolDTO> setList(ResultSet rs) throws SQLException {
		List<UsuarioRolDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		while (rs.next()) {
			UsuarioRolDTO u = new UsuarioRolDTO();
			u.setIdUsuario(rs.getInt("id_usuario"));
			u.setIdRol(rs.getInt("id_rol"));
			u.setEstado(rs.getInt("estado"));
			lista.add(u);
		}
		rs.close();
		return lista;
	}

}
