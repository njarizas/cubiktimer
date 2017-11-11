package xyz.njas.util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import xyz.njas.dto.UsuarioDTO;

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
        this.emailRemitente = "es.sigolf@gmail.com";
        this.passRemitente = "Sigolf954738";
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
    public boolean enviarMensajeDeActivacionDeCuenta(UsuarioDTO usuario) {
        this.emailDestinatario = usuario.getCorreo().trim().toLowerCase();
        StringBuilder stringBuilder = new StringBuilder(usuario.getClave());
        String nekot = stringBuilder.reverse().toString();
        String msg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                + "        <title>Gracias por regitrarte en Sigolf</title>\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n"
                + "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"></link>\n"
                + "<style>\n"
                + "            a:hover{\n"
                + "                color: #fff;\n"
                + "                background-color: #286090;\n"
                + "                border-color: #204d74;\n"
                + "            }\n"
                + "        </style>"
                + "    </head>\n"
                + "    <body>\n"
                + "         <table align=\"center\" style=\"border: solid #cccccc; border-radius: 8px; width: 70%; min-width: 480px; margin-top: 20px;\">\n"
                + "            <tr>\n"
                + "                <td align=\"center\" bgcolor=\"#00b953\" style=\" font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;\">\n"
                + "                    <img src=\"http://on-sigolf.rhcloud.com/imagenes/logo.png\" alt=\"logo\" height=\"80\" align=\"left\"/>\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">\n"
                + "                    <table width=\"100%\" style=\"text-align: center;\">\n"
                + "                        <tr>\n"
                + "                            <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 24px;\">\n"
                + "                                <b>Gracias " + usuario.getNombres() + " por registrarte en www.sigolf.com.co!</b>\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                        <tr>\n"
                + "                            <td style=\"padding: 10px 0 0 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px;\">\n"
                + "                                Por favor haz clic en en siguiente boton para activar tu cuenta:<br/><br/>\n"
                + "                                <a style=\"color: #fff; background-color: #337ab7; border-color: #2e6da4; border-radius: 5px; padding: 10px; text-decoration:none; margin-bottom: 10px;\" href=\"http://www.sigolf.com.co:8080/golf/ActivarCuenta?u=" + usuario.getIdUsuario() + "&t=" + nekot + "\">Activar cuenta</a>\n"
                + "                                <br/><br/>\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                        <tr>\n"
                + "                            <td style=\"padding: 10px 0 0 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px;\">\n"
                + "                                Si luego de hacer clic no se abre una nueva pagina por favor copia y pega esta URL en tu navegador:<br></br>\n"
                + "                                <strong>http://www.sigolf.com.co:8080/golf/ActivarCuenta?u=" + usuario.getIdUsuario() + "&t=" + nekot + "</strong>\n"
                + "                                <hr></hr>\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                        <tr>\n"
                + "                            <td style=\"padding:5px 0 0 0; color: #153643; font-family: Arial, sans-serif; font-size: 12px; line-height: 20px;\">\n"
                + "                                Si no te registraste en sigolf por favor haz caso omiso a este mensaje.\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                    </table>\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "        </table>\n"
                + "    </body>\n"
                + "</html>";
        try {
            init();
            mimeMessage.setSubject("Activacion de tu cuenta en sigolf");
            //mimeMessage.setText(contenido);
            mimeMessage.setContent(msg, "text/html");

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
    public boolean enviarMensajeDeRecuperacionDeClave(UsuarioDTO usuario, String pass) {
        this.emailDestinatario = usuario.getCorreo();
        String msg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                + "        <title>Gracias por tus mensajes</title>\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n"
                + "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"></link>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <table align=\"center\" style=\"border: solid #cccccc; border-radius: 8px; width: 70%; min-width: 480px; margin-top: 20px;\">\n"
                + "            <tr>\n"
                + "                <td align=\"center\" bgcolor=\"#00b953\" style=\" font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;\">\n"
                + "                    <img src=\"http://on-sigolf.rhcloud.com/imagenes/logo.png\" alt=\"logo\" height=\"80\" align=\"left\"/>\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">\n"
                + "                    <table width=\"100%\">\n"
                + "                        <tr>\n"
                + "                            <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 24px;\">\n"
                + "                                " + usuario.getNombres() + " Se ha restablecido tu clave de acceso a sigolf, tu nueva clave es:\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                        <tr>\n"
                + "                            <td style=\"padding: 25px 0 0 0; color: #153643; font-family: Arial, sans-serif; font-size: 18px; line-height: 20px;\">\n"
                + "                                <h3>" + pass + "</h3>\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                    </table>\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "        </table>\n"
                + "    </body>\n"
                + "</html>";
        try {
            init();
            mimeMessage.setSubject("Tu contraseña en sigolf ha sido restablecida");
            //mimeMessage.setText(contenido);
            mimeMessage.setContent(msg, "text/html");

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
