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
import com.cubiktimer.modelo.dto.PermisoDTO;
import com.cubiktimer.util.Constantes;
import com.mysql.jdbc.Statement;

public class PermisosDAO extends DAO<Integer, PermisoDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(PermisosDAO.class);

	public PermisosDAO() {
		super(Constantes.TABLA_PERMISOS, Constantes.PK_TABLA_PERMISOS);
	}

	@Override
	public int create(PermisoDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		int retorno = 0;
		String sql = "INSERT INTO permisos VALUES (?,?,?,?,?,?,?,?)";
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, null);
			ps.setString(2, dto.getUrl());
			ps.setObject(3, dto.getIdPadre());
			ps.setString(4, dto.getNombrePermiso());
			ps.setString(5, dto.getDescripcionPermiso());
			ps.setString(6, dto.getNamePermiso());
			ps.setString(7, dto.getDescriptionPermiso());
			ps.setObject(8, dto.getEstado());
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

	public int update(PermisoDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		int retorno = 0;
		String sql = "UPDATE permisos SET url = ?, id_padre = ?, nombre_permiso = ?, descripcion_permiso = ?,"
				+ " name_permiso = ?, description_permiso = ?, estado = ? " + " WHERE id_permiso = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, dto.getUrl());
			ps.setObject(2, dto.getIdPadre());
			ps.setString(3, dto.getNombrePermiso());
			ps.setString(4, dto.getDescripcionPermiso());
			ps.setString(5, dto.getNamePermiso());
			ps.setString(6, dto.getDescriptionPermiso());
			ps.setObject(7, dto.getEstado());
			ps.setObject(8, dto.getIdPermiso());
			ps.executeUpdate();
			retorno = dto.getIdPermiso();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin update");
		}
		return retorno;
	}

	public int merge(PermisoDTO dto) {
		if (dto == null) {
			return 0;
		}
		if (dto.getIdPermiso() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public int delete(PermisoDTO dto) {
		if (dto == null) {
			return 0;
		}
		return deleteByPK(dto.getIdPermiso());
	}

	public List<PermisoDTO> consultarPermisosPorIdUsuario(int idUsuario) {
		log.trace("inicio consultarPermisosPorIdUsuario");
		List<PermisoDTO> lista = new ArrayList<>();
		String sql = "SELECT DISTINCT p.*" + " FROM usuarios_roles ur" + " INNER JOIN roles_permisos rp"
				+ " ON ur.id_rol = rp.id_rol" + " INNER JOIN permisos p" + " ON rp.id_permiso = p.id_permiso"
				+ " WHERE ur.estado=1 and rp.estado=1 and p.estado=1" + " AND id_usuario=?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarPermisosPorIdUsuario");
		return lista;
	}

	public List<PermisoDTO> listarPermisos(int idUsuario) {
		log.trace("inicio consultarPermisosPorIdUsuario");
		List<PermisoDTO> lista = new ArrayList<>();
		String sql = "SELECT DISTINCT p.*" + " FROM usuarios_roles ur" + " INNER JOIN roles_permisos rp"
				+ " ON ur.id_rol = rp.id_rol" + " INNER JOIN permisos p" + " ON rp.id_permiso = p.id_permiso"
				+ " WHERE ur.estado=1 AND rp.estado=1 AND p.estado=1" + " AND url IS NOT NULL" + " AND url !=''"
				+ " AND id_usuario=?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarPermisosPorIdUsuario");
		return lista;
	}

	public List<PermisoDTO> consultarPermisosPorIdPadre(int idPadre) {
		log.trace("inicio consultarPermisosPorIdPadre");
		List<PermisoDTO> lista = new ArrayList<>();
		String sql = "SELECT *" + " FROM permisos" + " WHERE id_padre=?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idPadre);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarPermisosPorIdPadre");
		return lista;
	}

	/**
	 * Metodo que retorna la cantidad de submenus que tiene un permiso
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Integer contarPermisosIdPadre(Integer idPermiso) {
		log.trace("inicio contarPermisosIdPadre");
		int retorno = 0;
		String sql = "SELECT count(*) conteo" + " FROM permisos" + " WHERE id_padre=?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idPermiso);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					retorno = rs.getInt("conteo");
				}
			} finally {
				rs.close();
			}
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		} finally {
			log.trace("fin contarPermisosIdPadre");
		}
		return retorno;
	}

	public List<PermisoDTO> setList(ResultSet rs) throws SQLException {
		List<PermisoDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		while (rs.next()) {
			PermisoDTO p = new PermisoDTO();
			p.setIdPermiso(rs.getInt("id_permiso"));
			p.setUrl(rs.getString("url"));
			p.setIdPadre(rs.getInt("id_padre"));
			p.setNombrePermiso(rs.getString("nombre_permiso"));
			p.setDescripcionPermiso(rs.getString("descripcion_permiso"));
			p.setNamePermiso(rs.getString("name_permiso"));
			p.setDescriptionPermiso(rs.getString("description_permiso"));
			p.setEstado(rs.getInt("estado"));
			lista.add(p);
		}
		rs.close();
		return lista;
	}
}
