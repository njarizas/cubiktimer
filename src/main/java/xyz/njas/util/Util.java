package xyz.njas.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class Util {

	public SimpleDateFormat FECHA_HORA_MYSQL = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public SimpleDateFormat FECHA_MYSQL = new SimpleDateFormat("yyyy-MM-dd");
	public SimpleDateFormat FORMATO_FECHA_LARGA = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy",new Locale( "es" , "ES" ));  
	public static final DecimalFormat DF =new DecimalFormat("0.00");
	private static Util util;

	private Util(){
		
	}
	
	public static Util getInstance() {
		if (util == null) {
			util = new Util();
		}
		return util;
	}
	
	/**
	 * Método que recibe una cadena con minutos y segundos y centesimas de segundo en formato "mm:ss.cc"
	 * y lo convierte en un double que representa sólo segundos
	 * @param string
	 * @return 
	 */
	public static double calcularSegundos(String string) {
		if (string.length() == 8) {
			int min;
			double seg;
			min = Integer.parseInt(string.substring(0, 2));
			seg = Double.parseDouble(string.substring(3));
			return (min * 60) + seg;
		}
		return 0d;
	}

	/**
	 * Método que recibe una cadena con minutos y segundos y centesimas de segundo en formato "mm:ss.cc"
	 * y lo convierte en un entero que representa sólo milesimas de segundo
	 * @param string
	 * @return 
	 */
	public static int calcularMilesimasDeSegundos(String string) {
		return (int)(calcularSegundos(string)*1000);
	}

	/**
	 * Método que toma un Integer representando los milisegundos y devuelve una cadena con formato 
	 * "mm:ss.ss" (minutos, segundos y centésimas de segundo)
	 * @param miliseg
	 * @return 
	 */
	public static String darFormatoTiempo(Integer miliseg) {
		miliseg-=miliseg%10;
		String retorno = "";
		double seg=miliseg/1000d;
		int min;
		min=(int)Math.floor(seg)/60;
		seg-=(min*60);
		if (min<10){
			retorno +="0";
		}
		retorno+=min+":";
		if (seg<10){
			retorno+="0";
		}
		retorno+=DF.format(seg);
		return retorno.replace(",", ".");
	}
	
	public static String getRealPath() {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String realPath = (String) servletContext.getRealPath("/");
		return realPath;
	}
	
	public static String getContextPath() {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String contextPath = (String) servletContext.getContextPath();
		return contextPath;
	}

}
