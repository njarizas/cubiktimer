package com.cubiktimer.error;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.cubiktimer.util.EmailSenderInterface;
import com.cubiktimer.util.EmailSenderService;
import com.cubiktimer.util.Propiedades;

public class ExceptionHandler {
	
	private static final Logger log = Logger.getLogger(ExceptionHandler.class);
	private static Propiedades propiedades = Propiedades.getInstance();

	private ExceptionHandler() {

	}

	public static void manejarExcepcionLeve(Exception e) {
		System.out.println("paso leve");
		log.warn(e.getMessage());
	}
	
	public static void manejarExcepcionModerada(Exception e) {
		System.out.println("paso moderada");
		log.error(ExceptionUtils.getStackTrace(e));
	}
	
	public static void manejarExcepcionGrave(Exception e) {
		System.out.println("paso grave: " + ExceptionUtils.getStackTrace(e));
		EmailSenderInterface emailSender = new EmailSenderService();
		log.error(ExceptionUtils.getStackTrace(e));
		emailSender.enviarMensajePlano(propiedades.obtenerPropiedad("correo.user"), ExceptionUtils.getMessage(e),
				ExceptionUtils.getStackTrace(e));
	}

}
