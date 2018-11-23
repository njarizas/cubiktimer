package com.cubiktimer.controlador.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.cubiktimer.controlador.managedbeans.session.ConfiguracionManagedBean;
import com.cubiktimer.controlador.managedbeans.session.SesionManagedBean;
import com.cubiktimer.modelo.dao.CredencialDAO;
import com.cubiktimer.modelo.dao.UsuarioDAO;
import com.cubiktimer.modelo.dto.CredencialDTO;
import com.cubiktimer.modelo.dto.UsuarioDTO;
import com.cubiktimer.util.EncryptService;

/**
 *
 * @author Nelson
 */
@ManagedBean(name = "editarInformacionCuentaManagedBean")
@ViewScoped
public class EditarInformacionCuentaManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(EditarInformacionCuentaManagedBean.class);

	private String claveAnterior;
	private String claveNueva;
	private String confirmarClaveNueva;
	private String correo;
	private String confirmarCorreoNuevo;
	private boolean verificado;

	private String mensaje;

	private UsuarioDTO usuario = new UsuarioDTO();

	@ManagedProperty(value = "#{configuracionManagedBean}")
	private ConfiguracionManagedBean configuracionManagedBean;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;

	CredencialDAO credencialDAO;
	UsuarioDAO usuarioDAO;

	public EditarInformacionCuentaManagedBean() {
		verificado = false;
		credencialDAO = new CredencialDAO();
		usuarioDAO = new UsuarioDAO();
	}

	public List<UsuarioDTO> listarUsuarios() {
		return usuarioDAO.consultarUsuarios();
	}

	public String editarCuenta() {
		usuarioDAO.merge(sesionManagedBean.getUsuarioLogueado());
		sesionManagedBean.getMensaje()
				.setTitle(sesionManagedBean.getRecursos().getString("ElCambioFueRealizadoExitosamente"));
		sesionManagedBean.getMensaje()
				.setText(sesionManagedBean.getRecursos().getString("LaInformacionDeLaCuentaHaSidoModificada"));
		sesionManagedBean.getMensaje().setType("success");
		sesionManagedBean.getMensaje().setMensajePendiente(true);
		return "";
	}

	public String verificar() {
		if (claveAnterior.equals(sesionManagedBean.getUsuarioLogueado().getClave())) {
			verificado = true;
		} else {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("LaClaveNoEsCorrecta"));
			sesionManagedBean.getMensaje().setType("warning");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		}
		return "";
	}

	public String cancelar() {
		verificado = false;
		return "";
	}

	public String cambiarClave() {
		if (claveNueva.equals("") || confirmarClaveNueva.equals("")) {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("NingunaDeLasContraseñasPuedeEstarVacia"));
			sesionManagedBean.getMensaje().setType("warning");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
			return "";
		}
		if (!claveNueva.equals(confirmarClaveNueva)) {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("LasClavesNoCoinciden"));
			sesionManagedBean.getMensaje().setType("warning");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
			return "";
		}
		if (sesionManagedBean.getUsuarioLogueado().getClave().equals(EncryptService.encriptarClave(claveNueva))) {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("LaClaveNuevaNoPuedeSerIgualALaActual"));
			sesionManagedBean.getMensaje().setType("warning");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
			return "";
		}
		insertarCredencial();
		sesionManagedBean.getUsuarioLogueado().setClave(claveNueva);
		usuarioDAO.merge(sesionManagedBean.getUsuarioLogueado());
		sesionManagedBean.getMensaje()
				.setTitle(sesionManagedBean.getRecursos().getString("ElCambioFueRealizadoExitosamente"));
		sesionManagedBean.getMensaje()
				.setText(sesionManagedBean.getRecursos().getString("LaContraseñaHaSidoCambiadaExitosamente"));
		sesionManagedBean.getMensaje().setType("success");
		sesionManagedBean.getMensaje().setMensajePendiente(true);
		return "";
	}

	public String cambiarCorreo() {
		if (sesionManagedBean.getUsuarioLogueado().getCorreo().equals(correo)) {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("ElCorreoNuevoNoPuedeSerIgualAlActual"));
			sesionManagedBean.getMensaje().setType("warning");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
			return "";
		}
		List<UsuarioDTO> l = usuarioDAO.consultarUsuarioPorCorreo(correo);
		if (!l.isEmpty()) {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("NoSePuedeCambiarElCorreo") + " " + correo + " "
							+ sesionManagedBean.getRecursos().getString("PorQueEstaSiendoUsadoPorAlguien"));
			sesionManagedBean.getMensaje().setType("warning");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
			return "";
		}
		List<CredencialDTO> lia = credencialDAO.consultarCredencialPorCorreo(correo);
		if (!lia.isEmpty()) {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("NoSePuedeCambiarElCorreo")
					+ " " + correo + " " + sesionManagedBean.getRecursos().getString("PorQueYaHaSidoUsadoPorAlguien"));
			sesionManagedBean.getMensaje().setType("warning");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
			return "";
		}
		List<UsuarioDTO> l2 = usuarioDAO.consultarUsuarioPorCorreo(correo);
		List<CredencialDTO> lia2 = credencialDAO.consultarCredencialPorCorreo(correo);
		if (!l2.isEmpty() || !lia2.isEmpty()) {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("NoSePuedeCambiarElCorreo") + " " + correo);
			sesionManagedBean.getMensaje().setType("warning");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
			return "";
		}
		insertarCredencial();
		sesionManagedBean.getUsuarioLogueado().setCorreo(correo);
		usuarioDAO.merge(sesionManagedBean.getUsuarioLogueado());
		sesionManagedBean.getMensaje()
				.setTitle(sesionManagedBean.getRecursos().getString("ElCambioFueRealizadoExitosamente"));
		sesionManagedBean.getMensaje()
				.setText(sesionManagedBean.getRecursos().getString("ElCorreoElectronicoHaSidoCambiadoExitosamente"));
		sesionManagedBean.getMensaje().setType("success");
		sesionManagedBean.getMensaje().setMensajePendiente(true);
		return "";
	}

	public UsuarioDTO buscarUsuario(String correo) {
		List<UsuarioDTO> l = usuarioDAO.consultarUsuarioPorCorreo(correo);
		if (l.size() == 1) {
			return l.get(0);
		}
		return null;
	}

	public void insertarCredencial() {
		CredencialDTO credencial = new CredencialDTO();
		credencial.setIdUsuario(sesionManagedBean.getUsuarioLogueado().getIdUsuario());
		credencial.setCorreo(sesionManagedBean.getUsuarioLogueado().getCorreo());
		credencial.setClave(sesionManagedBean.getUsuarioLogueado().getClave());
		credencial.setFechaFin(new Date());
		credencial.setEstado(1);
		Date fechaUltimoCambio = sesionManagedBean.getUsuarioLogueado().getFechaCreacion();
		Date f = credencialDAO.obtenerFechaUltimaCredencial(sesionManagedBean.getUsuarioLogueado().getIdUsuario());
		if (f != null) {
			fechaUltimoCambio = f;
		}
		credencial.setFechaInicio(fechaUltimoCambio);
		if (credencialDAO.create(credencial) > 0) {
			log.trace("Creo credencial " + credencial.getCorreo());
		}
	}

	public String getClaveAnterior() {
		return claveAnterior;
	}

	public void setClaveAnterior(String claveAnterior) {
		this.claveAnterior = EncryptService.encriptarClave(claveAnterior);
	}

	public String getClaveNueva() {
		return claveNueva;
	}

	public void setClaveNueva(String claveNueva) {
		this.claveNueva = EncryptService.encriptarClave(claveNueva);
	}

	public String getConfirmarCorreoNuevo() {
		return confirmarCorreoNuevo;
	}

	public void setConfirmarCorreoNuevo(String confirmarCorreoNuevo) {
		this.confirmarCorreoNuevo = confirmarCorreoNuevo;
	}

	public String getConfirmarClaveNueva() {
		return confirmarClaveNueva;
	}

	public void setConfirmarClaveNueva(String confirmarClaveNueva) {
		this.confirmarClaveNueva = EncryptService.encriptarClave(confirmarClaveNueva);
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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

	public ConfiguracionManagedBean getConfiguracionManagedBean() {
		if (this.configuracionManagedBean != null) {
			return configuracionManagedBean;
		} else {
			return new ConfiguracionManagedBean();
		}
	}

	public void setConfiguracionManagedBean(ConfiguracionManagedBean configuracionManagedBean) {
		this.configuracionManagedBean = configuracionManagedBean;
	}

	public boolean isVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
