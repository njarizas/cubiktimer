package com.cubiktimer.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class Util {

	private SimpleDateFormat fechaHoraMysql = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private SimpleDateFormat fechaMysql = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat formatoFechaLarga = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
	private static final DecimalFormat DF = new DecimalFormat("0.00");
	private static Util util;

	private Util() {

	}

	public static Util getInstance() {
		if (util == null) {
			util = new Util();
		}
		return util;
	}

	/**
	 * Método que recibe una cadena con minutos y segundos y centesimas de segundo
	 * en formato "mm:ss.cc" y lo convierte en un double que representa sólo
	 * segundos
	 * 
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
	 * Método que recibe una cadena con minutos y segundos y centesimas de segundo
	 * en formato "mm:ss.cc" y lo convierte en un entero que representa sólo
	 * milesimas de segundo
	 * 
	 * @param string
	 * @return
	 */
	public static int calcularMilesimasDeSegundos(String string) {
		return (int) (calcularSegundos(string) * 1000);
	}

	/**
	 * Método que toma un Integer representando los milisegundos y devuelve una
	 * cadena con formato "mm:ss.ss" (minutos, segundos y centésimas de segundo)
	 * (no se tiene estipulado que las pruebas tomen mas de una hora)
	 * @param miliseg
	 * @return
	 */
	public static String darFormatoTiempo(Integer miliseg) {
		miliseg -= miliseg % 10;
		String retorno = "";
		double seg = miliseg / 1000d;
		int min;
		min = (int) Math.floor(seg) / 60;
		seg -= (min * 60);
		if (min < 10) {
			retorno += "0";
		}
		retorno += min + ":";
		if (seg < 10) {
			retorno += "0";
		}
		retorno += DF.format(seg);
		return retorno.replace(",", ".");
	}
	
	/**
	 * Metodo que genera una clave aleatoria de longitud 12 caracteres y solo puede tener caracteres alfanumericos 
	 * o los siguientes caracteres especiales: ! # $ % + - * ~ ^ @
	 * @return <code>String</code> la clave generada aleatoriamente
	 */
	public static String generarClaveAleatoria() {
		char[] caracteres;
		caracteres = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '#',
				'$', '%', '+', '-', '*', '~', '^', '@', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		StringBuilder pass = new StringBuilder("");
		for (int i = 0; i < 12; i++) {
			pass.append(caracteres[new Random().nextInt(caracteres.length)]);
		}
		return pass.toString();
	}

	public static String getRealPath() {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		return servletContext.getRealPath("/");
	}

	public static String getContextPath() {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		return servletContext.getContextPath();

	}

	public SimpleDateFormat getFechaHoraMysql() {
		return fechaHoraMysql;
	}

	public SimpleDateFormat getFechaMysql() {
		return fechaMysql;
	}

	public SimpleDateFormat getFormatoFechaLarga() {
		return formatoFechaLarga;
	}

	public static DecimalFormat getDf() {
		return DF;
	}

}
