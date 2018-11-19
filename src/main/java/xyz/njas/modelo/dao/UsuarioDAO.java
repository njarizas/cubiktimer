package xyz.njas.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import xyz.njas.modelo.dto.UsuarioDTO;
import xyz.njas.util.Util;

public class UsuarioDAO extends DAO<Integer, UsuarioDTO> {

	@Override
	public int create(UsuarioDTO dto) {
		int retorno = 0;
		conectar();
		String sql = "INSERT INTO usuarios VALUES (?,?,?,?,?,?,?,?,?,?)";
		String sql2 = "INSERT INTO usuarios_roles VALUES (?,?,?)";
		try {
			Util util = Util.getInstance();
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps.setObject(1, null);
			ps.setString(2, dto.getCorreo());
			ps.setString(3, dto.getClave());
			ps.setString(4, dto.getNombres());
			ps.setString(5, dto.getApellidos());
			ps.setString(6, String.valueOf(dto.getSexo()));
			ps.setString(7, util.fechaHoraMysql.format(dto.getFechaNacimiento()));
			ps.setString(8, util.fechaHoraMysql.format(dto.getFechaCreacion()));
			ps.setString(9, util.fechaHoraMysql.format(dto.getFechaModificacion()));
			ps.setObject(10, dto.getEstado());
			retorno = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			try {
				if (rs != null && rs.next()) {
					retorno = rs.getInt(1);
				}
			} finally {
				rs.close();
			}
			List<Integer> listaRoles = new ArrayList<Integer>();
			listaRoles.add(1);
			listaRoles.add(2);
			ps2.setInt(1, retorno);
			ps2.setInt(3, 1);
			for (Integer integer : listaRoles) {
				ps2.setObject(2, integer);
				ps2.executeUpdate();
			}
			desconectar();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			desconectar();
		}
		return 0;
	}

	public int update(UsuarioDTO dto) {
		int retorno = 0;
		conectar();
		String sql = "UPDATE usuarios SET correo = ?, clave = ?, nombres = ?, apellidos = ?, sexo = ?,"
				+ " fecha_nacimiento = ?, fecha_creacion = ?, fecha_modificacion = ?, estado = ? WHERE id_usuario = ?";
		try {
			Util util = Util.getInstance();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getCorreo());
			ps.setString(2, dto.getClave());
			ps.setString(3, dto.getNombres());
			ps.setString(4, dto.getApellidos());
			ps.setString(5, String.valueOf(dto.getSexo()));
			ps.setString(6, util.fechaHoraMysql.format(dto.getFechaNacimiento()));
			ps.setString(7, util.fechaHoraMysql.format(dto.getFechaCreacion()));
			ps.setString(8, util.fechaHoraMysql.format(dto.getFechaModificacion()));
			ps.setObject(9, dto.getEstado());
			ps.setObject(10, dto.getIdUsuario());
			ps.executeUpdate();
			retorno = dto.getIdUsuario();
			desconectar();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			desconectar();
		}
		return 0;
	}

	public int merge(UsuarioDTO dto) {
		if (dto.getIdUsuario() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public List<UsuarioDTO> consultarUsuarioPorCorreo(String correo) {
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		try {
			conectar();
			String sql = "SELECT * FROM usuarios WHERE correo = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, correo);
			lista = findList(ps);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			desconectar();
		}
		return lista;
	}

	public List<UsuarioDTO> traerTodoPorCorreo(String correo) {
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		try {
			conectar();
			String sql = "SELECT * FROM usuarios WHERE correo = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, correo);
			lista = findList(ps);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			desconectar();
		}
		return lista;
	}

	public List<UsuarioDTO> consultarUsuarioPorIdUsuario(int idUsuario) {
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		try {
			conectar();
			String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			lista = findList(ps);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			desconectar();
		}
		return lista;
	}

	public List<UsuarioDTO> consultarUsuarioPorIdUsuarioYClave(int idUsuario, String clave) {
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		try {
			conectar();
			String sql = "SELECT * FROM usuarios WHERE id_usuario = ? AND clave = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			ps.setString(2, clave);
			lista = findList(ps);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			desconectar();
		}
		return lista;
	}

	public List<UsuarioDTO> consultarUsuarioPorCorreoYEstado(String correo, Integer estado) {
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		try {
			conectar();
			String sql = "SELECT * FROM usuarios WHERE correo = ? AND estado = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, correo);
			ps.setInt(2, estado);
			lista = findList(ps);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			desconectar();
		}
		return lista;
	}

	public List<UsuarioDTO> consultarUsuarios() {
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		try {
			conectar();
			String sql = "SELECT * FROM usuarios WHERE estado = 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			lista = findList(ps);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			desconectar();
		}
		return lista;
	}

	public List<UsuarioDTO> consultarAmigosPorIdUsuario(Integer idUsuario) {
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		try {
			conectar();
			String sql = "SELECT u.* FROM amigos a INNER JOIN usuarios u ON a.id_amigo=u.id_usuario"
					+ " WHERE a.id_usuario = ? AND u.estado = 1 AND a.estado = 1";
			PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			lista = findList(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconectar();
		}
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
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		try {
			conectar();
			String sql = "SELECT * FROM usuarios"
					+ " WHERE id_usuario NOT IN (SELECT id_amigo FROM amigos WHERE id_usuario=? UNION SELECT id_usuario FROM amigos WHERE id_amigo=?)"
					+ " AND id_usuario!=?";
			PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			ps.setInt(2, idUsuario);
			ps.setInt(3, idUsuario);
			lista = findList(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconectar();
		}
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
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		try {
			conectar();
			String sql = "SELECT * FROM usuarios"
					+ " WHERE id_usuario IN (SELECT id_usuario FROM amigos WHERE id_amigo=? AND estado=2)"
					+ " AND id_usuario!=?";
			PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			ps.setInt(2, idUsuario);
			lista = findList(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconectar();
		}
		return lista;
	}

	public List<UsuarioDTO> consultarUsuariosAmigos(Integer idUsuario) {
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		try {
			conectar();
			String sql = "SELECT * FROM usuarios"
					+ " WHERE id_usuario IN (SELECT id_usuario FROM amigos WHERE id_amigo=? AND estado=1)"
					+ " AND id_usuario!=?";
			PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			ps.setInt(2, idUsuario);
			lista = findList(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconectar();
		}
		return lista;
	}

	public List<UsuarioDTO> findList(PreparedStatement ps) {
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		try {
			ResultSet rs = ps.executeQuery();
			try {
				Util util = Util.getInstance();
				while (rs.next()) {
					UsuarioDTO u = new UsuarioDTO();
					u.setIdUsuario(rs.getInt("id_usuario"));
					u.setCorreo(rs.getString("correo"));
					u.setClave(rs.getString("clave"));
					u.setNombres(rs.getString("nombres"));
					u.setApellidos(rs.getString("apellidos"));
					u.setSexo(rs.getString("sexo").charAt(0));
					u.setFechaNacimiento(util.fechaMysql.parse(rs.getString("fecha_nacimiento")));
					u.setFechaNacimiento(util.fechaHoraMysql.parse(rs.getString("fecha_creacion")));
					u.setFechaNacimiento(util.fechaHoraMysql.parse(rs.getString("fecha_modificacion")));
					u.setEstado(rs.getInt("estado"));
					lista.add(u);
				}
			} finally {
				rs.close();
			}
			desconectar();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			desconectar();
		} catch (ParseException pe) {
			pe.printStackTrace();
			desconectar();
		}
		return lista;
	}

}
