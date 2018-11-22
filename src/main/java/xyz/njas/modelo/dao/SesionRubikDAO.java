package xyz.njas.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;

import xyz.njas.modelo.dto.SesionRubikDTO;
import xyz.njas.util.Util;

public class SesionRubikDAO extends DAO<Integer, SesionRubikDTO> {

	private static final Logger log = Logger.getLogger(SesionRubikDAO.class);

	@Override
	public int create(SesionRubikDTO dto) {
		int retorno = 0;
		Util util = Util.getInstance();
		conectar();
		String sql = "INSERT INTO sesiones_rubik " + " (id_sesion,id_usuario,fecha,ip,estado)" + " VALUES (?,?,?,?,?)";
		try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, null);
			ps.setObject(2, dto.getIdUsuario());
			ps.setString(3, util.getFechaHoraMysql().format(dto.getFecha()));
			ps.setString(4, dto.getIp());
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

	public int update(SesionRubikDTO dto) {
		int retorno = 0;
		Util util = Util.getInstance();
		conectar();
		String sql = "UPDATE sesiones_rubik" + " SET id_usuario=?,fecha=?,ip=?,estado=?" + " WHERE id_sesion=?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setObject(1, dto.getIdUsuario());
			ps.setString(2, util.getFechaHoraMysql().format(dto.getFecha()));
			ps.setString(3, dto.getIp());
			ps.setObject(4, dto.getEstado());
			ps.setObject(5, dto.getIdSesion());
			ps.executeUpdate();
			retorno = dto.getIdSesion();
			desconectar();
			return retorno;
		} catch (Exception e) {
			log.warn(e.getMessage());
			desconectar();
		}
		return 0;
	}

	public int merge(SesionRubikDTO dto) {
		if (dto.getIdSesion() == null) {
			return create(dto);
		} else {
			return update(dto);
		}
	}

}
