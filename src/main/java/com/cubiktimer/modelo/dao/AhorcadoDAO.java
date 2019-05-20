package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.config.CubikTimerDataSource;
import com.cubiktimer.modelo.dto.AhorcadoDTO;
import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.Util;
import com.mysql.jdbc.Statement;

public class AhorcadoDAO extends DAO<Integer, AhorcadoDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AhorcadoDAO.class);

	public AhorcadoDAO() {
		super(Constantes.TABLA_AHORCADO, Constantes.PK_TABLA_AHORCADO);
	}

	@Override
	public int create(AhorcadoDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		int retorno = 0;
		Util util = Util.getInstance();
		String sql = "INSERT INTO ahorcado VALUES (?,?,?,?,?,?,?,?,?)";
		try (Connection con = CubikTimerDataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, null);
			ps.setObject(2, dto.getIdUsuario());
			ps.setString(3, util.getFechaHoraMysql().format(dto.getFecha()));
			ps.setString(4, dto.getPalabra());
			ps.setString(5, dto.getLetrasUsadas());
			ps.setObject(6, dto.getIntentosSobrantes());
			ps.setBoolean(7, dto.getGano());
			ps.setString(8, dto.getIp());
			ps.setObject(9, 1);
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

	public int update(AhorcadoDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		Util util = Util.getInstance();
		int retorno = 0;
		String sql = "UPDATE ahorcado SET id_ahorcado = ?, id_usuario = ?, fecha = ?, palabra = ?, letras_usadas = ?, intentos_sobrantes = ?, gano = ?, ip = ?, estado = ? WHERE id_ahorcado = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdAhorcado());
			ps.setObject(2, dto.getIdUsuario());
			ps.setString(3, util.getFechaHoraMysql().format(dto.getFecha()));
			ps.setString(4, dto.getPalabra());
			ps.setString(5, dto.getLetrasUsadas());
			ps.setObject(6, dto.getIntentosSobrantes());
			ps.setBoolean(7, dto.getGano());
			ps.setString(8, dto.getIp());
			ps.setObject(9, dto.getEstado());
			ps.setObject(10, dto.getIdAhorcado());
			ps.executeUpdate();
			retorno = dto.getIdAhorcado();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin update");
		}
		return retorno;
	}

	public int merge(AhorcadoDTO dto) {
		if (dto == null) {
			return 0;
		}
		if (dto.getIdAhorcado() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public int delete(AhorcadoDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio delete");
		int retorno = 0;
		String sql = "DELETE FROM ahorcado WHERE id_ahorcado = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdAhorcado());
			ps.executeUpdate();
			retorno = dto.getIdAhorcado();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin delete");
		}
		return retorno;
	}

	public List<AhorcadoDTO> setList(ResultSet rs) throws SQLException {
		List<AhorcadoDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		Util util = Util.getInstance();
		while (rs.next()) {
			AhorcadoDTO a = new AhorcadoDTO();
			a.setIdAhorcado((Integer) rs.getObject("id_ahorcado"));
			a.setIdUsuario((Integer) rs.getObject("id_usuario"));
			try {
				a.setFecha(rs.getString("fecha") == null ? new Date()
						: util.getFechaHoraMysql().parse(rs.getString("fecha")));
			} catch (ParseException e) {
				log.warn(e.getMessage());
			}
			a.setPalabra(rs.getString("palabra"));
			a.setLetrasUsadas(rs.getString("letras_usadas"));
			a.setIntentosSobrantes((Integer) rs.getObject("intentos_sobrantes"));
			a.setGano(rs.getBoolean("gano"));
			a.setIp(rs.getString("ip"));
			a.setEstado((Integer) rs.getObject("estado"));
			lista.add(a);
		}
		rs.close();
		return lista;
	}

}
