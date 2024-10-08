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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cubiktimer.controlador.factories.ConnectionFactory;
import com.cubiktimer.modelo.dto.TiempoRubikDTO;
import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.Util;

public class TiempoRubikDAO extends DAO<Integer, TiempoRubikDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(TiempoRubikDAO.class);

	public TiempoRubikDAO() {
		super(Constantes.TABLA_TIEMPOS_RUBIK, Constantes.PK_TABLA_TIEMPOS_RUBIK);
	}

	@Override
	public int create(TiempoRubikDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		int retorno = 0;
		Util util = Util.getInstance();
		String sql = "INSERT INTO tiempos_rubik"
				+ " (id_tiempo,id_sesion,id_average,id_tipo_cubo,mezcla,tiempo_inspeccion_segundos,"
				+ " tiempo_inspeccion_usado_milisegundos,tiempo_inspeccion_usado_texto,"
				+ " tiempo_milisegundos,tiempo_texto,dnf,penalizacion,pb,comentario,fecha,estado)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, null);
			ps.setObject(2, dto.getIdSesion());
			ps.setObject(3, dto.getIdAverage());
			ps.setObject(4, dto.getIdTipoCubo());
			ps.setString(5, dto.getMezcla());
			ps.setObject(6, dto.getTiempoInspeccionSegundos());
			ps.setObject(7, dto.getTiempoInspeccionUsadoMilisegundos());
			ps.setString(8, dto.getTiempoInspeccionUsadoTexto());
			ps.setObject(9, dto.getTiempoMilisegundos());
			ps.setString(10, dto.getTiempoTexto());
			ps.setBoolean(11, dto.getDnf());
			ps.setBoolean(12, dto.getPenalizacion());
			ps.setBoolean(13, dto.getPb());
			ps.setString(14, dto.getComentario());
			ps.setString(15, util.getFechaHoraMysql().format(dto.getFecha()));
			ps.setObject(16, dto.getEstado());
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

	public int update(TiempoRubikDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		int retorno = 0;
		Util util = Util.getInstance();
		String sql = "UPDATE tiempos_rubik SET id_sesion=?,id_average=?,id_tipo_cubo=?,mezcla=?,tiempo_inspeccion_segundos=?,"
				+ " tiempo_inspeccion_usado_milisegundos=?,tiempo_inspeccion_usado_texto=?,"
				+ " tiempo_milisegundos=?,tiempo_texto=?,dnf=?,penalizacion=?,pb=?,comentario=?,fecha=?,estado=?"
				+ " WHERE id_tiempo=?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdSesion());
			ps.setObject(2, dto.getIdAverage());
			ps.setObject(3, dto.getIdTipoCubo());
			ps.setString(4, dto.getMezcla());
			ps.setObject(5, dto.getTiempoInspeccionSegundos());
			ps.setObject(6, dto.getTiempoInspeccionUsadoMilisegundos());
			ps.setString(7, dto.getTiempoInspeccionUsadoTexto());
			ps.setObject(8, dto.getTiempoMilisegundos());
			ps.setString(9, dto.getTiempoTexto());
			ps.setBoolean(10, dto.getDnf());
			ps.setBoolean(11, dto.getPenalizacion());
			ps.setBoolean(12, dto.getPb());
			ps.setString(13, dto.getComentario());
			ps.setString(14, util.getFechaHoraMysql().format(dto.getFecha()));
			ps.setObject(15, dto.getEstado());
			ps.setObject(16, dto.getIdTiempo());
			ps.executeUpdate();
			retorno = dto.getIdTiempo();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin update");
		}
		return retorno;
	}

	public int merge(TiempoRubikDTO dto) {
		if (dto == null) {
			return 0;
		}
		if (dto.getIdTiempo() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public int delete(TiempoRubikDTO dto) {
		if (dto == null) {
			return 0;
		}
		return deleteByPK(dto.getIdTiempo());
	}

	public List<TiempoRubikDTO> setList(ResultSet rs) throws SQLException {
		List<TiempoRubikDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		Util util = Util.getInstance();
		while (rs.next()) {
			TiempoRubikDTO t = new TiempoRubikDTO();
			t.setIdTiempo((Integer) rs.getObject("id_tiempo"));
			t.setIdSesion((Integer) rs.getObject("id_sesion"));
			t.setIdAverage((Integer) rs.getObject("id_average"));
			t.setIdTipoCubo((Integer) rs.getObject("id_tipo_cubo"));
			t.setMezcla(rs.getString("mezcla"));
			t.setTiempoInspeccionSegundos((Integer) rs.getObject("tiempo_inspeccion_segundos"));
			t.setTiempoInspeccionUsadoMilisegundos((Integer) rs.getObject("tiempo_inspeccion_usado_milisegundos"));
			t.setTiempoInspeccionUsadoTexto(rs.getString("tiempo_inspeccion_usado_texto"));
			t.setTiempoMilisegundos((Integer) rs.getObject("tiempo_milisegundos"));
			t.setTiempoTexto(rs.getString("tiempo_texto"));
			t.setDnf(rs.getBoolean("dnf"));
			t.setPenalizacion(rs.getBoolean("penalizacion"));
			t.setPb((Boolean) rs.getObject("pb"));
			t.setComentario(rs.getString("comentario"));
			try {
				t.setFecha(rs.getString("fecha") == null ? new Date()
						: util.getFechaHoraMysql().parse(rs.getString("fecha")));
			} catch (ParseException e) {
				log.warn(e.getMessage());
			}
			t.setEstado((Integer) rs.getObject("estado"));
			lista.add(t);
		}
		rs.close();
		return lista;
	}

}
