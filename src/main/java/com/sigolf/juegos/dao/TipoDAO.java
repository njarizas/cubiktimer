package com.sigolf.juegos.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sigolf.juegos.dto.TipoDTO;

public class TipoDAO extends DAO<Integer,TipoDTO> {

	@Override
	public int create(TipoDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<TipoDTO> listarTiposDeCubo(){
		List<TipoDTO> lista=new ArrayList<TipoDTO>();
		conectar();
		String sql="SELECT * FROM tipos WHERE id_padre=2 AND estado=1";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				TipoDTO t = new TipoDTO();
				t.setIdTipo((Integer)rs.getObject("id_tipo"));
				t.setIdPadre((Integer)rs.getObject("id_padre"));
				t.setNombreTipo(rs.getString("nombre_tipo"));
				t.setNameTipo(rs.getString("name_tipo"));
				t.setEstado((Integer)rs.getObject("estado"));
				lista.add(t);
			}
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		}
		return lista;
	}

}
