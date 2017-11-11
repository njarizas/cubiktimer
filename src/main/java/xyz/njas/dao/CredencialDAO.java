package xyz.njas.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import xyz.njas.dto.CredencialDTO;
import xyz.njas.util.Util;

public class CredencialDAO extends DAO<Integer,CredencialDTO> {

	@Override
	public int create(CredencialDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<CredencialDTO> consultarCredencialPorCorreo(String correo){
		List<CredencialDTO> lista= new ArrayList<CredencialDTO>();
		conectar();
		String sql="SELECT * FROM credenciales WHERE correo = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, correo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				CredencialDTO c = new CredencialDTO();
				c.setIdCredencial(rs.getInt("id_credencial"));
				c.setIdUsuario(rs.getInt("id_usuario"));
				c.setCorreo(rs.getString("correo"));
				c.setClave(rs.getString("clave"));
				c.setFechaInicio(Util.fechaHoraMySql.parse(rs.getString("fecha_inicio")));
				c.setFechaFin(Util.fechaHoraMySql.parse(rs.getString("fecha_fin")));
				c.setEstado(rs.getInt("estado"));
				lista.add(c);
			}
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		}
		catch (ParseException pe) {
			// TODO Auto-generated catch block
			pe.printStackTrace();
		}
		return lista;
	}

	public List<CredencialDTO> consultarCredencialPorCorreoYEstado(String correo,Integer estado){
		List<CredencialDTO> lista= new ArrayList<CredencialDTO>();
		conectar();
		String sql="SELECT * FROM credenciales WHERE correo = ? AND estado = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, correo);
			ps.setInt(2, estado);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				CredencialDTO c = new CredencialDTO();
				c.setIdCredencial(rs.getInt("id_credencial"));
				c.setIdUsuario(rs.getInt("id_usuario"));
				c.setCorreo(rs.getString("correo"));
				c.setClave(rs.getString("clave"));
				c.setFechaInicio(Util.fechaHoraMySql.parse(rs.getString("fecha_inicio")));
				c.setFechaFin(Util.fechaHoraMySql.parse(rs.getString("fecha_fin")));
				c.setEstado(rs.getInt("estado"));
				lista.add(c);
			}
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		}
		catch (ParseException pe) {
			// TODO Auto-generated catch block
			pe.printStackTrace();
		}
		return lista;
	}
	
	public List<CredencialDTO> consultarCredencialPorCorreoClaveYEstado(String correo, String clave, Integer estado){
		List<CredencialDTO> lista= new ArrayList<CredencialDTO>();
		conectar();
		String sql="SELECT * FROM credenciales WHERE correo = ? AND clave = ? AND estado = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, correo);
			ps.setString(2, clave);
			ps.setInt(3, estado);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				CredencialDTO c = new CredencialDTO();
				c.setIdCredencial(rs.getInt("id_credencial"));
				c.setIdUsuario(rs.getInt("id_usuario"));
				c.setCorreo(rs.getString("correo"));
				c.setClave(rs.getString("clave"));
				c.setFechaInicio(Util.fechaHoraMySql.parse(rs.getString("fecha_inicio")));
				c.setFechaFin(Util.fechaHoraMySql.parse(rs.getString("fecha_fin")));
				c.setEstado(rs.getInt("estado"));
				lista.add(c);
			}
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		}
		catch (ParseException pe) {
			// TODO Auto-generated catch block
			pe.printStackTrace();
		}
		return lista;
	}

}
