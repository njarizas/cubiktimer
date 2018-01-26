package xyz.njas.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import xyz.njas.modelo.rubik.estadisticas.CuentaPuzzle;
import xyz.njas.modelo.rubik.estadisticas.Promedio;

public class EstadisticasDAO extends DAO<CuentaPuzzle, Integer> {
	
	public List<CuentaPuzzle> getListaConteoPuzzles(Integer idUsuario) {
		conectar();
        List<CuentaPuzzle> lista = new ArrayList();
        String sql = "SELECT sr.id_usuario,t.nombre_tipo nombre_puzzle, count(*) conteo_puzzle"
        		+ " FROM tiempos_rubik tr"
        		+ " INNER JOIN tipos t"
        		+ " ON tr.id_tipo_cubo=t.id_tipo"
        		+ " INNER JOIN sesiones_rubik sr"
        		+ " ON tr.id_sesion=sr.id_sesion"
        		+ " WHERE id_usuario=?"
        		+ " GROUP BY t.nombre_tipo;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CuentaPuzzle cuentaPuzzle = new CuentaPuzzle();
                cuentaPuzzle.setIdUsuario(rs.getInt("id_usuario"));
                cuentaPuzzle.setNombrePuzzle(rs.getString("nombre_puzzle"));
                cuentaPuzzle.setConteoPuzzle(rs.getInt("conteo_puzzle"));
                lista.add(cuentaPuzzle);
            }
            desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
            desconectar();
        }
        return lista;
    }
	
	public List<Promedio> getListaPromedios(Integer idUsuario, Integer idTipoCubo){
		conectar();
        List<Promedio> lista = new ArrayList();
        String sql = "SELECT FLOOR(avg(tr.tiempo_milisegundos)) promedio, t.nombre_tipo tipo_cubo,"
        		+ " DATE_FORMAT(sr.fecha,\"%d/%m/%Y\") fecha"
        		+ " FROM tiempos_rubik tr"
        		+ " INNER JOIN sesiones_rubik sr"
        		+ " ON tr.id_sesion=sr.id_sesion"
        		+ " INNER JOIN tipos t"
        		+ " ON tr.id_tipo_cubo=t.id_tipo"
        		+ " WHERE sr.id_usuario=?"
        		+ " AND tr.id_tipo_cubo=?"
        		+ " GROUP BY sr.id_sesion,t.nombre_tipo,sr.fecha;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idTipoCubo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Promedio promedio = new Promedio();
                promedio.setPromedio(rs.getInt("promedio"));
                promedio.setTipoCubo(rs.getString("tipo_cubo"));
                promedio.setFecha(rs.getString("fecha"));
                lista.add(promedio);
            }
            desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
            desconectar();
        }
        return lista;
	}

	@Override
	public int create(Integer dto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
