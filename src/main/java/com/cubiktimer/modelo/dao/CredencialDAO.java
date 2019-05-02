package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.config.CubikTimerDataSource;
import com.cubiktimer.modelo.dto.CredencialDTO;
import com.cubiktimer.util.Util;
import com.mysql.jdbc.Statement;

public class CredencialDAO extends DAO<Integer, CredencialDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(CredencialDAO.class);

	@Override
	public int create(CredencialDTO dto) {
		log.trace("inicio create");
		int retorno = 0;
		conectar();
		String sql = "INSERT INTO credenciales VALUES (?,?,?,?,?,?,?)";
		try (PreparedStatement ps = CubikTimerDataSource.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
			desconectar();
			log.trace("fin create");
			return retorno;
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
		log.trace("fin create");
		return 0;
	}

	public List<CredencialDTO> consultarCredencialPorCorreo(String correo) {
		log.trace("inicio consultarCredencialPorCorreo");
		List<CredencialDTO> lista = new ArrayList<>();
		conectar();
		String sql = "SELECT * FROM credenciales WHERE correo = ?";
		try (PreparedStatement ps = CubikTimerDataSource.getConnection().prepareStatement(sql)) {
			ps.setString(1, correo);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		} finally {
			desconectar();
		}
		log.trace("fin consultarCredencialPorCorreo");
		return lista;
	}

	public List<CredencialDTO> consultarCredencialPorCorreoYEstado(String correo, Integer estado) {
		log.trace("inicio consultarCredencialPorCorreoYEstado");
		List<CredencialDTO> lista = new ArrayList<>();
		conectar();
		String sql = "SELECT * FROM credenciales WHERE correo = ? AND estado = ?";
		try (PreparedStatement ps = CubikTimerDataSource.getConnection().prepareStatement(sql)) {
			ps.setString(1, correo);
			ps.setInt(2, estado);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		} finally {
			desconectar();
		}
		log.trace("fin consultarCredencialPorCorreoYEstado");
		return lista;
	}

	public List<CredencialDTO> consultarCredencialPorCorreoClaveYEstado(String correo, String clave, Integer estado) {
		log.trace("inicio consultarCredencialPorCorreoClaveYEstado");
		List<CredencialDTO> lista = new ArrayList<>();
		conectar();
		String sql = "SELECT * FROM credenciales WHERE correo = ? AND clave = ? AND estado = ?";
		try (PreparedStatement ps = CubikTimerDataSource.getConnection().prepareStatement(sql)) {
			ps.setString(1, correo);
			ps.setString(2, clave);
			ps.setInt(3, estado);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		} finally {
			desconectar();
		}
		log.trace("fin consultarCredencialPorCorreoClaveYEstado");
		return lista;
	}

	public Date obtenerFechaUltimaCredencial(Integer idUsuario) {
		log.trace("inicio obtenerFechaUltimaCredencial");
		Date retorno = null;
		conectar();
		String sql = "SELECT max(DATE_FORMAT(fecha_fin, \"%Y-%m-%d %H:%i:%s\")) fecha_ultimo_cambio FROM credenciales WHERE id_usuario=?";
		try (PreparedStatement ps = CubikTimerDataSource.getConnection().prepareCall(sql)) {
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
			desconectar();
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
		log.trace("fin obtenerFechaUltimaCredencial");
		return retorno;
	}

	public List<CredencialDTO> findList(PreparedStatement ps) {
		log.trace("inicio findList");
		List<CredencialDTO> lista = new ArrayList<>();
		try {
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Util util = Util.getInstance();
					CredencialDTO c = new CredencialDTO();
					c.setIdCredencial(rs.getInt("id_credencial"));
					c.setIdUsuario(rs.getInt("id_usuario"));
					c.setCorreo(rs.getString("correo"));
					c.setClave(rs.getString("clave"));
					c.setFechaInicio(util.getFechaHoraMysql().parse(rs.getString("fecha_inicio")));
					c.setFechaFin(util.getFechaHoraMysql().parse(rs.getString("fecha_fin")));
					c.setEstado(rs.getInt("estado"));
					lista.add(c);
				}
			} finally {
				rs.close();
			}
			desconectar();
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
			desconectar();
		} catch (ParseException pe) {
			log.warn(pe.getMessage());
			desconectar();
		}
		log.trace("fin findList");
		return lista;
	}

}
