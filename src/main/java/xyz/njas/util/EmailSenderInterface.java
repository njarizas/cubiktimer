package xyz.njas.util;

import xyz.njas.modelo.dto.UsuarioDTO;

/**
 *
 * @author Nelson
 */
public interface EmailSenderInterface {

	public boolean enviarMensajePlano(String destinatario, String asunto, String contenido);

	public boolean enviarMensajeHTML(String destinatario, String asunto, String contenido);

	public boolean enviarMensajeActivacionCuenta(UsuarioDTO usuario);

	public boolean enviarMensajeDeRecuperacionDeClave(UsuarioDTO usuario, String pass);
}
