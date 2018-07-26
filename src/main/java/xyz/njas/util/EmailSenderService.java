package xyz.njas.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import xyz.njas.modelo.dto.UsuarioDTO;

/**
 *
 * @author Nelson
 */
public class EmailSenderService implements EmailSenderInterface {

	public final static String HOST_EMAIL_GMAIL = "smtp.gmail.com";

	private String emailRemitente;
	private String passRemitente;
	private String emailDestinatario;

	private Session session;
	private MimeMessage mimeMessage;

	public EmailSenderService() {
		this.emailRemitente = "cubiktimer@gmail.com";
		this.passRemitente = "CubikTimer2018";
	}

	private void init() {
		try {
			Properties propiedades = new Properties();
			propiedades.setProperty("mail.smtp.host", HOST_EMAIL_GMAIL);
			propiedades.setProperty("mail.smtp.starttls.enable", "true");
			propiedades.setProperty("mail.smtp.port", "25");//587
			propiedades.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
			propiedades.setProperty("mail.smtp.user", this.emailRemitente);
			propiedades.setProperty("mail.smtp.auth", "true");

			session = Session.getDefaultInstance(propiedades);
			mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(emailRemitente));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDestinatario));
		} catch (MessagingException ex) {
			Logger.getLogger(EmailSenderService.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public boolean enviarMensajePlano(String destinatario, String asunto, String contenido) {
		this.emailDestinatario = destinatario;
		try {
			init();
			mimeMessage.setSubject(asunto);
			mimeMessage.setText(contenido);
			//mimeMessage.setContent(contenido, "text/html");
			Transport transport = session.getTransport("smtp");
			transport.connect(emailRemitente, passRemitente);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			return true;
		} catch (MessagingException ex) {
			Logger.getLogger(EmailSenderService.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	@Override
	public boolean enviarMensajeHTML(String destinatario, String asunto, String contenido) {
		this.emailDestinatario = destinatario;
		try {
			init();
			mimeMessage.setSubject(asunto);
			//mimeMessage.setText(contenido);
			mimeMessage.setContent(contenido, "text/html");
			Transport transport = session.getTransport("smtp");
			transport.connect(emailRemitente, passRemitente);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			return true;
		} catch (MessagingException ex) {
			Logger.getLogger(EmailSenderService.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	private String obtenerRutaPlantillasHTML() {
		String realPath = Util.getRealPath();
		String ruta = realPath+"WEB-INF/plantillasHTML/";
		return ruta;
	}

	private String aplicarPlantilla(String plantilla, Map<String, String> map) {
		for (Entry<String, String> entry : map.entrySet()) {
			plantilla = plantilla.replaceAll(entry.getKey(), entry.getValue());
		}
		return plantilla;
	}

	public boolean enviarMensajeActivacionCuenta(UsuarioDTO usuario) {
		try {
			this.emailDestinatario = usuario.getCorreo().trim().toLowerCase();
			StringBuilder stringBuilder = new StringBuilder(usuario.getClave());
			String nekot = stringBuilder.reverse().toString();
			StringBuffer msg=new StringBuffer("");
			String cadena="";
			String ruta = obtenerRutaPlantillasHTML();
			FileReader f = new FileReader(ruta+"mensaje-activacion-cuenta.html");
			BufferedReader b = new BufferedReader(f);
			while((cadena = b.readLine())!=null) {
				msg.append(cadena);
			}
			String mensaje = msg.toString();
			Map<String,String> map = new HashMap<String, String>();
			map.put("~:nombreUsuario~", usuario.getNombres());
			map.put("~:idUsuario~", usuario.getIdUsuario().toString());
			map.put("~:token~", nekot);
			mensaje = aplicarPlantilla(mensaje, map);
			b.close();
			return enviarMensajeHTML(this.emailDestinatario, "Activacion de tu cuenta en www.cubiktimer.com", mensaje);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean enviarMensajeDeRecuperacionDeClave(UsuarioDTO usuario, String pass) {
		try {
			this.emailDestinatario = usuario.getCorreo().trim().toLowerCase();
			StringBuffer msg=new StringBuffer("");
			String cadena="";
			String ruta = obtenerRutaPlantillasHTML();
			FileReader f = new FileReader(ruta+"mensaje-recuperacion-clave.html");
			BufferedReader b = new BufferedReader(f);
			while((cadena = b.readLine())!=null) {
				msg.append(cadena);
			}
			String mensaje = msg.toString();
			Map<String,String> map = new HashMap<String, String>();
			map.put("~:nombreUsuario~", usuario.getNombres());
			map.put("~:clave~", pass);
			mensaje = aplicarPlantilla(mensaje, map);
			b.close();
			return enviarMensajeHTML(this.emailDestinatario, "Tu clave de acceso a www.cubiktimer.com ha sido restablecida", mensaje);
		} catch (Exception e) {
			e.printStackTrace();
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
