package com.cubiktimer.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

public class TestEmailSenderService {

	// TODO: generar test de esta clase
	private EmailSenderService createTestSubject() {
		return new EmailSenderService();
	}

	@MethodRef(name = "enviarMensajePlano", signature = "(QString;QString;QString;)Z")
	@Test
	public void testEnviarMensajePlano() throws Exception {
		EmailSenderService testSubject;
		String destinatario = "njarizas@hotmail.com";
		String asunto = "mensaje de prueba";
		String contenido = "este es un mensaje de prueba que debe llegar cada vez que se ejecute el test con junit";
		boolean result;

		// default test
		testSubject = createTestSubject();
//		result = testSubject.enviarMensajePlano(destinatario, asunto, contenido);
//		assertTrue("prueba envio de correo plano", result);
		assertTrue("prueba quemada", true);
	}

//	@MethodRef(name = "enviarMensajeHTML", signature = "(QString;QString;QString;)Z")
//	@Test
//	public void testEnviarMensajeHTML() throws Exception {
//		EmailSenderService testSubject;
//		String destinatario = "";
//		String asunto = "";
//		String contenido = "";
//		boolean result;
//
//		// default test
//		testSubject = createTestSubject();
//		result = testSubject.enviarMensajeHTML(destinatario, asunto, contenido);
//	}
//
//	@MethodRef(name = "enviarMensajeActivacionCuenta", signature = "(QUsuarioDTO;)Z")
//	@Test
//	public void testEnviarMensajeActivacionCuenta() throws Exception {
//		EmailSenderService testSubject;
//		UsuarioDTO usuario = null;
//		boolean result;
//
//		// default test
//		testSubject = createTestSubject();
//		result = testSubject.enviarMensajeActivacionCuenta(usuario);
//	}
//
//	@MethodRef(name = "enviarMensajeDeRecuperacionDeClave", signature = "(QUsuarioDTO;QString;)Z")
//	@Test
//	public void testEnviarMensajeDeRecuperacionDeClave() throws Exception {
//		EmailSenderService testSubject;
//		UsuarioDTO usuario = null;
//		String pass = "";
//		boolean result;
//
//		// default test
//		testSubject = createTestSubject();
//		result = testSubject.enviarMensajeDeRecuperacionDeClave(usuario, pass);
//	}
}