package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.config.CubikTimerDataSource;
import com.cubiktimer.modelo.dto.UsuarioDTO;
import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.Util;
import com.mysql.jdbc.Statement;

public class UsuarioDAO extends DAO<Integer, UsuarioDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UsuarioDAO.class);

	public UsuarioDAO() {
		super(Constantes.TABLA_USUARIOS, Constantes.PK_TABLA_USUARIOS);
	}

	@Override
	public int create(UsuarioDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		int retorno = 0;
		Util util = Util.getInstance();
		String sql = "INSERT INTO usuarios VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = CubikTimerDataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, null);
			ps.setString(2, dto.getCorreo());
			ps.setString(3, dto.getSal());
			ps.setString(4, dto.getClave());
			ps.setString(5, dto.getNombres());
			ps.setString(6, dto.getApellidos());
			ps.setString(7, String.valueOf(dto.getSexo()));
			ps.setString(8, util.getFechaHoraMysql().format(dto.getFechaNacimiento()));
			ps.setString(9, util.getFechaHoraMysql().format(dto.getFechaCreacion()));
			ps.setString(10, util.getFechaHoraMysql().format(dto.getFechaModificacion()));
			ps.setObject(11, dto.getEstado());
			retorno = ps.executeUpdate();
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

	public int update(UsuarioDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		int retorno = 0;
		Util util = Util.getInstance();
		String sql = "UPDATE usuarios SET correo = ?, sal=?, clave = ?, nombres = ?, apellidos = ?, sexo = ?,"
				+ " fecha_nacimiento = ?, fecha_creacion = ?, fecha_modificacion = ?, estado = ? WHERE id_usuario = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, dto.getCorreo());
			ps.setString(2, dto.getSal());
			ps.setString(3, dto.getClave());
			ps.setString(4, dto.getNombres());
			ps.setString(5, dto.getApellidos());
			ps.setString(6, String.valueOf(dto.getSexo()));
			ps.setString(7, util.getFechaHoraMysql().format(dto.getFechaNacimiento()));
			ps.setString(8, util.getFechaHoraMysql().format(dto.getFechaCreacion()));
			ps.setString(9, util.getFechaHoraMysql().format(dto.getFechaModificacion()));
			ps.setObject(10, dto.getEstado());
			ps.setObject(11, dto.getIdUsuario());
			ps.executeUpdate();
			retorno = dto.getIdUsuario();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin update");
		}
		return retorno;
	}

	public int merge(UsuarioDTO dto) {
		//TODO arreglar este merge haciendo un metodo existeUsuario();
		if (dto == null) {
			return 0;
		}
		if (dto.getIdUsuario() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public int delete(UsuarioDTO dto) {
		if (dto == null) {
			return 0;
		}
		return deleteByPK(dto.getIdUsuario());
	}

	public List<UsuarioDTO> consultarUsuarioPorCorreo(String correo) {
		log.trace("inicio consultarUsuarioPorCorreo");
		List<UsuarioDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuarios WHERE correo = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, correo);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarUsuarioPorCorreo");
		return lista;
	}

	public List<UsuarioDTO> consultarUsuarioPorIdUsuario(int idUsuario) {
		log.trace("inicio consultarUsuarioPorIdUsuario");
		List<UsuarioDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarUsuarioPorIdUsuario");
		return lista;
	}

	public List<UsuarioDTO> consultarUsuarioPorIdUsuarioYClave(int idUsuario, String clave) {
		log.trace("inicio consultarUsuarioPorIdUsuarioYClave");
		List<UsuarioDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuarios WHERE id_usuario = ? AND clave = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ps.setString(2, clave);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarUsuarioPorIdUsuarioYClave");
		return lista;
	}

	public List<UsuarioDTO> consultarUsuarioPorCorreoYEstado(String correo, Integer estado) {
		List<UsuarioDTO> lista = new ArrayList<>();
		if (correo == null || estado == null) {
			return lista;
		}
		log.trace("inicio consultarUsuarioPorCorreoYEstado");
		String sql = "SELECT * FROM usuarios WHERE correo = ? AND estado = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, correo);
			ps.setInt(2, estado);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarUsuarioPorCorreoYEstado");
		return lista;
	}

	public List<UsuarioDTO> consultarUsuarios() {
		log.trace("inicio consultarUsuarios");
		List<UsuarioDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuarios WHERE estado = 1";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarUsuarios");
		return lista;
	}

	public List<UsuarioDTO> consultarAmigosPorIdUsuario(Integer idUsuario) {
		log.trace("inicio consultarAmigosPorIdUsuario");
		List<UsuarioDTO> lista = new ArrayList<>();
		String sql = "SELECT u.* FROM amigos a INNER JOIN usuarios u ON a.id_amigo=u.id_usuario"
				+ " WHERE a.id_usuario = ? AND u.estado = 1 AND a.estado = 1";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			lista = findList(ps);
		} catch (SQLException e) {
			log.warn(e.getMessage());
		}
		log.trace("fin consultarAmigosPorIdUsuario");
		return lista;
	}

	/**
	 * Método que consulta los usuarios que aun no son amigos, y no se les ha
	 * enviado solicitud de amistad
	 * 
	 * @param idUsuario
	 * @return
	 */
	public List<UsuarioDTO> consultarUsuariosNoAmigos(Integer idUsuario) {
		log.trace("inicio consultarUsuariosNoAmigos");
		List<UsuarioDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuarios"
				+ " WHERE id_usuario NOT IN (SELECT id_amigo FROM amigos WHERE id_usuario=? UNION SELECT id_usuario FROM amigos WHERE id_amigo=?)"
				+ " AND id_usuario!=?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ps.setInt(2, idUsuario);
			ps.setInt(3, idUsuario);
			lista = findList(ps);
		} catch (SQLException e) {
			log.warn(e.getMessage());
		}
		log.trace("fin consultarUsuariosNoAmigos");
		return lista;
	}

	/**
	 * Método que consulta los usuarios que aun no son amigos, y no se les ha
	 * enviado solicitud de amistad
	 * 
	 * @param idUsuario
	 * @return
	 */
	public List<UsuarioDTO> consultarSolicitudesDeAmistad(Integer idUsuario) {
		log.trace("inicio consultarSolicitudesDeAmistad");
		List<UsuarioDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuarios"
				+ " WHERE id_usuario IN (SELECT id_usuario FROM amigos WHERE id_amigo=? AND estado=2)"
				+ " AND id_usuario!=?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ps.setInt(2, idUsuario);
			lista = findList(ps);
		} catch (SQLException e) {
			log.warn(e.getMessage());
		}
		log.trace("fin consultarSolicitudesDeAmistad");
		return lista;
	}

	public List<UsuarioDTO> consultarUsuariosAmigos(Integer idUsuario) {
		log.trace("inicio consultarUsuariosAmigos");
		List<UsuarioDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuarios"
				+ " WHERE id_usuario IN (SELECT id_usuario FROM amigos WHERE id_amigo=? AND estado=1)"
				+ " AND id_usuario!=?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ps.setInt(2, idUsuario);
			lista = findList(ps);
		} catch (SQLException e) {
			log.warn(e.getMessage());
		}
		log.trace("fin consultarUsuariosAmigos");
		return lista;
	}

	public String consultarSalPorUsuario(String correo) {
		String sal;
		log.trace("inicio consultarSalPorUsuario: " + correo);
		log.debug(correo);
		List<UsuarioDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuarios WHERE correo=?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, correo);
			lista = findList(ps);
		} catch (SQLException e) {
			log.warn(e.getMessage());
		}
		log.debug("registros encontrados: " + lista.size());
		sal = lista.isEmpty() ? "" : lista.get(0).getSal();
		log.debug(sal);
		log.trace("fin consultarSalPorUsuario");
		return sal;
	}

	public List<UsuarioDTO> setList(ResultSet rs) throws SQLException {
		List<UsuarioDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		Util util = Util.getInstance();
		while (rs.next()) {
			UsuarioDTO u = new UsuarioDTO();
			u.setIdUsuario(rs.getInt("id_usuario"));
			u.setCorreo(rs.getString("correo"));
			u.setSal(rs.getString("sal"));
			u.setClave(rs.getString("clave"));
			u.setNombres(rs.getString("nombres"));
			u.setApellidos(rs.getString("apellidos"));
			u.setSexo(rs.getString("sexo").charAt(0));
			try {
				u.setFechaNacimiento(util.getFechaMysql().parse(rs.getString("fecha_nacimiento")));
				u.setFechaNacimiento(util.getFechaHoraMysql().parse(rs.getString("fecha_creacion")));
				u.setFechaNacimiento(util.getFechaHoraMysql().parse(rs.getString("fecha_modificacion")));
			} catch (ParseException e) {
				log.warn(e.getMessage());
			}
			u.setEstado(rs.getInt("estado"));
			lista.add(u);
		}
		rs.close();
		return lista;
	}

}
