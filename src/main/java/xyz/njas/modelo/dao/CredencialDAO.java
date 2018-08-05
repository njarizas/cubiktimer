package xyz.njas.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Statement;

import xyz.njas.modelo.dto.CredencialDTO;
import xyz.njas.util.Util;

public class CredencialDAO extends DAO<Integer,CredencialDTO> {

	@Override
	public int create(CredencialDTO dto) {
		int retorno=0;
		conectar();
		String sql="INSERT INTO credenciales VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, dto.getIdCredencial());
			ps.setObject(2, dto.getIdUsuario());
			ps.setObject(3, dto.getCorreo());
			ps.setObject(4, dto.getClave());
			ps.setObject(5, dto.getFechaInicio());
			ps.setObject(6, dto.getFechaFin());
			ps.setObject(7, dto.getEstado());
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
			desconectar();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			desconectar();
		}
		catch (ParseException pe) {
			pe.printStackTrace();
			desconectar();
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
			desconectar();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			desconectar();
		}
		catch (ParseException pe) {
			pe.printStackTrace();
			desconectar();
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
			desconectar();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			desconectar();
		}
		catch (ParseException pe) {
			pe.printStackTrace();
			desconectar();
		}
		return lista;
	}
	
	public List<CredencialDTO> traerTodoPorCorreo(String correo){
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
			desconectar();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			desconectar();
		}
		catch (ParseException pe) {
			pe.printStackTrace();
			desconectar();
		}
		return lista;
	}
	
    public Date obtenerFechaUltimaCredencial(Integer idUsuario){
        Date retorno = null;
        conectar();
        try {
            String sql="SELECT max(DATE_FORMAT(fecha_fin, \"%Y-%m-%d %H:%i:%s\")) fecha_ultimo_cambio FROM credenciales WHERE id_usuario=?";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                retorno = Util.fechaHoraMySql.parse(rs.getString("fecha_ultimo_cambio"));
                System.out.println("encontro: "+rs.getString("fecha_ultimo_cambio"));
            }
            desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
            desconectar();
        }
        return retorno;
    }

}
