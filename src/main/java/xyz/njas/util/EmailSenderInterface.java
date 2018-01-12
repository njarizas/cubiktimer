/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.njas.util;

import xyz.njas.dto.UsuarioDTO;

/**
 *
 * @author Nelson
 */
public interface EmailSenderInterface {

    public boolean enviarMensajePlano(String destinatario, String asunto, String contenido);

    public boolean enviarMensajeDeActivacionDeCuenta(UsuarioDTO usuario);

    public boolean enviarMensajeDeRecuperacionDeClave(UsuarioDTO usuario, String pass);
}