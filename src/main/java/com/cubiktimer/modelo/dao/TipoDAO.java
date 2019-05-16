package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.config.CubikTimerDataSource;
import com.cubiktimer.modelo.dto.TipoDTO;
import com.cubiktimer.util.Constantes;
import com.mysql.jdbc.Statement;

public class TipoDAO extends DAO<Integer, TipoDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(TipoDAO.class);

	public TipoDAO() {
		super(Constantes.TABLA_TIPOS);
	}

	@Override
	public int create(TipoDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		int retorno = 0;
		String sql = "INSERT INTO tipos VALUES (?,?,?,?,?)";
		try (Connection con = CubikTimerDataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, dto.getIdTipo());
			ps.setObject(2, dto.getIdPadre());
			ps.setObject(3, dto.getNombreTipo());
			ps.setObject(4, dto.getNameTipo());
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

	public int update(TipoDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		int retorno = 0;
		String sql = "UPDATE tipos SET id_tipo = ?, id_padre = ?, nombre_tipo = ?, name_tipo = ?, estado = ? WHERE id_tipo = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdTipo());
			ps.setObject(2, dto.getIdPadre());
			ps.setObject(3, dto.getNombreTipo());
			ps.setObject(4, dto.getNameTipo());
			ps.setObject(5, dto.getEstado());
			ps.setObject(6, dto.getIdTipo());
			ps.executeUpdate();
			retorno = dto.getIdTipo();
			log.trace("fin update");
			return retorno;
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin update");
		return 0;
	}

	public int merge(TipoDTO dto) {
		if (dto == null) {
			return 0;
		}
		if (dto.getIdTipo() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

	public List<TipoDTO> listarTiposDeCuboPorEstado(Integer estado) {
		log.trace("inicio listarTiposDeCubo");
		List<TipoDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM tipos WHERE id_padre=2 AND estado=?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, estado);
			lista = findList(ps);
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
		}
		log.trace("fin listarTiposDeCubo");
		return lista;
	}

	public List<TipoDTO> listarTiposDeCubo() {
		return listarTiposDeCuboPorEstado(1);
	}

	public List<TipoDTO> listarTiposDeCuboFewest() {
		return listarTiposDeCuboPorEstado(101);
	}

	public List<TipoDTO> setList(ResultSet rs) throws SQLException {
		List<TipoDTO> lista = new ArrayList<>();
		if (rs == null) {
			return lista;
		}
		while (rs.next()) {
			TipoDTO t = new TipoDTO();
			t.setIdTipo((Integer) rs.getObject("id_tipo"));
			t.setIdPadre((Integer) rs.getObject("id_padre"));
			t.setNombreTipo(rs.getString("nombre_tipo"));
			t.setNameTipo(rs.getString("name_tipo"));
			t.setEstado((Integer) rs.getObject("estado"));
			lista.add(t);
		}
		rs.close();
		return lista;
	}

}
