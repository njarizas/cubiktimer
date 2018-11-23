package com.cubiktimer.modelo.rubik.estadisticas;

import java.io.Serializable;
import java.util.List;

public class ListaPromedioCategoria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Promedio> lista;

	public List<Promedio> getLista() {
		return lista;
	}

	public void setLista(List<Promedio> lista) {
		this.lista = lista;
	}
	
	public String obtenerFechas(){
		StringBuilder retorno=new StringBuilder("");
        for (int i=0; i<lista.size();i++) {
            if (i!=0){
                retorno.append(",");
            }
            retorno.append("'"+lista.get(i).getFecha()+"'");
        }
        return retorno.toString();
	}
	
	public String obtenerDatosUTC(){
		StringBuilder retorno= new StringBuilder("{"
				+ " name: '"+obtenerTipoCubo()+"',"
				+ " data: [");
        for (int i=0; i<lista.size();i++) {
            if (i!=0){
                retorno.append(",");
            }
            retorno.append("[Date.UTC("+lista.get(i).getFechaUTC()+"),"+lista.get(i).getProm()+"]");
        }
        retorno.append(""
        		+ "]"
        		+ "}");
        return retorno.toString();
	}
	
	public String obtenerPromedios(){
		StringBuilder retorno= new StringBuilder("");
        for (int i=0; i<lista.size();i++) {
            if (i!=0){
                retorno.append(",");
            }
            retorno.append(lista.get(i).getProm());
        }
        return retorno.toString();
	}
	
	public String obtenerTipoCubo(){
		if (!lista.isEmpty()){
			return lista.get(0).getTipoCubo();
		}
		return "";
	}

}
