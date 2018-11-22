package xyz.njas.modelo.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;

import xyz.njas.modelo.dto.TipoDTO;

public class TipoDAO extends DAO<Integer, TipoDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(TipoDAO.class);

	@Override
	public int create(TipoDTO dto) {
		int retorno = 0;
		conectar();
		String sql = "INSERT INTO tipos VALUES (?,?,?,?,?)";
		try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
			return retorno;
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
		return 0;
	}

	public List<TipoDTO> listarTiposDeCubo() {
		List<TipoDTO> lista = new ArrayList<>();
		conectar();
		String sql = "SELECT * FROM tipos WHERE id_padre=2 AND estado=1";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
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
		return lista;
	}

}
