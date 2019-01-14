package com.cubiktimer.controlador.managedbeans.session;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.cubiktimer.modelo.dao.CredencialDAO;
import com.cubiktimer.modelo.dao.UsuarioDAO;
import com.cubiktimer.modelo.dto.CredencialDTO;
import com.cubiktimer.modelo.dto.UsuarioDTO;
import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.EmailSenderInterface;
import com.cubiktimer.util.EmailSenderService;
import com.cubiktimer.util.EncryptService;

@ManagedBean
@ViewScoped
public class RegistroManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(RegistroManagedBean.class);

	private String clave;
	private String confirmarClave;

	private UsuarioDTO usuario;
	private UsuarioDAO usuarioDAO;
	private CredencialDAO credencialDAO;
	private EmailSenderInterface email;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;

	public RegistroManagedBean() {
		usuario = new UsuarioDTO();
		usuario.setEstado(2);
		usuarioDAO = new UsuarioDAO();
		credencialDAO = new CredencialDAO();
	}

	public String registrarUsuario() {
		if (validarusuario()) {
			usuario.setFechaCreacion(new Date());
			usuario.setFechaModificacion(new Date());
			if (persistirUsuario()) {
				sesionManagedBean.getMensaje()
						.setTitle(sesionManagedBean.getRecursos().getString("UsuarioRegistradoExitosamente"));
				sesionManagedBean.getMensaje()
						.setText(sesionManagedBean.getRecursos().getString("MensajeCuentaCreada"));
				sesionManagedBean.getMensaje().setType(Constantes.SUCCESS);
				sesionManagedBean.getMensaje().setMensajePendiente(true);
				email = new EmailSenderService();
				email.enviarMensajeActivacionCuenta(usuario);
				usuario = new UsuarioDTO();
				clave = "";
				confirmarClave = "";
				return "index";
			}
		}
		return "";
	}

	/**
	 * Método que se encarga de verificar que los datos de un usuario sean
	 * coherentes para realizar el registro en la base de datos
	 *
	 * @return <code>true</code> si los datos son válidos<br>
	 *         <code>false</code>si los datos no son válidos
	 */
	public boolean validarusuario() {
		if (clave.equals(confirmarClave)) {
			usuario.setClave(EncryptService.encriptarClave(clave));
		} else {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
			sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("LasClavesNoCoinciden"));
			sesionManagedBean.getMensaje().setType(Constantes.ERROR);
			sesionManagedBean.getMensaje().setMensajePendiente(true);
			return false;
		}
		List<UsuarioDTO> lu = usuarioDAO.consultarUsuarioPorCorreo(usuario.getCorreo().trim());
		List<CredencialDTO> lc = credencialDAO.consultarCredencialPorCorreo(usuario.getCorreo().trim());
		if (!lu.isEmpty() || !lc.isEmpty()) {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("NoSePuedeCrearUnUsuarioConCorreo") + " "
							+ usuario.getCorreo().trim());
			sesionManagedBean.getMensaje().setType(Constantes.WARNING);
			sesionManagedBean.getMensaje().setMensajePendiente(true);
			return false;
		}
		return true;
	}

	/**
	 * Método que se encarga de realizar la persistencia del usuario en la base de
	 * datos
	 * 
	 * @return <code>true</code> si se pudo guardar<br>
	 *         <code>false</code> si no se pudo guardar
	 */
	public boolean persistirUsuario() {
		try {
			List<UsuarioDTO> l = usuarioDAO.consultarUsuarioPorCorreoYEstado(usuario.getCorreo().trim(), 1);
			List<CredencialDTO> lia = credencialDAO.consultarCredencialPorCorreoYEstado(usuario.getCorreo().trim(), 1);
			if (!l.isEmpty()) {
				sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
				sesionManagedBean.getMensaje()
						.setText(sesionManagedBean.getRecursos().getString("YaExisteUnUsuarioRegistradoConCorreo")
								+ ": " + usuario.getCorreo());
				sesionManagedBean.getMensaje().setType(Constantes.WARNING);
				sesionManagedBean.getMensaje().setMensajePendiente(true);
				return false;
			}
			if (!lia.isEmpty()) {
				sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
				sesionManagedBean.getMensaje()
						.setText(sesionManagedBean.getRecursos().getString("NoSePuedeCrearUnUsuarioConCorreo") + ": "
								+ usuario.getCorreo() + " "
								+ sesionManagedBean.getRecursos().getString("PorQueYaHaSidoUsadoPorAlguien"));
				sesionManagedBean.getMensaje().setType(Constantes.WARNING);
				sesionManagedBean.getMensaje().setMensajePendiente(true);
				return false;
			} else {
				usuario.setIdUsuario(usuarioDAO.create(usuario));
				return true;
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("OcurrioUnErrorAlIntentarRegistrarElUsuario"));
			sesionManagedBean.getMensaje().setType(Constantes.ERROR);
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		}
		return false;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getConfirmarClave() {
		return confirmarClave;
	}

	public void setConfirmarClave(String confirmarClave) {
		this.confirmarClave = confirmarClave;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public SesionManagedBean getSesionManagedBean() {
		if (this.sesionManagedBean != null) {
			return this.sesionManagedBean;
		} else {
			return new SesionManagedBean();
		}
	}

	public void setSesionManagedBean(SesionManagedBean sesionManagedBean) {
		this.sesionManagedBean = sesionManagedBean;
	}

	public EmailSenderInterface getEmail() {
		return email;
	}

	public void setEmail(EmailSenderInterface email) {
		this.email = email;
	}

}