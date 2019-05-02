package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.config.CubikTimerDataSource;
import com.cubiktimer.modelo.dto.TipoDTO;
import com.mysql.jdbc.Statement;

public class TipoDAO extends DAO<Integer, TipoDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(TipoDAO.class);

	@Override
	public int create(TipoDTO dto) {
		log.trace("inicio create");
		int retorno = 0;
		conectar();
		String sql = "INSERT INTO tipos VALUES (?,?,?,?,?)";
		try (PreparedStatement ps = CubikTimerDataSource.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

	public List<TipoDTO> listarTiposDeCuboPorEstado(Integer estado) {
		log.trace("inicio listarTiposDeCubo");
		List<TipoDTO> lista = new ArrayList<>();
		conectar();
		String sql = "SELECT * FROM tipos WHERE id_padre=2 AND estado=?";
		try (PreparedStatement ps = CubikTimerDataSource.getConnection().prepareStatement(sql)) {
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

	public List<TipoDTO> findList(PreparedStatement ps) {
		log.trace("inicio findList");
		List<TipoDTO> lista = new ArrayList<>();
		try {
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					TipoDTO t = new TipoDTO();
					t.setIdTipo((Integer) rs.getObject("id_tipo"));
					t.setIdPadre((Integer) rs.getObject("id_padre"));
					t.setNombreTipo(rs.getString("nombre_tipo"));
					t.setNameTipo(rs.getString("name_tipo"));
					t.setEstado((Integer) rs.getObject("estado"));
					lista.add(t);
				}
			} finally {
				rs.close();
			}
			desconectar();
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
			desconectar();
		}
		log.trace("fin findList");
		return lista;
	}

}
