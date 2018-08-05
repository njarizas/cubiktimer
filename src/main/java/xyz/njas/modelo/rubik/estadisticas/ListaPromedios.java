package xyz.njas.modelo.rubik.estadisticas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaPromedios implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public List<ListaPromedioCategoria> lista;

	public ListaPromedios() {
		super();
		lista = new ArrayList<ListaPromedioCategoria>();
	}

	public List<ListaPromedioCategoria> getLista() {
		return lista;
	}

	public void setLista(List<ListaPromedioCategoria> lista) {
		this.lista = lista;
	}
	
	public String obtenerDatos(){
		String retorno="";
        for (int i=0; i<lista.size();i++) {
            if (i!=0){
                retorno+=",";
            }
            retorno+=lista.get(i).obtenerDatosUTC();
        }
        return retorno;
	}

}
