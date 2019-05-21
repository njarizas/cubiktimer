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

import com.cubiktimer.controlador.factories.ConnectionFactory;
import com.cubiktimer.modelo.dto.SesionRubikDTO;
import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.Util;
import com.mysql.jdbc.Statement;

public class SesionRubikDAO extends DAO<Integer, SesionRubikDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SesionRubikDAO.class);

	public SesionRubikDAO() {
		super(Constantes.TABLA_SESIONES_RUBIK, Constantes.PK_TABLA_SESIONES_RUBIK);
	}

	@Override
	public int create(SesionRubikDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		int retorno = 0;
		Util util = Util.getInstance();
		String sql = "INSERT INTO sesiones_rubik " + " (id_sesion,id_usuario,fecha,ip,estado)" + " VALUES (?,?,?,?,?)";
		try (Connection con = ConnectionFactory.getConnection();
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
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin create");
		}
		return retorno;
	}

	public int update(SesionRubikDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		int retorno = 0;
		Util util = Util.getInstance();
		String sql = "UPDATE sesiones_rubik SET id_usuario=?, fecha=?, ip=?, estado=? WHERE id_sesion=?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdUsuario());
			ps.setString(2, util.getFechaHoraMysql().format(dto.getFecha()));
			ps.setString(3, dto.getIp());
			ps.setObject(4, dto.getEstado());
			ps.setObject(5, dto.getIdSesion());
			ps.executeUpdate();
			retorno = dto.getIdSesion();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin update");
		}
		return retorno;
	}

	public int merge(SesionRubikDTO dto) {
		if (dto == null) {
			return 0;
		}
		if (dto.getIdSesion() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public int delete(SesionRubikDTO dto) {
		if (dto == null) {
			return 0;
		}
		return deleteByPK(dto.getIdSesion());
	}

	public List<SesionRubikDTO> setList(ResultSet rs) throws SQLException {
		List<SesionRubikDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
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
