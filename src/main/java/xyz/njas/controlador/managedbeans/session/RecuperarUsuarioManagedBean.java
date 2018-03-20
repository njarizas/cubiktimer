package xyz.njas.controlador.managedbeans.session;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import xyz.njas.controlador.facade.UsuarioFacade;
import xyz.njas.modelo.dao.CredencialDAO;
import xyz.njas.modelo.dao.UsuarioDAO;
import xyz.njas.modelo.dto.CredencialDTO;
import xyz.njas.modelo.dto.UsuarioDTO;

/**
 * Bean que se utiliza para recuperar clave o correo electronico, notese que es de vista puesto que las variables las inyecta a #{sesionManagedBean}
 * @author Nelson
 */
@ManagedBean
@ViewScoped
public class RecuperarUsuarioManagedBean {

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
		List<UsuarioDTO> lu=usuarioDAO.consultarUsuarioPorCorreo(email.trim());
		String retorno = "";
		if (lu.isEmpty()){
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			//TODO internacionalizar mensaje
			sesionManagedBean.getMensaje().setText("Actualmente no existe un usuario con correo: " + email.trim()
			+ ", por favor intente recordar el usuario");
			sesionManagedBean.getMensaje().setType("warning");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		} else {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			//TODO internacionalizar mensaje
			sesionManagedBean.getMensaje().setText("Hemos enviado un correo electrónico a la dirección: "+email.trim()+" con tu nueva contraseña.");
			sesionManagedBean.getMensaje().setType("info");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		}
		return retorno;
	}
	
	public String recuperarUsuario() {
		List<UsuarioDTO> lu=usuarioDAO.consultarUsuarioPorCorreo(email.trim());
		List<CredencialDTO> lc=credencialDAO.consultarCredencialPorCorreo(email.trim());
		String retorno = "";
		if (!lu.isEmpty()){
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			//TODO internacionalizar mensaje
			sesionManagedBean.getMensaje().setText("Su usuario actual es: "+email.trim());
			sesionManagedBean.getMensaje().setType("info");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		} else if (!lc.isEmpty()){
			String correoActual = usuarioFacade.consultarCorreoActualPorCorreoAntiguo(email.trim());
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			//TODO internacionalizar mensaje
			sesionManagedBean.getMensaje().setText("Su usuario actual es: "+correoActual.trim());
			sesionManagedBean.getMensaje().setType("info");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
			
		} else {
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			//TODO internacionalizar mensaje
			sesionManagedBean.getMensaje().setText("No existe ningún usuario relacionado con el email: "+email.trim());
			sesionManagedBean.getMensaje().setType("info");
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