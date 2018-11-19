package xyz.njas.controlador.managedbeans.session;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import xyz.njas.controlador.facade.ConfiguracionFacade;
import xyz.njas.modelo.dao.CredencialDAO;
import xyz.njas.modelo.dao.PermisosDAO;
import xyz.njas.modelo.dao.RolDAO;
import xyz.njas.modelo.dao.UsuarioDAO;
import xyz.njas.modelo.dto.ConfiguracionDTO;
import xyz.njas.modelo.dto.CredencialDTO;
import xyz.njas.modelo.dto.PermisoDTO;
import xyz.njas.modelo.dto.RolDTO;
import xyz.njas.modelo.dto.UsuarioDTO;
import xyz.njas.util.EncryptService;
import xyz.njas.util.Util;

/**
 * Bean que se utiliza para iniciar sesion, notese que es de vista puesto que las variables las inyecta a #{sesionManagedBean}
 * @author Nelson
 */
@ManagedBean
@ViewScoped
public class IniciarSesionManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String login;
	private String pass;

	private UsuarioDAO usuarioDAO;
	private CredencialDAO credencialDAO;
	private PermisosDAO PermisosDAO;
	private RolDAO rolDAO;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;

	ConfiguracionFacade configuracionFacade;

	public IniciarSesionManagedBean() {
		super();
		usuarioDAO = new UsuarioDAO();
		credencialDAO = new CredencialDAO();
		PermisosDAO = new PermisosDAO();
		rolDAO = new RolDAO();
		configuracionFacade = new ConfiguracionFacade();
	}

	/**
	 * Método que se encarga de iniciar sesión haciendo unas validaciones
	 * previamente
	 * @return
	 */
	public String iniciarSesion() {
		UsuarioDTO u = buscarUsuario(login);
		if (u != null) { // si existe un usuario con el correo proporcionado
			if (login.equals(u.getCorreo()) && pass.equals(u.getClave())) {//se comprueba que la clave y el correo correspondan
				List<RolDTO> listaRoles = rolDAO.consultarRolesPorIdUsuario(u.getIdUsuario());
				for (RolDTO rolDTO : listaRoles) {
					System.out.println(rolDTO);
				}
				List<PermisoDTO> listaPermisos = PermisosDAO.consultarPermisosPorIdUsuario(u.getIdUsuario());
				if (listaRoles.isEmpty()) {
					sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
					sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("UsuarioNoTieneNingunRol"));
					sesionManagedBean.getMensaje().setType("warning");
					sesionManagedBean.getMensaje().setMensajePendiente(true);
					return "";
				}
				if (u.getEstado() == 1) {
					sesionManagedBean.setUsuarioLogueado(u);
					sesionManagedBean.setListaRoles(listaRoles);
					sesionManagedBean.setRolActual(sesionManagedBean.getListaRoles().get(0));
					sesionManagedBean.setListaPermisos(listaPermisos);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idUsuario", u.getIdUsuario());
					ConfiguracionDTO cDto = configuracionFacade.obtenerIdiomaPreferidoPorIdUsuario(u.getIdUsuario());
					if (cDto!=null){
						String idioma = cDto.getValorTexto();
						if (idioma != null){
							sesionManagedBean.setIdioma(idioma);
							Locale locale=new Locale(idioma);
							sesionManagedBean.setLocale(locale);
							FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
						}
					}
					ConfiguracionDTO cDto2 = configuracionFacade.obtenerPaginaInicialPorIdUsuario(u.getIdUsuario());
					if (cDto2 != null){
						String paginaInicio = cDto2.getValorTexto();
						return "/"+paginaInicio+"?faces-redirect=true";
					}
					return "/rubik?faces-redirect=true";
				}
				if (u.getEstado() == 2) {
					sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
					sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("MensajeVerificarCuenta"));
					sesionManagedBean.getMensaje().setType("warning");
					sesionManagedBean.getMensaje().setMensajePendiente(true);
					return "";
				}
			}//la clave y correo no corresponden , entonces se busca en las credenciales
			if (credencialDAO.consultarCredencialPorCorreoClaveYEstado(login, pass, 1).size() > 0) {//existe
				List<CredencialDTO> listaCredenciales = credencialDAO.consultarCredencialPorCorreoClaveYEstado(login, pass, 1);
				Date fechaMod = listaCredenciales.get(0).getFechaFin();
				Util util = Util.getInstance();
				sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
				sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("LaContraseñaFueModificadaEl") + ": " + util.FORMATO_FECHA_LARGA.format(fechaMod));
				sesionManagedBean.getMensaje().setType("warning");
				sesionManagedBean.getMensaje().setMensajePendiente(true);
			} else {//No existe
				sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
				sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("LaContraseñaIngresadaNoEsCorrecta"));
				sesionManagedBean.getMensaje().setType("warning");
				sesionManagedBean.getMensaje().setMensajePendiente(true);
			}
		} //no existe un usuario con el correo proporcionado: se busca si existe un usuario antiguo en las credenciales (el usuario cambió el correo)
		else if (existeUsuarioAntiguo(login)) { //Si existe
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("ExisteUnaCuentaAsociadaAlCorreo") + ": " + login + ", " + sesionManagedBean.getRecursos().getString("SinEmbargoEsteCorreoYaNoEsValido"));
			sesionManagedBean.getMensaje().setType("warning");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		} else { //No existe
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
			sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("NoExisteNingunUsuarioRegistradoConCorreo") + ": " + login);
			sesionManagedBean.getMensaje().setType("warning");
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		}
		return "";
	}

	public UsuarioDTO buscarUsuario(String correo) {
		List<UsuarioDTO> lista = usuarioDAO.consultarUsuarioPorCorreo(correo);
		if (lista.size() == 1) {
			return lista.get(0);
		}
		return null;
	}

	public boolean existeUsuarioAntiguo(String correo) {
		List<CredencialDTO> lista = credencialDAO.consultarCredencialPorCorreoYEstado(correo, 1);
		return lista.size() > 0;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = EncryptService.encriptarClave(pass);
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

}
