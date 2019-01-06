package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.cubiktimer.modelo.dto.TiempoRubikDTO;
import com.mysql.jdbc.Statement;

public class TiempoRubikDAO extends DAO<Integer, TiempoRubikDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(TiempoRubikDAO.class);

	@Override
	public int create(TiempoRubikDTO dto) {
		int retorno = 0;
		conectar();
		String sql = "INSERT INTO tiempos_rubik"
				+ " (id_tiempo,id_sesion,id_tipo_cubo,mezcla,tiempo_inspeccion_segundos,"
				+ " tiempo_inspeccion_usado_milisegundos,tiempo_inspeccion_usado_texto,"
				+ " tiempo_milisegundos,tiempo_texto,dnf,penalizacion,comentario,video,ip,estado)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, null);
			ps.setObject(2, dto.getIdSesion());
			ps.setObject(3, dto.getIdTipoCubo());
			ps.setString(4, dto.getMezcla());
			ps.setObject(5, dto.getTiempoInspeccionSegundos());
			ps.setObject(6, dto.getTiempoInspeccionUsadoMilisegundos());
			ps.setString(7, dto.getTiempoInspeccionUsadoTexto());
			ps.setObject(8, dto.getTiempoMilisegundos());
			ps.setString(9, dto.getTiempoTexto());
			ps.setBoolean(10, dto.getDnf());
			ps.setBoolean(11, dto.getPenalizacion());
			ps.setString(12, dto.getComentario());
			ps.setString(13, dto.getVideo());
			ps.setString(14, dto.getIp());
			ps.setObject(15, dto.getEstado());
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
			return retorno;
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
		return 0;
	}

	public int update(TiempoRubikDTO dto) {
		int retorno = 0;
		conectar();
		String sql = "UPDATE tiempos_rubik" + " SET id_sesion=?,id_tipo_cubo=?,mezcla=?,tiempo_inspeccion_segundos=?,"
				+ " tiempo_inspeccion_usado_milisegundos=?,tiempo_inspeccion_usado_texto=?,"
				+ " tiempo_milisegundos=?,tiempo_texto=?,dnf=?,penalizacion=?,comentario=?,video=?,ip=?,estado=?"
				+ " WHERE id_tiempo=?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdSesion());
			ps.setObject(2, dto.getIdTipoCubo());
			ps.setString(3, dto.getMezcla());
			ps.setObject(4, dto.getTiempoInspeccionSegundos());
			ps.setObject(5, dto.getTiempoInspeccionUsadoMilisegundos());
			ps.setString(6, dto.getTiempoInspeccionUsadoTexto());
			ps.setObject(7, dto.getTiempoMilisegundos());
			ps.setString(8, dto.getTiempoTexto());
			ps.setBoolean(9, dto.getDnf());
			ps.setBoolean(10, dto.getPenalizacion());
			ps.setString(11, dto.getComentario());
			ps.setString(12, dto.getVideo());
			ps.setString(13, dto.getIp());
			ps.setObject(14, dto.getEstado());
			ps.setObject(15, dto.getIdTiempo());
			ps.executeUpdate();
			retorno = dto.getIdTiempo();
			desconectar();
			return retorno;
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
		return 0;
	}

	public int merge(TiempoRubikDTO dto) {
		if (dto.getIdTiempo() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

}
