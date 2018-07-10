package xyz.njas.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import xyz.njas.modelo.dto.UsuarioDTO;
import xyz.njas.util.Util;

public class UsuarioDAO extends DAO<Integer,UsuarioDTO> {

	@Override
	public int create(UsuarioDTO dto) {
		int retorno=0;
		conectar();
		String sql="INSERT INTO usuarios VALUES (?,?,?,?,?,?,?,?,?,?)";
		String sql2="INSERT INTO usuarios_roles VALUES (?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement ps2=conn.prepareStatement(sql2);
			ps.setObject(1, null);
			ps.setString(2, dto.getCorreo());
			ps.setString(3, dto.getClave());
			ps.setString(4, dto.getNombres());
			ps.setString(5, dto.getApellidos());
			ps.setString(6, String.valueOf(dto.getSexo()));
			ps.setString(7, Util.fechaHoraMySql.format(dto.getFechaNacimiento()));
			ps.setString(8, Util.fechaHoraMySql.format(dto.getFechaCreacion()));
			ps.setString(9, Util.fechaHoraMySql.format(dto.getFechaModificacion()));
			ps.setObject(10, dto.getEstado());
			retorno=ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
			  retorno = rs.getInt(1);
			}
			List<Integer> listaRoles = new ArrayList<Integer>();
			listaRoles.add(1);
			listaRoles.add(2);
			ps2.setInt(1, retorno);
			ps2.setInt(3, 1);
			for (Integer integer : listaRoles) {
				ps2.setObject(2, integer);
				ps2.executeUpdate();
			}
			desconectar();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			desconectar();
		} 
		return 0;
	}
	
	public int update(UsuarioDTO dto) {
		int retorno=0;
		conectar();
		String sql="UPDATE usuarios SET correo = ?, clave = ?, nombres = ?, apellidos = ?, sexo = ?, fecha_nacimiento = ?, fecha_creacion = ?, fecha_modificacion = ?, estado = ? WHERE id_usuario = ?;";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, dto.getCorreo());
			ps.setString(2, dto.getClave());
			ps.setString(3, dto.getNombres());
			ps.setString(4, dto.getApellidos());
			ps.setString(5, String.valueOf(dto.getSexo()));
			ps.setString(6, Util.fechaHoraMySql.format(dto.getFechaNacimiento()));
			ps.setString(7, Util.fechaHoraMySql.format(dto.getFechaCreacion()));
			ps.setString(8, Util.fechaHoraMySql.format(dto.getFechaModificacion()));
			ps.setObject(9, dto.getEstado());
			ps.setObject(10, dto.getIdUsuario());
			ps.executeUpdate();
			retorno=dto.getIdUsuario();
			desconectar();
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			desconectar();
		} 
		return 0;
	}
	
	public int merge(UsuarioDTO dto) {
		if (dto.getIdUsuario()==null){
			return create(dto);
		} else{
			return update(dto);
		}
	}

	public List<UsuarioDTO> consultarUsuarioPorCorreo(String correo){
		List<UsuarioDTO> lista= new ArrayList<UsuarioDTO>();
		conectar();
		String sql="SELECT * FROM usuarios WHERE correo = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, correo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				UsuarioDTO u = new UsuarioDTO();
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setCorreo(rs.getString("correo"));
				u.setClave(rs.getString("clave"));
				u.setNombres(rs.getString("nombres"));
				u.setApellidos(rs.getString("apellidos"));
				u.setSexo(rs.getString("sexo").charAt(0));
				u.setFechaNacimiento(Util.fechaMySql.parse(rs.getString("fecha_nacimiento")));
				u.setFechaCreacion(Util.fechaHoraMySql.parse(rs.getString("fecha_creacion")));
				u.setFechaModificacion(Util.fechaHoraMySql.parse(rs.getString("fecha_modificacion")));
				u.setEstado(rs.getInt("estado"));
				lista.add(u);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (ParseException pe) {
			pe.printStackTrace();
		}
		return lista;
	}
	
	public List<UsuarioDTO> consultarUsuarioPorIdUsuario(int idUsuario){
		List<UsuarioDTO> lista= new ArrayList<UsuarioDTO>();
		conectar();
		String sql="SELECT * FROM usuarios WHERE id_usuario = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				UsuarioDTO u = new UsuarioDTO();
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setCorreo(rs.getString("correo"));
				u.setClave(rs.getString("clave"));
				u.setNombres(rs.getString("nombres"));
				u.setApellidos(rs.getString("apellidos"));
				u.setSexo(rs.getString("sexo").charAt(0));
				u.setFechaNacimiento(Util.fechaMySql.parse(rs.getString("fecha_nacimiento")));
				u.setFechaCreacion(Util.fechaHoraMySql.parse(rs.getString("fecha_creacion")));
				u.setFechaModificacion(Util.fechaHoraMySql.parse(rs.getString("fecha_modificacion")));
				u.setEstado(rs.getInt("estado"));
				lista.add(u);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (ParseException pe) {
			pe.printStackTrace();
		}
		return lista;
	}
	
	public List<UsuarioDTO> consultarUsuarioPorIdUsuarioYClave(int idUsuario, String clave){
		List<UsuarioDTO> lista= new ArrayList<UsuarioDTO>();
		conectar();
		String sql="SELECT * FROM usuarios WHERE id_usuario = ? AND clave = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			ps.setString(2, clave);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				UsuarioDTO u = new UsuarioDTO();
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setCorreo(rs.getString("correo"));
				u.setClave(rs.getString("clave"));
				u.setNombres(rs.getString("nombres"));
				u.setApellidos(rs.getString("apellidos"));
				u.setSexo(rs.getString("sexo").charAt(0));
				u.setFechaNacimiento(Util.fechaMySql.parse(rs.getString("fecha_nacimiento")));
				u.setFechaCreacion(Util.fechaHoraMySql.parse(rs.getString("fecha_creacion")));
				u.setFechaModificacion(Util.fechaHoraMySql.parse(rs.getString("fecha_modificacion")));
				u.setEstado(rs.getInt("estado"));
				lista.add(u);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (ParseException pe) {
			pe.printStackTrace();
		}
		return lista;
	}

	public List<UsuarioDTO> consultarUsuarioPorCorreoYEstado(String correo, Integer estado){
		List<UsuarioDTO> lista= new ArrayList<UsuarioDTO>();
		conectar();
		String sql="SELECT * FROM usuarios WHERE correo = ? AND estado = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, correo);
			ps.setInt(2, estado);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				UsuarioDTO u = new UsuarioDTO();
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setCorreo(rs.getString("correo"));
				u.setNombres(rs.getString("nombres"));
				u.setApellidos(rs.getString("apellidos"));
				u.setSexo(rs.getString("sexo").charAt(0));
				u.setFechaNacimiento(Util.fechaHoraMySql.parse(rs.getString("fecha_nacimiento")));
				u.setFechaNacimiento(Util.fechaHoraMySql.parse(rs.getString("fecha_creacion")));
				u.setFechaNacimiento(Util.fechaHoraMySql.parse(rs.getString("fecha_modificacion")));
				u.setEstado(rs.getInt("estado"));
				lista.add(u);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		catch (ParseException pe) {
			pe.printStackTrace();
		}
		return lista;
	}

}
