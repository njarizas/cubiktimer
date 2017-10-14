package com.sigolf.juegos.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.sigolf.juegos.dao.CredencialDAO;
import com.sigolf.juegos.dao.UsuarioDAO;
import com.sigolf.juegos.dto.ConfiguracionDTO;
import com.sigolf.juegos.dto.CredencialDTO;
import com.sigolf.juegos.dto.RolDTO;
import com.sigolf.juegos.dto.UsuarioDTO;
import com.sigolf.juegos.facade.ConfiguracionFacade;
import com.sigolf.juegos.util.EncryptService;
import com.sigolf.juegos.util.UtilSigolf;

/**
 * Bean que se utiliza para iniciar sesion, notese que es de vista puesto que las variables las inyecta a #{sesionManagedBean}
 * @author Nelson
 */
@ManagedBean
@ViewScoped
public class IniciarSesionManagedBean {

	private String login;
	private String pass;

	private UsuarioDAO usuarioDAO;
	private CredencialDAO credencialDAO;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;

	ConfiguracionFacade configuracionFacade;

	public IniciarSesionManagedBean() {
		super();
		usuarioDAO = new UsuarioDAO();
		credencialDAO = new CredencialDAO();
		configuracionFacade = new ConfiguracionFacade();
	}

	/**
	 * Método que se encarga de iniciar sesión haciendo unas validaciones
	 * previamente
	 * @return
	 */
	public String iniciarSesion() {
		//	        System.out.println("entro a usuariosManagedBean.iniciarSesion");
		//UsuarioEntity u = buscarUsuario(login);//se busca un usuario con el correo ingresado y se asigna en la variable u
		UsuarioDTO u = buscarUsuario(login);
		if (u != null) { // si existe un usuario con el correo proporcionado
			if (login.equals(u.getCorreo()) && pass.equals(u.getClave())) {//se comprueba que la clave y el correo correspondan
				/* TODO Traer lista de roles del usuario
				 * */
				List<RolDTO> listaRoles = new ArrayList();
				listaRoles.add(new RolDTO(1, "Superusuario", "Superusuario", 1));
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
					return "/ahorcado?faces-redirect=true";
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
				sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString("Atencion"));
				sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos().getString("LaContraseñaFueModificadaEl") + ": " + UtilSigolf.formatoFechaLarga.format(fechaMod));
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

	//	    public List<CredencialEntity> buscarCredencialesPorCorreo(String correo) {
	//	        List<CredencialEntity> lista = crFacade.findByCorreo(correo);
	//	        if (lista.size() > 0) {
	//	            return lista;
	//	        }
	//	        return null;
	//	    }

	//	    public List<CredencialEntity> buscarCredencialesPorId(Integer id) {
	//	        List<CredencialEntity> lista = crFacade.findByIdUsuario(id);
	//	        if (lista.size() > 0) {
	//	            return lista;
	//	        }
	//	        return null;
	//	    }

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
