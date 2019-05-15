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
import com.cubiktimer.modelo.dto.ParametroDTO;
import com.cubiktimer.util.Constantes;

public class ParametroDAO extends DAO<String, ParametroDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ParametroDAO.class);

	public ParametroDAO() {
		super(Constantes.TABLA_PARAMETROS);
	}

	@Override
	public int create(ParametroDTO dto) {
		log.trace("inicio create");
		int retorno = 0;
		String sql = "INSERT INTO parametros VALUES (?,?)";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getCodigo());
			ps.setObject(2, dto.getValor());
			retorno = ps.executeUpdate();
			log.trace("fin create");
			return retorno;
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin create");
		return 0;
	}

	public String obtenerValorParametro(String codigo) {
		log.trace("inicio obtenerValorParametro");
		List<ParametroDTO> lista = obtenerParametro(codigo);
		if (!lista.isEmpty()) {
			log.trace("fin obtenerValorParametro");
			return lista.get(0).getValor();
		}
		log.info("No existe un parametro con código: " + codigo);
		log.trace("fin obtenerValorParametro");
		return "";
	}

	public List<ParametroDTO> obtenerParametro(String codigo) {
		log.trace("inicio obtenerParametro");
		List<ParametroDTO> lista = new ArrayList<>();
		String sql = "SELECT codigo, valor FROM parametros WHERE codigo = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, codigo);
			lista = findList(ps);
		} catch (SQLException e) {
			log.warn(e.getMessage());
		}
		log.trace("fin obtenerParametro");
		return lista;
	}

	public List<ParametroDTO> setList(ResultSet rs) throws SQLException {
		List<ParametroDTO> lista = new ArrayList<>();
		while (rs.next()) {
			ParametroDTO p = new ParametroDTO();
			p.setCodigo(rs.getString("codigo"));
			p.setValor(rs.getString("valor"));
			lista.add(p);
		}
		rs.close();
		return lista;
	}

}
