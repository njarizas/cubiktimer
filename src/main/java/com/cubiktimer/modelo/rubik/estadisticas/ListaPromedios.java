/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik.estadisticas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaPromedios implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<ListaPromedioCategoria> lista;

	public ListaPromedios() {
		super();
		lista = new ArrayList<>();
	}

	public List<ListaPromedioCategoria> getLista() {
		return lista;
	}

	public void setLista(List<ListaPromedioCategoria> lista) {
		this.lista = lista;
	}
	
	public String obtenerDatos(){
		StringBuilder retorno=new StringBuilder();
        for (int i=0; i<lista.size();i++) {
            if (i!=0){
                retorno.append(",");
            }
            retorno.append(lista.get(i).obtenerDatosUTC());
        }
        return retorno.toString();
	}

}
