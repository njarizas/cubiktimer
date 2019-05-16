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
import com.cubiktimer.modelo.dto.AmigoDTO;
import com.cubiktimer.util.Constantes;
import com.mysql.jdbc.Statement;

public class AmigoDAO extends DAO<Integer, AmigoDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AmigoDAO.class);

	public AmigoDAO() {
		super(Constantes.TABLA_AMIGOS);
	}

	@Override
	public int create(AmigoDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio create");
		int retorno = 0;
		String sql = "INSERT INTO amigos VALUES (?,?,?,?)";
		try (Connection con = CubikTimerDataSource.getConnection();
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
			log.trace("fin create");
			return retorno;
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin create");
		return 0;
	}

	public int update(AmigoDTO dto) {
		if (dto == null) {
			return 0;
		}
		log.trace("inicio update");
		int retorno = 0;
		String sql = "UPDATE amigos SET id_amistad=?, id_usuario = ?, id_amigo = ?, estado = ? WHERE id_amistad = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdAmistad());
			ps.setObject(2, dto.getIdUsuario());
			ps.setObject(3, dto.getIdAmigo());
			ps.setObject(4, dto.getEstado());
			ps.setObject(5, dto.getIdAmistad());
			ps.executeUpdate();
			retorno = dto.getIdAmistad();
			log.trace("fin update");
			return retorno;
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin update");
		return 0;
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

	public void delete(AmigoDTO dto) {
		if (dto == null) {
			return;
		}
		log.trace("inicio delete");
		if (dto.getIdAmistad() == null && sonAmigos(dto.getIdUsuario(), dto.getIdAmigo())) {
			dto.setIdAmistad(
					consultarAmigosPorIdUsuarioYIdAmigo(dto.getIdUsuario(), dto.getIdAmigo()).get(0).getIdAmistad());
		}
		String sql = "DELETE FROM amigos WHERE id_amistad = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdAmistad());
			ps.executeUpdate();
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		log.trace("fin delete");
	}

	public List<AmigoDTO> consultarAmigosPorIdUsuarioYIdAmigo(Integer idUsuario, Integer idAmigo) {
		log.trace("inicio consultarAmigosPorIdUsuarioYIdAmigo");
		List<AmigoDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM amigos a WHERE a.id_usuario = ? AND a.id_amigo = ?";
		try (Connection con = CubikTimerDataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ps.setInt(2, idAmigo);
			lista = findList(ps);
		} catch (SQLException e) {
			log.warn(e.getMessage());
		}
		log.trace("fin consultarAmigosPorIdUsuarioYIdAmigo");
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
