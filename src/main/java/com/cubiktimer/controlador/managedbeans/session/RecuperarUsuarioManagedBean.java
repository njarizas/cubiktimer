package com.cubiktimer.controlador.managedbeans.session;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.cubiktimer.controlador.facade.UsuarioFacade;
import com.cubiktimer.modelo.dao.CredencialDAO;
import com.cubiktimer.modelo.dao.UsuarioDAO;
import com.cubiktimer.modelo.dto.CredencialDTO;
import com.cubiktimer.modelo.dto.UsuarioDTO;
import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.EmailSenderInterface;
import com.cubiktimer.util.EmailSenderService;
import com.cubiktimer.util.EncryptService;
import com.cubiktimer.util.Util;

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
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("ActualmenteNoExisteUnUsuarioConCorreo") + ": "
							+ email.trim() + ", "
							+ sesionManagedBean.getRecursos().getString("PorFavorIntenteRecordarElUsuario"));
			sesionManagedBean.getMensaje().setType(Constantes.WARNING);
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		} else {
			UsuarioDTO u = lu.get(0);
			EmailSenderInterface emailSender = new EmailSenderService();
			String nuevaClave = Util.generarClaveAleatoria();
			if (emailSender.enviarMensajeDeRecuperacionDeClave(lu.get(0), nuevaClave)) {
				sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
				sesionManagedBean.getMensaje().setText(
						sesionManagedBean.getRecursos().getString("HemosEnviadoUnCorreoElectronicoALaDireccion") + ": "
								+ email.trim() + " "
								+ sesionManagedBean.getRecursos().getString("ConTuNuevaContraseña"));
				sesionManagedBean.getMensaje().setType(Constantes.INFO);
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
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("SuUsuarioActualEs") + ": " + email.trim());
			sesionManagedBean.getMensaje().setType(Constantes.INFO);
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		} else if (!lc.isEmpty()) {
			String correoActual = usuarioFacade.consultarCorreoActualPorCorreoAntiguo(email.trim());
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
			sesionManagedBean.getMensaje().setText(
					sesionManagedBean.getRecursos().getString("SuUsuarioActualEs") + ": " + correoActual.trim());
			sesionManagedBean.getMensaje().setType(Constantes.INFO);
			sesionManagedBean.getMensaje().setMensajePendiente(true);

		} else {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("NoExisteNingunUsuarioRelacionadoConElEmail")
							+ ": " + email.trim());
			sesionManagedBean.getMensaje().setType(Constantes.INFO);
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		}
		return retorno;
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