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
import com.cubiktimer.modelo.dto.SesionRubikDTO;
import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.Util;
import com.mysql.jdbc.Statement;

public class SesionRubikDAO extends DAO<Integer, SesionRubikDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SesionRubikDAO.class);

	public SesionRubikDAO() {
		super(Constantes.TABLA_SESIONES_RUBIK);
	}

	@Override
	public int create(SesionRubikDTO dto) {
		log.trace("inicio create");
		int retorno = 0;
		Util util = Util.getInstance();
		String sql = "INSERT INTO sesiones_rubik " + " (id_sesion,id_usuario,fecha,ip,estado)" + " VALUES (?,?,?,?,?)";
		try (Connection con = CubikTimerDataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
			log.trace("fin create");
			return retorno;
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin create");
		return 0;
	}

	public int update(SesionRubikDTO dto) {
		log.trace("inicio update");
		int retorno = 0;
		Util util = Util.getInstance();
		String sql = "UPDATE sesiones_rubik SET id_usuario=?, fecha=?, ip=?, estado=? WHERE id_sesion=?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdUsuario());
			ps.setString(2, util.getFechaHoraMysql().format(dto.getFecha()));
			ps.setString(3, dto.getIp());
			ps.setObject(4, dto.getEstado());
			ps.setObject(5, dto.getIdSesion());
			ps.executeUpdate();
			retorno = dto.getIdSesion();
			log.trace("fin update");
			return retorno;
		} catch (Exception e) {
			log.warn(e.getMessage());
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

	public List<SesionRubikDTO> setList(ResultSet rs) throws SQLException {
		List<SesionRubikDTO> lista = new ArrayList<>();
		Util util = Util.getInstance();
		while (rs.next()) {
			SesionRubikDTO s = new SesionRubikDTO();
			s.setIdSesion((Integer) rs.getObject("id_sesion"));
			s.setIdUsuario((Integer) rs.getObject("id_usuario"));
			try {
				s.setFecha(util.getFechaHoraMysql().parse(rs.getString("fecha")));
			} catch (ParseException e) {
				log.warn(e.getMessage());
			}
			s.setIp(rs.getString("ip"));
			s.setEstado((Integer) rs.getObject("estado"));
			lista.add(s);
		}
		rs.close();
		return lista;
	}

}
