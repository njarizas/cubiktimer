package xyz.njas.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import xyz.njas.modelo.dto.UsuarioRolDTO;

public class UsuarioRolDAO extends DAO<Integer, UsuarioRolDTO> {
	
	private static final Logger log = Logger.getLogger(UsuarioRolDAO.class);

	@Override
	public int create(UsuarioRolDTO dto) {
		conectar();
		String sql = "INSERT INTO usuarios_roles VALUES (?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, dto.getIdUsuario());
			ps.setObject(2, dto.getIdRol());
			ps.setObject(3, dto.getEstado());
			ps.executeUpdate();
			desconectar();
			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
		return 0;
	}

	public int update(UsuarioRolDTO dto) {
		conectar();
		String sql = "UPDATE usuarios_roles SET estado = ? WHERE id_usuario = ? AND id_rol = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, dto.getEstado());
			ps.setObject(2, dto.getIdUsuario());
			ps.setObject(3, dto.getIdRol());
			ps.executeUpdate();
			desconectar();
			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
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
		List<UsuarioRolDTO> lista = new ArrayList<UsuarioRolDTO>();
		try {
			String sql = "SELECT * FROM usuarios_roles WHERE id_usuario = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		return lista;
	}

	public List<UsuarioRolDTO> findList(PreparedStatement ps) {
		List<UsuarioRolDTO> lista = new ArrayList<UsuarioRolDTO>();
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
		return lista;
	}

}
