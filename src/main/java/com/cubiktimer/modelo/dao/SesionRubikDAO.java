package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.cubiktimer.config.CubikTimerDataSource;
import com.cubiktimer.modelo.dto.SesionRubikDTO;
import com.cubiktimer.util.Util;
import com.mysql.jdbc.Statement;

public class SesionRubikDAO extends DAO<Integer, SesionRubikDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SesionRubikDAO.class);

	@Override
	public int create(SesionRubikDTO dto) {
		log.trace("inicio create");
		int retorno = 0;
		Util util = Util.getInstance();
		conectar();
		String sql = "INSERT INTO sesiones_rubik " + " (id_sesion,id_usuario,fecha,ip,estado)" + " VALUES (?,?,?,?,?)";
		try (PreparedStatement ps = CubikTimerDataSource.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, null);
			ps.setObject(2, dto.getIdUsuario());
			ps.setString(3, util.getFechaHoraMysql().format(dto.getFecha()));
			ps.setString(4, dto.getIp());
			ps.setObject(5, dto.getEstado());
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

	public int update(SesionRubikDTO dto) {
		log.trace("inicio update");
		int retorno = 0;
		Util util = Util.getInstance();
		conectar();
		String sql = "UPDATE sesiones_rubik" + " SET id_usuario=?,fecha=?,ip=?,estado=?" + " WHERE id_sesion=?";
		try (PreparedStatement ps = CubikTimerDataSource.getConnection().prepareStatement(sql)) {
			ps.setObject(1, dto.getIdUsuario());
			ps.setString(2, util.getFechaHoraMysql().format(dto.getFecha()));
			ps.setString(3, dto.getIp());
			ps.setObject(4, dto.getEstado());
			ps.setObject(5, dto.getIdSesion());
			ps.executeUpdate();
			retorno = dto.getIdSesion();
			desconectar();
			log.trace("fin update");
			return retorno;
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
		log.trace("fin update");
		return 0;
	}

	public int merge(SesionRubikDTO dto) {
		if (dto.getIdSesion() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

}
