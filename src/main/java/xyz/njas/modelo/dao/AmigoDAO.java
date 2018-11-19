package xyz.njas.modelo.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import xyz.njas.modelo.dto.AmigoDTO;

public class AmigoDAO extends DAO<Integer, AmigoDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public int create(AmigoDTO dto) {
		int retorno = 0;
		conectar();
		String sql = "INSERT INTO amigos VALUES (?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, null);
			ps.setObject(2, dto.getIdUsuario());
			ps.setObject(3, dto.getIdAmigo());
			ps.setObject(4, dto.getEstado());
			retorno = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			try {
				if (rs != null && rs.next()) {
					retorno = rs.getInt(1);
				}
			} finally {
				rs.close();
			}
			desconectar();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			desconectar();
		}
		return 0;
	}

	public int update(AmigoDTO dto) {
		int retorno = 0;
		conectar();
		String sql = "UPDATE amigos SET id_usuario = ?, id_amigo = ?, estado = ? WHERE id_amistad = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, dto.getIdUsuario());
			ps.setObject(2, dto.getIdAmigo());
			ps.setObject(3, dto.getEstado());
			ps.setObject(4, dto.getIdAmistad());
			ps.executeUpdate();
			retorno = dto.getIdUsuario();
			desconectar();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			desconectar();
		}
		return 0;
	}

	public int merge(AmigoDTO dto) {
		if (dto.getIdAmistad() == null
				&& consultarAmigosPorIdUsuarioYIdAmigo(dto.getIdUsuario(), dto.getIdAmigo()).isEmpty()) {
			return create(dto);
		} else {
			if (dto.getIdAmistad() == null) {
				if (!consultarAmigosPorIdUsuarioYIdAmigo(dto.getIdUsuario(), dto.getIdAmigo()).isEmpty()) {
					dto.setIdAmistad(consultarAmigosPorIdUsuarioYIdAmigo(dto.getIdUsuario(), dto.getIdAmigo()).get(0)
							.getIdAmistad());
				}
			}
			return update(dto);
		}
	}

	public void delete(AmigoDTO dto) {
		if (dto.getIdAmistad() == null) {
			if (!consultarAmigosPorIdUsuarioYIdAmigo(dto.getIdUsuario(), dto.getIdAmigo()).isEmpty()) {
				dto.setIdAmistad(consultarAmigosPorIdUsuarioYIdAmigo(dto.getIdUsuario(), dto.getIdAmigo()).get(0)
						.getIdAmistad());
			}
		}
		String sql = "DELETE FROM amigos WHERE id_amistad = ?";
		try {
			conectar();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, dto.getIdAmistad());
			ps.executeUpdate();
			desconectar();
		} catch (Exception e) {
			e.printStackTrace();
			desconectar();
		}
	}

	public List<AmigoDTO> consultarAmigosPorIdUsuarioYIdAmigo(Integer idUsuario, Integer idAmigo) {
		List<AmigoDTO> lista = new ArrayList<AmigoDTO>();
		try {
			conectar();
			String sql = "SELECT * FROM amigos a WHERE a.id_usuario = ? AND a.id_amigo = ?";
			PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			ps.setInt(2, idAmigo);
			lista = findList(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconectar();
		}
		return lista;
	}

	public List<AmigoDTO> findList(PreparedStatement ps) {
		List<AmigoDTO> lista = new ArrayList<AmigoDTO>();
		try {
			ResultSet rs = ps.executeQuery();
			try {
				while (rs.next()) {
					AmigoDTO a = new AmigoDTO();
					a.setIdAmistad(rs.getInt("id_amistad"));
					a.setIdUsuario(rs.getInt("id_usuario"));
					a.setIdAmigo(rs.getInt("id_amigo"));
					a.setEstado(rs.getInt("estado"));
					lista.add(a);
				}
			} finally {
				rs.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return lista;
	}

}
