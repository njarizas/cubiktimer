/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik.estadisticas;

import java.io.Serializable;
import java.util.List;

public class ListaCuentaPuzzle implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<CuentaPuzzle> lista;
	
	public List<CuentaPuzzle> getLista() {
		return lista;
	}

	public void setLista(List<CuentaPuzzle> lista) {
		this.lista = lista;
	}

	public String obtenerDatos(){
		StringBuilder retorno=new StringBuilder("");
        for (int i=0; i<lista.size();i++) {
            if (i!=0){
                retorno.append(",");
            }
            retorno.append(lista.get(i).toString());
        }
        return retorno.toString();
	}

}
