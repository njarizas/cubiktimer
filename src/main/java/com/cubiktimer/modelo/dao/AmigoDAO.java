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
import com.cubiktimer.modelo.dto.AmigoDTO;
import com.cubiktimer.util.Constantes;

public class AmigoDAO extends DAO<Integer, AmigoDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(AmigoDAO.class);

	public AmigoDAO() {
		super(Constantes.TABLA_AMIGOS, Constantes.PK_TABLA_AMIGOS);
	}

	@Override
	public int create(AmigoDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		int retorno = 0;
		String sql = "INSERT INTO amigos VALUES (?,?,?,?)";
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, null);
			ps.setObject(2, dto.getIdUsuario());
			ps.setObject(3, dto.getIdAmigo());
			ps.setObject(4, dto.getEstado());
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

	public int update(AmigoDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		int retorno = 0;
		String sql = "UPDATE amigos SET id_amistad=?, id_usuario = ?, id_amigo = ?, estado = ? WHERE id_amistad = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdAmistad());
			ps.setObject(2, dto.getIdUsuario());
			ps.setObject(3, dto.getIdAmigo());
			ps.setObject(4, dto.getEstado());
			ps.setObject(5, dto.getIdAmistad());
			ps.executeUpdate();
			retorno = dto.getIdAmistad();
		} catch (Exception e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin update");
		}
		return retorno;
	}

	public int merge(AmigoDTO dto) {
		if (dto == null) {
			return 0;
		}
		if (dto.getIdAmistad() == null
				&& consultarAmigosPorIdUsuarioYIdAmigo(dto.getIdUsuario(), dto.getIdAmigo()).isEmpty()) {
			return create(dto);
		} else {
			if (dto.getIdAmistad() == null && sonAmigos(dto.getIdUsuario(), dto.getIdAmigo())) {
				dto.setIdAmistad(consultarAmigosPorIdUsuarioYIdAmigo(dto.getIdUsuario(), dto.getIdAmigo()).get(0)
						.getIdAmistad());
			}
			return update(dto);
		}
	}

	private boolean sonAmigos(Integer idUsuario, Integer idAmigo) {
		return !consultarAmigosPorIdUsuarioYIdAmigo(idUsuario, idAmigo).isEmpty();
	}

	public int delete(AmigoDTO dto) {
		if (dto == null) {
			return 0;
		}
		return deleteByPK(dto.getIdAmistad());
	}

	public List<AmigoDTO> consultarAmigosPorIdUsuarioYIdAmigo(Integer idUsuario, Integer idAmigo) {
		List<AmigoDTO> lista = new ArrayList<>();
		if (idUsuario == null || idAmigo == null) {
			return lista;
		}
		log.trace("inicio consultarAmigosPorIdUsuarioYIdAmigo");

		String sql = "SELECT * FROM amigos a WHERE a.id_usuario = ? AND a.id_amigo = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ps.setInt(2, idAmigo);
			lista = findList(ps);
		} catch (SQLException e) {
			log.warn(e.getMessage());
		} finally {
			log.trace("fin consultarAmigosPorIdUsuarioYIdAmigo");
		}
		return lista;
	}

	public List<AmigoDTO> setList(ResultSet rs) throws SQLException {
		List<AmigoDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		while (rs.next()) {
			AmigoDTO a = new AmigoDTO();
			a.setIdAmistad(rs.getInt("id_amistad"));
			a.setIdUsuario(rs.getInt("id_usuario"));
			a.setIdAmigo(rs.getInt("id_amigo"));
			a.setEstado(rs.getInt("estado"));
			lista.add(a);
		}
		rs.close();
		return lista;
	}

}
