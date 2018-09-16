package xyz.njas.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import xyz.njas.modelo.dto.RolDTO;

public class RolDAO extends DAO<Integer,RolDTO> {

	@Override
	public int create(RolDTO dto) {
		int retorno=0;
		conectar();
		String sql="INSERT INTO roles VALUES (?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, dto.getIdRol());
			ps.setObject(2, dto.getNombreRol());
			ps.setObject(3, dto.getDescripcion());
			ps.setObject(4, dto.getEstado());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
			  retorno = rs.getInt(1);
			}
			desconectar();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			desconectar();
		} 
		return 0;
	}
	
	public int update(RolDTO dto) {
		conectar();
		String sql="UPDATE roles SET nombre_rol = ?, descripcion = ?, estado = ? WHERE id_rol = ?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setObject(1, dto.getNombreRol());
			ps.setObject(2, dto.getDescripcion());
			ps.setObject(3, dto.getEstado());
			ps.setObject(4, dto.getIdRol());
			ps.executeUpdate();
			desconectar();
			return dto.getIdRol();
		} catch (Exception e) {
			e.printStackTrace();
			desconectar();
		} 
		return 0;
	}
	
	public int merge(RolDTO dto) {
		if (dto.getIdRol()==null){
			return create(dto);
		} else{
			return update(dto);
		}
	}
	
	public List<RolDTO> consultarRolesPorIdUsuario(int idUsuario){
		List<RolDTO> lista= new ArrayList<RolDTO>();
		conectar();
		String sql="SELECT R.* FROM usuarios_roles UR" + 
				" LEFT JOIN roles R" + 
				" ON UR.id_rol = R.id_rol" + 
				" WHERE id_usuario = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				RolDTO u = new RolDTO();
				u.setIdRol(rs.getInt("id_rol"));
				u.setNombreRol(rs.getString("nombre_rol"));
				u.setDescripcion(rs.getString("descripcion"));
				u.setEstado(rs.getInt("estado"));
				lista.add(u);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return lista;
	}

}