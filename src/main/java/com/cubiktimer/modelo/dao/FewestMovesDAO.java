package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.cubiktimer.modelo.dto.FewestMovesDTO;
import com.cubiktimer.util.Util;
import com.mysql.jdbc.Statement;

public class FewestMovesDAO extends DAO<Integer, FewestMovesDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(FewestMovesDAO.class);

	@Override
	public int create(FewestMovesDTO dto) {
		log.trace("inicio create");
		int retorno = 0;
		Util util = Util.getInstance();
		conectar();
		String sql = "INSERT INTO soluciones_rubik (id_solucion, id_sesion, id_tipo_cubo, mezcla, tiempo_usado_milisegundos,"
				+ " tiempo_restante_texto, solucion, longitud_solucion, solucion_valida, dnf, comentario, fecha, estado)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

	public int update(FewestMovesDTO dto) {
		log.trace("inicio update");
		int retorno = 0;
		Util util = Util.getInstance();
		conectar();
		String sql = "UPDATE cubiktimer.soluciones_rubik SET id_sesion = ?, id_tipo_cubo = ?,"
				+ " mezcla = ?, tiempo_usado_milisegundos = ?, tiempo_restante_texto = ?, solucion = ?,"
				+ " longitud_solucion = ?, solucion_valida = ?, dnf = ?, comentario = ?, fecha = ?, estado = ?"
				+ " WHERE id_solucion = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
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

	public int merge(FewestMovesDTO dto) {
		if (dto.getIdFewestMove() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

}
