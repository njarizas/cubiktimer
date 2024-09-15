/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.error;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cubiktimer.util.EmailSenderInterface;
import com.cubiktimer.util.EmailSenderService;
import com.cubiktimer.util.Propiedades;

public class ExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

	private ExceptionHandler() {

	}

	public static void manejarExcepcionLeve(Exception e) {
		log.warn(e.getMessage());
	}
	
	public static void manejarExcepcionModerada(Exception e) {
		log.error(ExceptionUtils.getStackTrace(e));
	}
	
	public static void manejarExcepcionGrave(Exception e) {
		Propiedades propiedades = Propiedades.getInstance();
		EmailSenderInterface emailSender = new EmailSenderService();
		log.error(ExceptionUtils.getStackTrace(e));
		emailSender.enviarMensajePlano(propiedades.obtenerPropiedad("correo.user"), ExceptionUtils.getMessage(e),
				ExceptionUtils.getStackTrace(e));
	}

}
