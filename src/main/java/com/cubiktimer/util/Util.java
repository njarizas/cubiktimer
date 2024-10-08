/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cubiktimer.error.ExceptionHandler;

public class Util implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(Util.class);

	private SimpleDateFormat fechaHoraMysql = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private SimpleDateFormat fechaMysql = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat formatoFechaCorta = new SimpleDateFormat("dd/MM/yyyy");
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
	 * en formato "mm:ss.cc" y lo convierte en un BigDecimal que representa sólo
	 * segundos
	 * 
	 * @param string
	 * @return
	 */
	public static BigDecimal calcularSegundos(String string) {
		if (string.length() == 8) {
			BigDecimal min;
			BigDecimal seg = BigDecimal.valueOf(Double.parseDouble(string.substring(3)));
			min = new BigDecimal(string.substring(0, 2));
			return min.multiply(BigDecimal.valueOf(60)).add(seg);
		}
		return new BigDecimal("0.0");
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
		return calcularSegundos(string).multiply(BigDecimal.valueOf(1000)).intValue();
	}

	/**
	 * Método que toma un Integer representando los milisegundos y devuelve una
	 * cadena con formato "mm:ss.ss" (minutos, segundos y centésimas de segundo) (no
	 * se tiene estipulado que las pruebas tomen mas de una hora)
	 * 
	 * @param miliseg
	 * @return
	 */
	public static String darFormatoTiempo(Integer miliseg) {
		miliseg -= miliseg % 10;
		String retorno = "";
		BigDecimal seg = BigDecimal.valueOf(miliseg).divide(BigDecimal.valueOf(1000));
		int min;
		min = (int) Math.floor(seg.doubleValue()) / 60;
		seg = seg.subtract(BigDecimal.valueOf(min * 60L));
		if (min < 10) {
			retorno += "0";
		}
		retorno += min + ":";
		if (seg.intValue() < 10) {
			retorno += "0";
		}
		retorno += DF.format(seg);
		return retorno.replace(",", ".");
	}

	/**
	 * Metodo que genera una clave aleatoria de longitud 12 caracteres y solo puede
	 * tener caracteres alfanumericos o los siguientes caracteres especiales: ! # %
	 * + - * ~ ^ @
	 * 
	 * @return <code>String</code> la clave generada aleatoriamente
	 */
	public static String generarClaveAleatoria() {
		return randomString(12);
	}

	/**
	 * Metodo que genera una cadena aleatoria de longitud 32 caracteres y solo puede
	 * tener caracteres alfanumericos o los siguientes caracteres especiales: ! # %
	 * + - * ~ ^ @
	 * 
	 * @return <code>String</code> la clave generada aleatoriamente
	 */
	public static String generarSal() {
		return randomString(32);
	}

	private static String randomString(int longitud) {
		char[] caracteres;
		caracteres = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '#',
				'%', '+', '-', '*', '~', '^', '@', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
				'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		StringBuilder pass = new StringBuilder("");
		for (int i = 0; i < longitud; i++) {
			pass.append(caracteres[new Random().nextInt(caracteres.length)]);
		}
		return pass.toString();
	}

	/**
	 * Método que recibe un algoritmo con notación WCA y lo traduce a la notación
	 * utilizada por twistysim.
	 * 
	 * @param secuenciaWCA
	 * @return
	 */
	public static String traducirSecuenciaWCA(int idTipoCubo, String secuenciaWCA) {
		String retorno;
		switch (idTipoCubo) {
		case 25:
			retorno = traducirSecuenciaWCAMegaminx(secuenciaWCA);
			break;
		case 24:
			retorno = traducirSecuenciaWCASkewb(secuenciaWCA);
			break;
		case 27:
			retorno = traducirSecuenciaWCASquare1(secuenciaWCA);
			break;
		default:
			retorno = traducirSecuenciaWCANxN(secuenciaWCA);
			break;
		}
		return retorno.trim();
	}

	public static String traducirSecuenciaWCASquare1(String secuencia) {
		secuencia = secuencia.replaceAll(" ", "");

		StringBuilder retorno = new StringBuilder("");
		List<String> lista;
		lista = Arrays.asList(secuencia.split("/"));
		for (String string : lista) {
			String[] temp = string.split(",");
			if (temp.length > 1) {
				String u = temp[0].replaceAll("\\(", "").replaceAll(" ", "");
				String d = temp[1].replaceAll("\\)", "").replaceAll(" ", "");
				try {
					int sup = Integer.parseInt(u);
					int inf = Integer.parseInt(d);
					retorno.append("/ ");
					if (sup != 0) {
						retorno.append("U");
						if (Math.abs(sup) > 1) {
							retorno.append(Math.abs(sup));
						}
						if (sup < 0) {
							retorno.append("'");
						}
						retorno.append(" ");
					}
					if (inf != 0) {
						retorno.append("D");
						if (Math.abs(inf) > 1) {
							retorno.append(Math.abs(inf));
						}
						if (inf < 0) {
							retorno.append("'");
						}
						retorno.append(" ");
					}
				} catch (Exception e) {
					ExceptionHandler.manejarExcepcionGrave(e);
				}
			} else {
				log.warn("Se encontró una inconsistencia en la mezcla square1: " + string);
			}
		}
		if (secuencia.endsWith("/")) {
			retorno.append("/");
		}
		return retorno.toString().replaceFirst("/", "");
	}

	public static String traducirSecuenciaWCANxN(String secuencia) {
		return secuencia.replaceAll("Bw", "b").replaceAll("Dw", "d").replaceAll("Fw", "f").replaceAll("Lw", "l")
				.replaceAll("Rw", "r").replaceAll("Uw", "u").replaceAll("\\[r]", "x").replaceAll("\\[r2]", "x2")
				.replaceAll("\\[r']", "x'").replaceAll("\\[l']", "x").replaceAll("\\[l2]", "x2")
				.replaceAll("\\[l]", "x'").replaceAll("\\[u]", "y").replaceAll("\\[u2]", "y2")
				.replaceAll("\\[u']", "y'").replaceAll("\\[d']", "y").replaceAll("\\[d2]", "y2")
				.replaceAll("\\[d]", "y'").replaceAll("\\[f]", "z").replaceAll("\\[f2]", "z2")
				.replaceAll("\\[f']", "z'").replaceAll("\\[b']", "z").replaceAll("\\[b2]", "z2")
				.replaceAll("\\[b]", "z'");
	}

	public static String traducirSecuenciaWCAMegaminx(String secuencia) {
		return secuencia.replaceAll("R[\\+][\\+]", "br2").replaceAll("R--", "br2'").replaceAll("D[\\+][\\+]", "d2")
				.replaceAll("D--", "d2'");
	}

	public static String traducirSecuenciaWCASkewb(String secuencia) {
		return secuencia.replaceAll("U'", "b'").replaceAll("U", "b").replaceAll("R'", "A").replaceAll("R", "R'")
				.replaceAll("A", "R").replaceAll("B'", "C").replaceAll("B", "B'").replaceAll("C", "B");
	}

	public static String obtenerLetraDeCaraPorColor(String color) {
		switch (color) {
		case Constantes.COLOR_CARA_B:
			return Constantes.LETRA_CARA_B;
		case Constantes.COLOR_CARA_D:
			return Constantes.LETRA_CARA_D;
		case Constantes.COLOR_CARA_F:
			return Constantes.LETRA_CARA_F;
		case Constantes.COLOR_CARA_L:
			return Constantes.LETRA_CARA_L;
		case Constantes.COLOR_CARA_R:
			return Constantes.LETRA_CARA_R;
		case Constantes.COLOR_CARA_U:
			return Constantes.LETRA_CARA_U;
		default:
			return null;
		}
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

	public static Integer getInteger(String numero) throws Exception {
		return Integer.parseInt(numero);
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

	public SimpleDateFormat getFormatoFechaCorta() {
		return formatoFechaCorta;
	}

	public static DecimalFormat getDf() {
		return DF;
	}
}