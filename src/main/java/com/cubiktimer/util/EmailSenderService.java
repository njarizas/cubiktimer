package com.cubiktimer.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.cubiktimer.modelo.dao.ParametroDAO;
import com.cubiktimer.modelo.dto.UsuarioDTO;

/**
 *
 * @author Nelson
 */
public class EmailSenderService implements EmailSenderInterface {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(EmailSenderService.class);
	public static final String HOST_EMAIL_GMAIL = "smtp.gmail.com";

	private String emailRemitente;
	private String passRemitente;
	private String emailDestinatario;

	private transient Session session;
	private transient MimeMessage mimeMessage;

	public EmailSenderService() {
		Propiedades.configurarPropiedades("C:\\cubiktimer\\conexion.properties");
		Propiedades propiedades = Propiedades.getInstance();
		this.emailRemitente = propiedades.obtenerPropiedad("correo.user");
		this.passRemitente = propiedades.obtenerPropiedad("correo.password");
	}

	private void init() {
		try {
			Properties propiedades = new Properties();
			propiedades.setProperty("mail.smtp.host", HOST_EMAIL_GMAIL);
			propiedades.setProperty("mail.smtp.starttls.enable", "true");
			propiedades.setProperty("mail.smtp.port", "25");// 587
			propiedades.setProperty("mail.smtp.ssl.trust", HOST_EMAIL_GMAIL);
			propiedades.setProperty("mail.smtp.user", this.emailRemitente);
			propiedades.setProperty("mail.smtp.auth", "true");

			session = Session.getDefaultInstance(propiedades);
			mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(emailRemitente));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDestinatario));
		} catch (MessagingException ex) {
			log.warn(ex.getMessage());
		}
	}

	@Override
	public boolean enviarMensajePlano(String destinatario, String asunto, String contenido) {
		this.emailDestinatario = destinatario;
		try {
			init();
			mimeMessage.setSubject(asunto);
			mimeMessage.setText(contenido);
			Transport transport = session.getTransport("smtp");
			transport.connect(emailRemitente, passRemitente);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			return true;
		} catch (MessagingException ex) {
			log.warn(ex.getMessage());
			return false;
		}
	}

	@Override
	public boolean enviarMensajeHTML(String destinatario, String asunto, String contenido) {
		this.emailDestinatario = destinatario;
		try {
			init();
			mimeMessage.setSubject(asunto);
			mimeMessage.setContent(contenido, "text/html");
			Transport transport = session.getTransport("smtp");
			transport.connect(emailRemitente, passRemitente);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			return true;
		} catch (MessagingException ex) {
			log.warn(ex.getMessage());
			return false;
		}
	}

	private String obtenerRutaPlantillasHTML() {
		return Util.getRealPath() + "WEB-INF/plantillasHTML/";
	}

	private String aplicarPlantilla(String plantilla, Map<String, String> map) {
		for (Entry<String, String> entry : map.entrySet()) {
			plantilla = plantilla.replaceAll(entry.getKey(), entry.getValue());
		}
		return plantilla;
	}

	public boolean enviarMensajeActivacionCuenta(UsuarioDTO usuario) {
		ParametroDAO dao = new ParametroDAO();
		try {
			this.emailDestinatario = usuario.getCorreo().trim().toLowerCase();
			StringBuilder stringBuilder = new StringBuilder(usuario.getClave());
			String nekot = stringBuilder.reverse().toString();
			StringBuilder msg = new StringBuilder("");
			String cadena = "";
			String ruta = obtenerRutaPlantillasHTML();
			try (FileReader f = new FileReader(ruta + "mensaje-activacion-cuenta.html");
					BufferedReader b = new BufferedReader(f)) {
				while ((cadena = b.readLine()) != null) {
					msg.append(cadena);
				}
			}
			String mensaje = msg.toString();
			Map<String, String> map = new HashMap<>();
			map.put("~:nombreUsuario~", usuario.getNombres());
			map.put("~:idUsuario~", usuario.getIdUsuario().toString());
			map.put("~:token~", nekot);
			map.put("~:host_cubiktimer~", dao.obtenerValorParametro("host_cubiktimer"));
			mensaje = aplicarPlantilla(mensaje, map);

			return enviarMensajeHTML(this.emailDestinatario, "Activacion de tu cuenta en www.cubiktimer.com", mensaje);
		} catch (Exception e) {
			log.warn(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean enviarMensajeDeRecuperacionDeClave(UsuarioDTO usuario, String pass) {
		try {
			this.emailDestinatario = usuario.getCorreo().trim().toLowerCase();
			StringBuilder msg = new StringBuilder("");
			String cadena = "";
			String ruta = obtenerRutaPlantillasHTML();
			try (FileReader f = new FileReader(ruta + "mensaje-recuperacion-clave.html");
					BufferedReader b = new BufferedReader(f)) {
				while ((cadena = b.readLine()) != null) {
					msg.append(cadena);
				}
			}
			String mensaje = msg.toString();
			Map<String, String> map = new HashMap<>();
			map.put("~:nombreUsuario~", usuario.getNombres());
			map.put("~:clave~", pass);
			mensaje = aplicarPlantilla(mensaje, map);

			return enviarMensajeHTML(this.emailDestinatario,
					"Tu clave de acceso a www.cubiktimer.com ha sido restablecida", mensaje);
		} catch (Exception e) {
			log.warn(e.getMessage());
			return false;
		}
	}

	public String getEmailRemitente() {
		return emailRemitente;
	}

	public void setEmailRemitente(String emailRemitente) {
		this.emailRemitente = emailRemitente;
	}

	public String getPassRemitente() {
		return passRemitente;
	}

	public void setPassRemitente(String passRemitente) {
		this.passRemitente = passRemitente;
	}

	public String getEmailDestinatario() {
		return emailDestinatario;
	}

	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

}
