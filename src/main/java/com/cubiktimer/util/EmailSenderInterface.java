/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.util;

import java.io.Serializable;

import com.cubiktimer.modelo.dto.UsuarioDTO;

/**
 *
 * @author Nelson
 */
public interface EmailSenderInterface extends Serializable {

	public boolean enviarMensajePlano(String destinatario, String asunto, String contenido);

	public boolean enviarMensajeHTML(String destinatario, String asunto, String contenido);

	public boolean enviarMensajeActivacionCuenta(UsuarioDTO usuario);

	public boolean enviarMensajeDeRecuperacionDeClave(UsuarioDTO usuario, String pass);
}
