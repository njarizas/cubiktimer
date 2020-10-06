package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.controlador.factories.ConnectionFactory;
import com.cubiktimer.modelo.dto.FewestMovesDTO;
import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.Util;

public class FewestMovesDAO extends DAO<Integer, FewestMovesDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(FewestMovesDAO.class);

	public FewestMovesDAO() {
		super(Constantes.TABLA_SOLUCIONES_RUBIK, Constantes.PK_TABLA_SOLUCIONES_RUBIK);
	}

	@Override
	public int create(FewestMovesDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		int retorno = 0;
		Util util = Util.getInstance();
		String sql = "INSERT INTO soluciones_rubik (id_solucion, id_sesion, id_tipo_cubo, mezcla, tiempo_usado_milisegundos,"
				+ " tiempo_restante_texto, solucion, longitud_solucion, solucion_valida, dnf, comentario, fecha, estado)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, null);
			ps.setObject(2, dto.getIdSesion());
			ps.setObject(3, dto.getIdTipoCubo());
			ps.setString(4, dto.getMezcla());
			ps.setObject(5, dto.getTiempoUsadoMilisegundos());
			ps.setString(6, dto.getTiempoRestanteTexto());
			ps.setString(7, dto.getSolucion());
			ps.setObject(8, dto.getLongitudSolucion());
			ps.setBoolean(9, dto.getSolucionValida());
			ps.setBoolean(10, dto.getDnf());
			ps.setString(11, dto.getComentario());
			ps.setString(12, util.getFechaHoraMysql().format(dto.getFecha()));
			ps.setObject(13, dto.getEstado());
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

	public int update(FewestMovesDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		int retorno = 0;
		Util util = Util.getInstance();
		String sql = "UPDATE soluciones_rubik SET id_sesion = ?, id_tipo_cubo = ?,"
				+ " mezcla = ?, tiempo_usado_milisegundos = ?, tiempo_restante_texto = ?, solucion = ?,"
				+ " longitud_solucion = ?, solucion_valida = ?, dnf = ?, comentario = ?, fecha = ?, estado = ?"
				+ " WHERE id_solucion = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdSesion());
			ps.setObject(2, dto.getIdTipoCubo());
			ps.setString(3, dto.getMezcla());
			ps.setObject(4, dto.getTiempoUsadoMilisegundos());
			ps.setString(5, dto.getTiempoRestanteTexto());
			ps.setString(6, dto.getSolucion());
			ps.setObject(7, dto.getLongitudSolucion());
			ps.setBoolean(8, dto.getSolucionValida());
			ps.setBoolean(9, dto.getDnf());
			ps.setString(10, dto.getComentario());
			ps.setString(11, util.getFechaHoraMysql().format(dto.getFecha()));
			ps.setObject(12, dto.getEstado());
			ps.setObject(13, dto.getIdFewestMove());
			ps.executeUpdate();
			retorno = dto.getIdFewestMove();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin update");
		}
		return retorno;
	}

	public int merge(FewestMovesDTO dto) {
		if (dto == null) {
			return 0;
		}
		if (dto.getIdFewestMove() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public int delete(FewestMovesDTO dto) {
		if (dto == null) {
			return 0;
		}
		return deleteByPK(dto.getIdFewestMove());
	}

	public List<FewestMovesDTO> setList(ResultSet rs) throws SQLException {
		List<FewestMovesDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		Util util = Util.getInstance();
		while (rs.next()) {
			FewestMovesDTO f = new FewestMovesDTO();
			f.setIdFewestMove((Integer) rs.getObject("id_solucion"));
			f.setIdSesion((Integer) rs.getObject("id_sesion"));
			f.setIdTipoCubo((Integer) rs.getObject("id_tipo_cubo"));
			f.setMezcla(rs.getString("mezcla"));
			f.setTiempoUsadoMilisegundos((Integer) rs.getObject("tiempo_usado_milisegundos"));
			f.setTiempoRestanteTexto(rs.getString("tiempo_restante_texto"));
			f.setSolucion(rs.getString("solucion"));
			f.setLongitudSolucion((Integer) rs.getObject("longitud_solucion"));
			f.setSolucionValida(rs.getBoolean("solucion_valida"));
			f.setDnf(rs.getBoolean("dnf"));
			f.setComentario(rs.getString("comentario"));
			try {
				f.setFecha(util.getFechaHoraMysql().parse(rs.getString("fecha")));
			} catch (ParseException e) {
				log.warn(e.getMessage());
			}
			f.setEstado((Integer) rs.getObject("estado"));
			lista.add(f);
		}
		rs.close();
		return lista;
	}

}
