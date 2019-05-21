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
import com.cubiktimer.modelo.dto.ConfiguracionDTO;
import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.Util;
import com.mysql.jdbc.Statement;

public class ConfiguracionDAO extends DAO<Integer, ConfiguracionDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ConfiguracionDAO.class);

	public ConfiguracionDAO() {
		super(Constantes.TABLA_CONFIGURACION, Constantes.PK_TABLA_CONFIGURACION);
	}

	@Override
	public int create(ConfiguracionDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		int retorno = 0;
		Util util = Util.getInstance();
		String sql = "INSERT INTO configuracion VALUES (?,?,?,?,?,?,?,?)";
		try (Connection con = CubikTimerDataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, null);
			ps.setObject(2, dto.getIdUsuario());
			ps.setObject(3, dto.getIdTipo());
			ps.setString(4, dto.getValorTexto());
			ps.setObject(5, dto.getValorEntero());
			ps.setObject(6, dto.getValorDecimal());
			if (dto.getValorFecha() != null) {
				ps.setString(7, util.getFechaHoraMysql().format(dto.getValorFecha()));
			} else {
				ps.setNull(7, java.sql.Types.DATE);
			}
			ps.setObject(8, 1);
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
			log.warn(e.getMessage() + ": " + e.getStackTrace());
		} finally {
			log.trace("fin create");
		}
		return retorno;
	}

	public int update(ConfiguracionDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		int retorno = 0;
		Util util = Util.getInstance();
		String sql = "UPDATE configuracion" + " SET id_usuario=?,id_tipo=?,valor_texto=?,valor_entero=?,"
				+ " valor_decimal=?,valor_fecha=?,estado=?" + " WHERE id_configuracion=?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdUsuario());
			ps.setObject(2, dto.getIdTipo());
			ps.setString(3, dto.getValorTexto());
			ps.setObject(4, dto.getValorEntero());
			ps.setObject(5, dto.getValorDecimal());
			if (dto.getValorFecha() != null) {
				ps.setString(6, util.getFechaHoraMysql().format(dto.getValorFecha()));
			} else {
				ps.setNull(6, java.sql.Types.DATE);
			}
			ps.setObject(7, dto.getEstado());
			ps.setObject(8, dto.getIdConfiguracion());
			ps.executeUpdate();
			retorno = dto.getIdConfiguracion();
		} catch (Exception e) {
			log.warn(e.getMessage() + ": " + e.getStackTrace());
		} finally {
			log.trace("fin update");
		}
		return retorno;
	}

	public int merge(ConfiguracionDTO dto) {
		if (dto == null) {
			return 0;
		}
		if (!existeConfiguracion(dto.getIdUsuario(), dto.getIdTipo())) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public int delete(ConfiguracionDTO dto) {
		if (dto == null) {
			return 0;
		}
		return deleteByPK(dto.getIdConfiguracion());
	}

	public List<ConfiguracionDTO> consultarConfiguracionPorIdUsuarioYEstado(Integer idUsuario, Integer estado) {
		List<ConfiguracionDTO> lista = new ArrayList<>();
		if (idUsuario == null || estado == null) {
			return lista;
		}
		log.trace("inicio consultarConfiguracionPorIdUsuarioYEstado");
		String sql = "SELECT * FROM configuracion WHERE id_usuario = ? AND estado = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ps.setInt(2, estado);
			ResultSet rs = ps.executeQuery();
			lista = setList(rs);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage() + ": " + sqle.getStackTrace());
		}
		log.trace("fin consultarConfiguracionPorIdUsuarioYEstado");
		return lista;
	}

	public boolean existeConfiguracion(Integer idUsuario, Integer idTipo) {
		return !consultarConfiguracionPorIdUsuarioIdTipoYEstado(idUsuario, idTipo, 1).isEmpty();
	}

	public List<ConfiguracionDTO> consultarConfiguracionPorIdUsuarioIdTipoYEstado(Integer idUsuario, Integer idTipo,
			Integer estado) {
		List<ConfiguracionDTO> lista = new ArrayList<>();
		if (idUsuario == null || idTipo == null || estado == null) {
			return lista;
		}
		log.trace("inicio consultarConfiguracionPorIdUsuarioIdTipoYEstado");
		String sql = "SELECT * FROM configuracion WHERE id_usuario = ? AND id_tipo = ? AND estado = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ps.setInt(2, idTipo);
			ps.setInt(3, estado);
			ResultSet rs = ps.executeQuery();
			lista = setList(rs);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage() + ": " + sqle.getStackTrace());
		}
		log.trace("fin consultarConfiguracionPorIdUsuarioIdTipoYEstado");
		return lista;
	}

	public List<ConfiguracionDTO> setList(ResultSet rs) throws SQLException {
		List<ConfiguracionDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		Util util = Util.getInstance();
		while (rs.next()) {
			ConfiguracionDTO c = new ConfiguracionDTO();
			c.setIdConfiguracion((Integer) rs.getObject("id_configuracion"));
			c.setIdUsuario((Integer) rs.getObject("id_usuario"));
			c.setIdTipo((Integer) rs.getObject("id_tipo"));
			c.setValorTexto(rs.getString("valor_texto"));
			c.setValorEntero((Integer) rs.getObject("valor_entero"));
			c.setValorDecimal((Double) rs.getObject("valor_decimal"));
			try {
				if (rs.getString("valor_fecha") != null) {
					c.setValorFecha(util.getFechaHoraMysql().parse(rs.getString("valor_fecha")));
				}
			} catch (ParseException pe) {
				log.trace("La fecha de la configuracion no tiene el formato esperado: ConfiguracionDAO");
				log.warn(pe.getMessage() + ": " + pe.getStackTrace());
			}
			c.setEstado((Integer) rs.getObject("estado"));
			lista.add(c);
		}
		rs.close();
		return lista;
	}

}
