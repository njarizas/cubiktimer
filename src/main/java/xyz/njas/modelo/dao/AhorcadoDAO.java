package xyz.njas.modelo.dao;

import java.sql.PreparedStatement;

import xyz.njas.modelo.dto.AhorcadoDTO;
import xyz.njas.util.Util;

public class AhorcadoDAO extends DAO<Integer,AhorcadoDTO> {

	@Override
	public int create(AhorcadoDTO dto) {
		int retorno=0;
		conectar();
		String sql="INSERT INTO ahorcado VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setObject(1, null);
			ps.setObject(2, dto.getIdUsuario());
			ps.setString(3, Util.fechaHoraMySql.format(dto.getFecha()));
			ps.setString(4, dto.getPalabra());
			ps.setString(5, dto.getLetrasUsadas());
			ps.setObject(6, dto.getIntentosSobrantes());
			ps.setBoolean(7, dto.getGano());
			ps.setString(8, dto.getIp());
			ps.setObject(9, 1);
			retorno=ps.executeUpdate();
			desconectar();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			desconectar();
		} 
		return 0;
	}

}
