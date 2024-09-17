/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cubiktimer.controlador.factories.ConnectionFactory;
import com.cubiktimer.modelo.dto.AverageDTO;
import com.cubiktimer.util.Constantes;

public class AverageDAO extends DAO<Integer, AverageDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(AverageDAO.class);

	public AverageDAO() {
		super(Constantes.TABLA_AVERAGES, Constantes.PK_TABLA_AVERAGES);
	}

	@Override
	public int create(AverageDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		int retorno = 0;
		String sql = "INSERT INTO averages " + " (id_average,mejor,mejor_texto,peor,peor_texto,media,media_texto,ao5,ao5_texto,estado)" + " VALUES (?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, null);
			ps.setObject(2, dto.getMejor());
			ps.setString(3, dto.getMejorTexto());
			ps.setObject(4, dto.getPeor());
			ps.setString(5, dto.getPeorTexto());
			ps.setObject(6, dto.getMedia());
			ps.setString(7, dto.getMediaTexto());
			ps.setObject(8, dto.getAo5());
			ps.setString(9, dto.getAo5Texto());
			ps.setObject(10, dto.getEstado());
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

	@Override
	public int update(AverageDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		int retorno = 0;
		String sql = "UPDATE averages SET mejor=?, mejor_texto=?, peor=?, peor_texto=?, media=?, media_texto=?, ao5=?, ao5_texto=?, estado=? WHERE id_average=?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getMejor());
			ps.setString(2, dto.getMejorTexto());
			ps.setObject(3, dto.getPeor());
			ps.setString(4, dto.getPeorTexto());
			ps.setObject(5, dto.getMedia());
			ps.setString(6, dto.getMediaTexto());
			ps.setObject(7, dto.getAo5());
			ps.setString(8, dto.getAo5Texto());
			ps.setObject(9, dto.getEstado());
			ps.setObject(10, dto.getIdAverage());
			ps.executeUpdate();
			retorno = dto.getIdAverage();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin update");
		}
		return retorno;
	}

	@Override
	public int merge(AverageDTO dto) {
		if (dto == null) {
			return 0;
		}
		if (dto.getIdAverage() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	@Override
	public int delete(AverageDTO dto) {
		if (dto == null) {
			return 0;
		}
		return deleteByPK(dto.getIdAverage());
	}

	@Override
	public List<AverageDTO> setList(ResultSet rs) throws SQLException {
		List<AverageDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		while (rs.next()) {
			AverageDTO s = new AverageDTO();
			s.setIdAverage((Integer) rs.getObject("id_average"));
			s.setMejor((Integer) rs.getObject("mejor"));
			s.setMejorTexto(rs.getString("mejor_texto"));
			s.setPeor((Integer) rs.getObject("peor"));
			s.setPeorTexto(rs.getString("peor_texto"));
			s.setMedia((Integer)rs.getObject("media"));
			s.setMediaTexto(rs.getString("media_texto"));
			s.setAo5((Integer)rs.getObject("ao5"));
			s.setAo5Texto(rs.getString("ao5_texto"));
			s.setEstado((Integer) rs.getObject("estado"));
			lista.add(s);
		}
		rs.close();
		return lista;
	}

}
