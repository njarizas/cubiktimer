package xyz.njas.modelo.rubik.estadisticas;

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
		String retorno="";
        for (int i=0; i<lista.size();i++) {
            if (i!=0){
                retorno+=",";
            }
            retorno+="'"+lista.get(i).getFecha()+"'";
        }
        return retorno;
	}
	
	public String obtenerDatosUTC(){
		String retorno="{"
				+ " name: '"+obtenerTipoCubo()+"',"
				+ " data: [";
        for (int i=0; i<lista.size();i++) {
            if (i!=0){
                retorno+=","
                		+ "";
            }
            retorno+="[Date.UTC("+lista.get(i).getFechaUTC()+"),"+lista.get(i).getPromedio()+"]";
        }
        retorno+=""
        		+ "]"
        		+ "}";
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
		if (!lista.isEmpty()){
			return lista.get(0).getTipoCubo();
		}
		return "";
	}

}
