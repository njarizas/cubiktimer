package com.cubiktimer.modelo.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.cubiktimer.config.CubikTimerDataSource;
import com.cubiktimer.modelo.dto.AhorcadoDTO;
import com.cubiktimer.util.Util;

public class AhorcadoDAO extends DAO<Integer, AhorcadoDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AhorcadoDAO.class);

	@Override
	public int create(AhorcadoDTO dto) {
		log.trace("inicio create");
		int retorno = 0;
		Util util = Util.getInstance();
		conectar();
		String sql = "INSERT INTO ahorcado VALUES (?,?,?,?,?,?,?,?,?)";
		try (PreparedStatement ps = CubikTimerDataSource.getConnection().prepareStatement(sql)) {
			ps.setObject(1, null);
			ps.setObject(2, dto.getIdUsuario());
			ps.setString(3, util.getFechaHoraMysql().format(dto.getFecha()));
			ps.setString(4, dto.getPalabra());
			ps.setString(5, dto.getLetrasUsadas());
			ps.setObject(6, dto.getIntentosSobrantes());
			ps.setBoolean(7, dto.getGano());
			ps.setString(8, dto.getIp());
			ps.setObject(9, 1);
			retorno = ps.executeUpdate();
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

}
