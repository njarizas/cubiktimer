package com.cubiktimer.controlador.managedbeans.session;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.cubiktimer.controlador.facade.UsuarioFacade;
import com.cubiktimer.modelo.dao.CredencialDAO;
import com.cubiktimer.modelo.dao.UsuarioDAO;
import com.cubiktimer.modelo.dto.CredencialDTO;
import com.cubiktimer.modelo.dto.UsuarioDTO;
import com.cubiktimer.util.EmailSenderInterface;
import com.cubiktimer.util.EmailSenderService;
import com.cubiktimer.util.EncryptService;

/**
 * Bean que se utiliza para recuperar clave o correo electronico, notese que es
 * de vista puesto que las variables las inyecta a #{sesionManagedBean}
 * 
 * @author Nelson
 */
@ManagedBean
@ViewScoped
public class RecuperarUsuarioManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;

	private UsuarioDAO usuarioDAO;
	private CredencialDAO credencialDAO;
	private UsuarioFacade usuarioFacade;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;

	public RecuperarUsuarioManagedBean() {
		super();
		usuarioDAO = new UsuarioDAO();
		credencialDAO = new CredencialDAO();
		usuarioFacade = new UsuarioFacade();
	}

	public String recuperarClave() {
		List<UsuarioDTO> lu = usuarioDAO.consultarUsuarioPorCorreo(email.trim());
		String retorno = "";
		if (lu.isEmpty()) {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("ActualmenteNoExisteUnUsuarioConCorreo") + ": "
							+ email.trim() + ", "
							+ sesionManagedBean.getRecursos().getString("PorFavorIntenteRecordarElUsuario"));
			sesionManagedBean.getMensaje().setType("warning");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		} else {
			UsuarioDTO u = lu.get(0);
			EmailSenderInterface emailSender = new EmailSenderService();
			String nuevaClave = generarClaveAleatoria();
			if (emailSender.enviarMensajeDeRecuperacionDeClave(lu.get(0), nuevaClave)) {
				sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
				sesionManagedBean.getMensaje().setText(
						sesionManagedBean.getRecursos().getString("HemosEnviadoUnCorreoElectronicoALaDireccion") + ": "
								+ email.trim() + " "
								+ sesionManagedBean.getRecursos().getString("ConTuNuevaContraseña"));
				sesionManagedBean.getMensaje().setType("info");
				sesionManagedBean.getMensaje().setMensajePendiente(true);
				u.setClave(EncryptService.encriptarClave(nuevaClave));
				usuarioDAO.update(u);
			}
		}
		return retorno;
	}

	public String recuperarUsuario() {
		List<UsuarioDTO> lu = usuarioDAO.consultarUsuarioPorCorreo(email.trim());
		List<CredencialDTO> lc = credencialDAO.consultarCredencialPorCorreo(email.trim());
		String retorno = "";
		if (!lu.isEmpty()) {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("SuUsuarioActualEs") + ": " + email.trim());
			sesionManagedBean.getMensaje().setType("info");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		} else if (!lc.isEmpty()) {
			String correoActual = usuarioFacade.consultarCorreoActualPorCorreoAntiguo(email.trim());
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			sesionManagedBean.getMensaje().setText(
					sesionManagedBean.getRecursos().getString("SuUsuarioActualEs") + ": " + correoActual.trim());
			sesionManagedBean.getMensaje().setType("info");
			sesionManagedBean.getMensaje().setMensajePendiente(true);

		} else {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("NoExisteNingunUsuarioRelacionadoConElEmail")
							+ ": " + email.trim());
			sesionManagedBean.getMensaje().setType("info");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		}
		return retorno;
	}

	public String generarClaveAleatoria() {
		char[] caracteres;
		caracteres = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '#',
				'$', '%', '+', '-', '*', '~', '^', '@', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		StringBuilder pass = new StringBuilder("");
		for (int i = 0; i < 12; i++) {
			pass.append(caracteres[new Random().nextInt(caracteres.length + 1)]);
		}
		return pass.toString();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SesionManagedBean getSesionManagedBean() {
		return sesionManagedBean;
	}

	public void setSesionManagedBean(SesionManagedBean sesionManagedBean) {
		this.sesionManagedBean = sesionManagedBean;
	}

}