package xyz.njas.modelo.rubik.estadisticas;

import java.io.Serializable;
import java.util.List;

public class ListaPromedios implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Promedio> lista;

	public List<Promedio> getLista() {
		return lista;
	}

	public void setLista(List<Promedio> lista) {
		this.lista = lista;
	}
	
	public String obtenerFechas(){
		String retorno="";
        for (int i=0; i<lista.size();i++) {
            if (i!=0){
                retorno+=",";
            }
            retorno+="'"+lista.get(i).getFecha()+"'";
        }
        return retorno;
	}
	
	public String obtenerPromedios(){
		String retorno="";
        for (int i=0; i<lista.size();i++) {
            if (i!=0){
                retorno+=",";
            }
            retorno+=lista.get(i).getPromedio();
        }
        return retorno;
	}
	
	public String obtenerTipoCubo(){
		if (lista.size()>=0){
			return lista.get(0).getTipoCubo();
		}
		return "";
	}

}
