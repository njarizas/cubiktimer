package xyz.njas.modelo.dao;

import java.util.List;

import xyz.njas.modelo.dto.PermisoDTO;

public class Test {

	public static void main(String[] args) {
		
		PermisosDAO permisosDAO = new PermisosDAO();
		List<PermisoDTO> lista = permisosDAO.consultarPermisosPorIdUsuario(12);
		System.out.println("prueba");
		for (PermisoDTO permisoDTO : lista) {
			System.out.println(permisoDTO);
		}
		
		int cant = permisosDAO.contarPermisosIdPadre(1);
		System.out.println(cant);
		
	}

}
