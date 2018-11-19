package xyz.njas.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import xyz.njas.modelo.dto.ConfiguracionDTO;
import xyz.njas.util.Util;

public class ConfiguracionDAO extends DAO<Integer, ConfiguracionDTO> {

	@Override
	public int create(ConfiguracionDTO dto) {
		int retorno = 0;
		conectar();
		String sql = "INSERT INTO configuracion VALUES (?,?,?,?,?,?,?,?)";
		try {
			Util util = Util.getInstance();
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, null);
			ps.setObject(2, dto.getIdUsuario());
			ps.setObject(3, dto.getIdTipo());
			ps.setString(4, dto.getValorTexto());
			ps.setObject(5, dto.getValorEntero());
			ps.setObject(6, dto.getValorDecimal());
			if (dto.getValorFecha() != null) {
				ps.setString(7, util.fechaHoraMysql.format(dto.getValorFecha()));
			} else {
				ps.setNull(7, java.sql.Types.DATE);
			}
			ps.setObject(8, 1);
			retorno = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			try {
				if (rs != null && rs.next()) {
					retorno = rs.getInt(1);
				}
			} finally {
				rs.close();
			}
			desconectar();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			desconectar();
		}
		return 0;
	}

	public List<ConfiguracionDTO> consultarConfiguracionPorIdUsuarioYEstado(Integer idUsuario, Integer estado) {
		List<ConfiguracionDTO> lista = new ArrayList<ConfiguracionDTO>();
		conectar();
		String sql = "SELECT * FROM configuracion WHERE id_usuario = ? AND estado = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			ps.setInt(2, estado);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Util util = Util.getInstance();
					ConfiguracionDTO c = new ConfiguracionDTO();
					c.setIdConfiguracion((Integer) rs.getObject("id_configuracion"));
					c.setIdUsuario((Integer) rs.getObject("id_usuario"));
					c.setIdTipo((Integer) rs.getObject("id_tipo"));
					c.setValorTexto(rs.getString("valor_texto"));
					c.setValorEntero((Integer) rs.getObject("valor_entero"));
					c.setValorDecimal((Double) rs.getObject("valor_decimal"));
					try {
						c.setValorFecha(util.fechaHoraMysql.parse(rs.getString("fecha_nacimiento")));
					} catch (ParseException pe) {
						System.out.println(
								"La fecha de la configuracion esta nula o no tiene el formato esperado: ConfiguracionDAO");
						pe.getMessage();
					}
					c.setEstado((Integer) rs.getObject("estado"));
					lista.add(c);
				}
			} finally {
				rs.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return lista;
	}

	public int update(ConfiguracionDTO dto) {
		int retorno = 0;
		conectar();
		String sql = "UPDATE configuracion" + " SET id_usuario=?,id_tipo=?,valor_texto=?,valor_entero=?,"
				+ " valor_decimal=?,valor_fecha=?,estado=?" + " WHERE id_configuracion=?";
		try {
			Util util = Util.getInstance();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, dto.getIdUsuario());
			ps.setObject(2, dto.getIdTipo());
			ps.setString(3, dto.getValorTexto());
			ps.setObject(4, dto.getValorEntero());
			ps.setObject(5, dto.getValorDecimal());
			if (dto.getValorFecha() != null) {
				ps.setString(6, util.fechaHoraMysql.format(dto.getValorFecha()));
			} else {
				ps.setNull(6, java.sql.Types.DATE);
			}
			ps.setObject(7, dto.getEstado());
			ps.setObject(8, dto.getIdConfiguracion());
			ps.executeUpdate();
			retorno = dto.getIdConfiguracion();
			desconectar();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			desconectar();
		}
		return 0;
	}

	public int merge(ConfiguracionDTO dto) {
		if (!existeConfiguracion(dto.getIdUsuario(), dto.getIdTipo())) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public boolean existeConfiguracion(Integer idUsuario, Integer idTipo) {
		boolean existeConfiguracion = !consultarConfiguracionPorIdUsuarioIdTipoYEstado(idUsuario, idTipo, 1).isEmpty();
		return existeConfiguracion;
	}

	public List<ConfiguracionDTO> consultarConfiguracionPorIdUsuarioIdTipoYEstado(Integer idUsuario, Integer idTipo,
			Integer estado) {
		List<ConfiguracionDTO> lista = new ArrayList<ConfiguracionDTO>();
		conectar();
		String sql = "SELECT * FROM configuracion WHERE id_usuario = ? AND id_tipo = ? AND estado = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			ps.setInt(2, idTipo);
			ps.setInt(3, estado);
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					Util util = Util.getInstance();
					ConfiguracionDTO c = new ConfiguracionDTO();
					c.setIdConfiguracion((Integer) rs.getObject("id_configuracion"));
					c.setIdUsuario((Integer) rs.getObject("id_usuario"));
					c.setIdTipo((Integer) rs.getObject("id_tipo"));
					c.setValorTexto(rs.getString("valor_texto"));
					c.setValorEntero((Integer) rs.getObject("valor_entero"));
					c.setValorDecimal((Double) rs.getObject("valor_decimal"));
					try {
						c.setValorFecha(util.fechaHoraMysql.parse(rs.getString("valor_fecha")));
					} catch (ParseException pe) {
						System.out
								.println("La fecha de la configuracion no tiene el formato esperado: ConfiguracionDAO");
						pe.getMessage();
					} catch (NullPointerException npe) {
						System.out.println("La fecha de la configuracion esta nula: ConfiguracionDAO");
						npe.getMessage();
					} catch (Exception e) {
						e.printStackTrace();
					}
					c.setEstado((Integer) rs.getObject("estado"));
					lista.add(c);
				}
			} finally {
				rs.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return lista;
	}

}
