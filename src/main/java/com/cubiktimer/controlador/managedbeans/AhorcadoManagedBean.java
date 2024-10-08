/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.controlador.managedbeans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cubiktimer.controlador.managedbeans.session.SesionManagedBean;
import com.cubiktimer.error.ExceptionHandler;
import com.cubiktimer.modelo.dao.AhorcadoDAO;
import com.cubiktimer.modelo.dto.AhorcadoDTO;
import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.Util;

/**
 *
 * @author nelsonjas
 */
@ManagedBean
@ViewScoped
public class AhorcadoManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(AhorcadoManagedBean.class);

	private Random random = new Random();

	private String palabra;
	private char[] palabraVisible;
	private char[] palabraOculta;
	private int intentosRestantes;
	private String letra;
	private boolean juegoEstaFinalizado = false;
	private List<String> letrasUsadas;

	private AhorcadoDTO dto;
	private AhorcadoDAO dao;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;

	public AhorcadoManagedBean() {
		reset();
		dto = new AhorcadoDTO();
		dao = new AhorcadoDAO();
	}

	@PostConstruct
	public void init() {
		if (sesionManagedBean.getUsuarioLogueado() != null) {
			this.dto.setIdUsuario(sesionManagedBean.getUsuarioLogueado().getIdUsuario());
		}
		this.dto.setIp(sesionManagedBean.getIpConsultante());
		log.debug("ip: " + this.dto.getIp());
	}

	public String reiniciar() {
		reset();
		return "";
	}

	public void reset() {
		letrasUsadas = new ArrayList<>();
		juegoEstaFinalizado = false;
		palabra = "dinosaurio";
		try {
			inicializarPalabra();
		} catch (Exception e) {
			log.warn(e.getMessage());
			ExceptionHandler.manejarExcepcionModerada(e);
		}
		letra = "";
		palabraVisible = palabra.toCharArray();
		palabraOculta = palabra.toCharArray();
		for (int i = 0; i < palabraVisible.length; i++) {
			palabraVisible[i] = '_';
		}
		intentosRestantes = 10;
	}

	public void comprobarLetra() {
		if (!juegoEstaFinalizado) {
			boolean encontroLetra = false;
			if (!letra.isEmpty()) {
				String l = String.valueOf(letra.toUpperCase().charAt(0));
				if (letrasUsadas.indexOf(l.toUpperCase()) < 0) {
					letrasUsadas.add(l.toUpperCase());
				}
				for (int i = 0; i < palabraOculta.length; i++) {
					if (letra.toUpperCase().charAt(0) == palabraOculta[i]
							|| letra.toLowerCase().charAt(0) == palabraOculta[i]) {
						palabraVisible[i] = palabraOculta[i];
						encontroLetra = true;
					}
				}
			}
			if (!encontroLetra) {
				intentosRestantes -= 1;
			}
			letra = "";
			if (sonPalabrasIguales()) {
				sesionManagedBean.getMensaje().setTitle("¡Ganaste!");
				sesionManagedBean.getMensaje().setText("La palabra era: " + this.palabra);
				sesionManagedBean.getMensaje().setType(Constantes.SUCCESS);
				sesionManagedBean.getMensaje().setMensajePendiente(true);
				juegoEstaFinalizado = true;
				// new Date(), palabra, false, letrasUsadas
				dto.setFecha(new Date());
				dto.setPalabra(this.palabra);
				dto.setLetrasUsadas(Arrays.toString(letrasUsadas.toArray()));
				dto.setIntentosSobrantes(intentosRestantes);
				dto.setGano(true);
				dao.create(dto);
			} else if (intentosRestantes == 0) {
				sesionManagedBean.getMensaje().setTitle("¡Perdiste!");
				sesionManagedBean.getMensaje().setText("La palabra era: " + this.palabra);
				sesionManagedBean.getMensaje().setType(Constantes.ERROR);
				sesionManagedBean.getMensaje().setMensajePendiente(true);
				juegoEstaFinalizado = true;
				dto.setFecha(new Date());
				dto.setPalabra(this.palabra);
				dto.setLetrasUsadas(Arrays.toString(letrasUsadas.toArray()));
				dto.setIntentosSobrantes(intentosRestantes);
				dto.setGano(false);
				dao.create(dto);
			}
		}
	}

	public boolean seHaUsadoLetra(String letra) {
		return this.letrasUsadas.indexOf(letra) >= 0;
	}

	public String obtenerPalabra() {
		StringBuilder r = new StringBuilder("");
		for (int i = 0; i < palabraVisible.length; i++) {
			r.append(" " + palabraVisible[i] + " ");
		}
		return r.toString();
	}

	public boolean sonPalabrasIguales() {
		for (int i = 0; i < palabraVisible.length; i++) {
			if (palabraVisible[i] != palabraOculta[i]) {
				return false;
			}
		}
		return true;
	}

	public void inicializarPalabra() throws IOException {
		String realPath = Util.getRealPath();
		log.trace("Real Path" + realPath);
		String cadena;
		int lineas = 0;
		int linea;
		String archivo = realPath + "resources/txt/palabras_ahorcado.txt";
		try (FileReader f = new FileReader(archivo);
				BufferedReader b = new BufferedReader(f)) {
			while ((cadena = b.readLine()) != null) {
				lineas++;
			}
		}
		linea = random.nextInt(lineas);
		try (FileReader a = new FileReader(archivo);
				BufferedReader c = new BufferedReader(a)) {
			lineas = 0;
			while ((cadena = c.readLine()) != null) {
				if (linea == lineas) {
					this.palabra = cadena;
				}
				lineas++;
			}
		}
		log.info("palabra seleccionada: " + this.palabra);

	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public char[] getPalabraVisible() {
		return palabraVisible;
	}

	public void setPalabraVisible(char[] palabraVisible) {
		this.palabraVisible = palabraVisible;
	}

	public char[] getPalabraOculta() {
		return palabraOculta;
	}

	public void setPalabraOculta(char[] palabraOculta) {
		this.palabraOculta = palabraOculta;
	}

	public int getIntentosRestantes() {
		return intentosRestantes;
	}

	public void setIntentosRestantes(int intentosRestantes) {
		this.intentosRestantes = intentosRestantes;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public boolean isJuegoEstaFinalizado() {
		return juegoEstaFinalizado;
	}

	public void setJuegoEstaFinalizado(boolean juegoEstaFinalizado) {
		this.juegoEstaFinalizado = juegoEstaFinalizado;
	}

	public List<String> getLetrasUsadas() {
		return letrasUsadas;
	}

	public void setLetrasUsadas(List<String> letrasUsadas) {
		this.letrasUsadas = letrasUsadas;
	}

	public SesionManagedBean getSesionManagedBean() {
		if (this.sesionManagedBean != null) {
			return this.sesionManagedBean;
		} else {
			return new SesionManagedBean();
		}
	}

	public void setSesionManagedBean(SesionManagedBean sesionManagedBean) {
		this.sesionManagedBean = sesionManagedBean;
	}

}
