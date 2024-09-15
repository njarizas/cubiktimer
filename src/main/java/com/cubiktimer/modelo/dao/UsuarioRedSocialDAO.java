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

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cubiktimer.controlador.factories.ConnectionFactory;
import com.cubiktimer.modelo.dto.UsuarioRedSocialDTO;
import com.cubiktimer.util.Constantes;

public class UsuarioRedSocialDAO extends DAO<Integer, UsuarioRedSocialDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(UsuarioRedSocialDAO.class);

	public UsuarioRedSocialDAO() {
		super(Constantes.TABLA_USUARIOS_REDES_SOCIALES, Constantes.PK_TABLA_USUARIOS_REDES_SOCIALES);
	}

	@Override
	public int create(UsuarioRedSocialDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		int retorno = 0;
		String sql = "INSERT INTO usuarios_redes_sociales VALUES (?,?,?,?)";
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, null);
			ps.setObject(2, dto.getIdUsuario());
			ps.setString(3, dto.getIdUsuarioRedSocial());
			ps.setString(4, dto.getSistema());
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

	public int update(UsuarioRedSocialDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		int retorno = 0;
		String sql = "UPDATE usuarios_redes_sociales SET id_usuario=?, id_usuario_red_social=?, sistema=? WHERE id_registro = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdRegistro());
			ps.setString(2, dto.getIdUsuarioRedSocial());
			ps.setString(3, dto.getSistema());
			ps.setObject(4, dto.getIdRegistro());
			ps.executeUpdate();
			retorno = dto.getIdRegistro();
		} catch (Exception e) {
			log.warn(ExceptionUtils.getMessage(e));
		} finally {
			log.trace("fin update");
		}
		return retorno;
	}

	public int merge(UsuarioRedSocialDTO dto) {
		if (dto == null) {
			return 0;
		}
		if (dto.getIdRegistro() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public int delete(UsuarioRedSocialDTO dto) {
		if (dto == null) {
			return 0;
		}
		return deleteByPK(dto.getIdRegistro());
	}

	public List<UsuarioRedSocialDTO> consultarUsuarioPorIdUsuarioRedSocial(String idUsuarioRedSocial) {
		log.trace("inicio consultarUsuarioPorIdUsuarioRedSocial");
		List<UsuarioRedSocialDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuarios_redes_sociales WHERE id_usuario_red_social = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, idUsuarioRedSocial);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarUsuarioPorIdUsuarioRedSocial");
		return lista;
	}

	public List<UsuarioRedSocialDTO> consultarUsuarioPorIdUsuario(int idUsuario) {
		log.trace("inicio consultarUsuarioPorIdUsuario");
		List<UsuarioRedSocialDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuarios_redes_sociales WHERE id_usuario = ?";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarUsuarioPorIdUsuario");
		return lista;
	}

	public List<UsuarioRedSocialDTO> consultarUsuariosRedesSociales() {
		log.trace("inicio consultarUsuariosRedesSociales");
		List<UsuarioRedSocialDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuarios_redes_sociales";
		try (Connection con = ConnectionFactory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin consultarUsuariosRedesSociales");
		return lista;
	}

	public List<UsuarioRedSocialDTO> setList(ResultSet rs) throws SQLException {
		List<UsuarioRedSocialDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		while (rs.next()) {
			UsuarioRedSocialDTO u = new UsuarioRedSocialDTO();
			u.setIdRegistro(rs.getInt("id_registro"));
			u.setIdUsuario(rs.getInt("id_usuario"));
			u.setIdUsuarioRedSocial(rs.getString("id_usuario_red_social"));
			u.setSistema(rs.getString("sistema"));
			lista.add(u);
		}
		rs.close();
		return lista;
	}

}
