package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubiktimer.modelo.dto.ParametroDTO;

public class ParametroDAO extends DAO<String, ParametroDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AmigoDAO.class);

	@Override
	public int create(ParametroDTO dto) {
		int retorno = 0;
		conectar();
		String sql = "INSERT INTO parametros VALUES (?,?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setObject(1, dto.getCodigo());
			ps.setObject(2, dto.getValor());
			retorno = ps.executeUpdate();
			desconectar();
			return retorno;
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
		return 0;
	}

	public String obtenerValorParametro(String codigo) {
		List<ParametroDTO> lista = obtenerParametro(codigo);
		if (!lista.isEmpty()) {
			return lista.get(0).getValor();
		}
		log.info("No existe un parametro con código: " + codigo);
		return "";
	}

	public List<ParametroDTO> obtenerParametro(String codigo) {
		List<ParametroDTO> lista = new ArrayList<>();
		conectar();
		String sql = "SELECT codigo, valor FROM parametros WHERE codigo = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, codigo);
			lista = findList(ps);
		} catch (SQLException e) {
			log.warn(e.getMessage());
		} finally {
			desconectar();
		}
		return lista;
	}

	public List<ParametroDTO> findList(PreparedStatement ps) {
		List<ParametroDTO> lista = new ArrayList<>();
		try {
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					ParametroDTO p = new ParametroDTO();
					p.setCodigo(rs.getString("codigo"));
					p.setValor(rs.getString("valor"));
					lista.add(p);
				}
			} finally {
				rs.close();
			}
			desconectar();
		} catch (SQLException sqle) {
			log.warn(sqle.getMessage());
			desconectar();
		}
		return lista;
	}

}
