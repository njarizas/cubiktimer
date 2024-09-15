/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.controlador.managedbeans.session;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cubiktimer.controlador.facade.ConfiguracionFacade;
import com.cubiktimer.controlador.managedbeans.RubikManagedBean;
import com.cubiktimer.modelo.dao.CredencialDAO;
import com.cubiktimer.modelo.dao.EstadisticasDAO;
import com.cubiktimer.modelo.dao.PermisosDAO;
import com.cubiktimer.modelo.dao.RolDAO;
import com.cubiktimer.modelo.dao.UsuarioDAO;
import com.cubiktimer.modelo.dto.ConfiguracionDTO;
import com.cubiktimer.modelo.dto.CredencialDTO;
import com.cubiktimer.modelo.dto.PermisoDTO;
import com.cubiktimer.modelo.dto.RolDTO;
import com.cubiktimer.modelo.dto.UsuarioDTO;
import com.cubiktimer.util.Constantes;
import com.cubiktimer.util.EncryptService;
import com.cubiktimer.util.Util;

/**
 * Bean que se utiliza para iniciar sesion, notese que es de vista puesto que
 * las variables las inyecta a #{sesionManagedBean}
 * 
 * @author Nelson
 */
@ManagedBean
@ViewScoped
public class IniciarSesionManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(IniciarSesionManagedBean.class);

	private String login;
	private String pass;

	private UsuarioDAO usuarioDAO;
	private CredencialDAO credencialDAO;
	private PermisosDAO permisosDAO;
	private RolDAO rolDAO;
	private EstadisticasDAO estadisticasDAO;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;

	@ManagedProperty(value = "#{configuracionManagedBean}")
	private ConfiguracionManagedBean configuracionManagedBean;

	@ManagedProperty(value = "#{rubikManagedBean}")
	private RubikManagedBean rubikManagedBean;

	ConfiguracionFacade configuracionFacade;

	public IniciarSesionManagedBean() {
		super();
		usuarioDAO = new UsuarioDAO();
		credencialDAO = new CredencialDAO();
		permisosDAO = new PermisosDAO();
		rolDAO = new RolDAO();
		estadisticasDAO = new EstadisticasDAO();
		configuracionFacade = new ConfiguracionFacade();
	}

	/**
	 * Método que se encarga de iniciar sesión haciendo unas validaciones
	 * previamente
	 * 
	 * @return
	 */
	public String iniciarSesion() {
		log.trace("inicio iniciarSesion");
		UsuarioDTO u = buscarUsuario(login);
		if (u != null) { // si existe un usuario con el correo proporcionado
			String sal = usuarioDAO.consultarSalPorUsuario(u.getCorreo());
			String hash = EncryptService.encriptarClave(pass + sal);
			if (login.equals(u.getCorreo()) && hash.equals(u.getClave())) {// se comprueba que la clave y el correo
																			// correspondan
				List<RolDTO> listaRoles = rolDAO.consultarRolesPorIdUsuario(u.getIdUsuario());
				for (RolDTO rolDTO : listaRoles) {
					log.trace(rolDTO.toString());
				}
				List<PermisoDTO> listaPermisos = permisosDAO.consultarPermisosPorIdUsuario(u.getIdUsuario());
				if (listaRoles.isEmpty()) {
					sesionManagedBean.getMensaje()
							.setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
					sesionManagedBean.getMensaje()
							.setText(sesionManagedBean.getRecursos().getString("UsuarioNoTieneNingunRol"));
					sesionManagedBean.getMensaje().setType(Constantes.WARNING);
					sesionManagedBean.getMensaje().setMensajePendiente(true);
					log.trace("fin iniciarSesion");
					return "";
				}
				if (u.getEstado() == 1) {
					sesionManagedBean.setUsuarioLogueado(u);
					sesionManagedBean.setListaRoles(listaRoles);
					sesionManagedBean.setRolActual(sesionManagedBean.getListaRoles().get(0));
					sesionManagedBean.setListaPermisos(listaPermisos);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idUsuario",
							u.getIdUsuario());
					configuracionManagedBean.setIdUsuario(u.getIdUsuario());
					log.info("Inicio de sesión de usuario : " + u.getIdUsuario());
					ConfiguracionDTO cDto = configuracionFacade.obtenerIdiomaPreferidoPorIdUsuario(u.getIdUsuario());
					rubikManagedBean.getListaRecords().setListaPBSingle(estadisticasDAO.obtenerListaPBSingle(u.getIdUsuario()));
					if (cDto != null) {
						String idioma = cDto.getValorTexto();
						if (idioma != null) {
							sesionManagedBean.setIdioma(idioma);
							Locale locale = new Locale(idioma);
							sesionManagedBean.setLocale(locale);
							FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
						}
					}
					configuracionManagedBean.cargarConfiguracion();
					ConfiguracionDTO cDto2 = configuracionFacade.obtenerPaginaInicialPorIdUsuario(u.getIdUsuario());
					if (cDto2 != null) {
						String paginaInicio = cDto2.getValorTexto();
						log.trace("fin iniciarSesion");
						return "/" + paginaInicio + "?faces-redirect=true";
					}
					log.trace("fin iniciarSesion");
					return "/rubik?faces-redirect=true";
				}
				if (u.getEstado() == 2) {
					sesionManagedBean.getMensaje()
							.setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
					sesionManagedBean.getMensaje()
							.setText(sesionManagedBean.getRecursos().getString("MensajeVerificarCuenta"));
					sesionManagedBean.getMensaje().setType(Constantes.WARNING);
					sesionManagedBean.getMensaje().setMensajePendiente(true);
					log.trace("fin iniciarSesion");
					return "";
				}
			} // la clave y correo no corresponden , entonces se busca en las credenciales
			List<CredencialDTO> listaCredenciales = credencialDAO.consultarCredencialPorCorreoClaveYEstado(login, hash,
					1);
			if (!listaCredenciales.isEmpty()) {// existe
				Date fechaMod = listaCredenciales.get(0).getFechaFin();
				Util util = Util.getInstance();
				sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
				sesionManagedBean.getMensaje()
						.setText(sesionManagedBean.getRecursos().getString("LaContraseñaFueModificadaEl") + ": "
								+ util.getFormatoFechaLarga().format(fechaMod));
				sesionManagedBean.getMensaje().setType(Constantes.WARNING);
				sesionManagedBean.getMensaje().setMensajePendiente(true);
			} else {// No existe
				sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
				sesionManagedBean.getMensaje()
						.setText(sesionManagedBean.getRecursos().getString("LaContraseñaIngresadaNoEsCorrecta"));
				sesionManagedBean.getMensaje().setType(Constantes.WARNING);
				sesionManagedBean.getMensaje().setMensajePendiente(true);
			}
		} // no existe un usuario con el correo proporcionado: se busca si existe un
			// usuario antiguo en las credenciales (el usuario cambió el correo)
		else if (existeUsuarioAntiguo(login)) { // Si existe
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("ExisteUnaCuentaAsociadaAlCorreo") + ": " + login
							+ ", " + sesionManagedBean.getRecursos().getString("SinEmbargoEsteCorreoYaNoEsValido"));
			sesionManagedBean.getMensaje().setType(Constantes.WARNING);
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		} else { // No existe
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("NoExisteNingunUsuarioRegistradoConCorreo")
							+ ": " + login);
			sesionManagedBean.getMensaje().setType(Constantes.WARNING);
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		}
		log.trace("fin iniciarSesion");
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
		return !lista.isEmpty();
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
		this.pass = pass;
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
			return this.configuracionManagedBean;
		} else {
			return new ConfiguracionManagedBean();
		}
	}

	public void setConfiguracionManagedBean(ConfiguracionManagedBean configuracionManagedBean) {
		this.configuracionManagedBean = configuracionManagedBean;
	}

	public RubikManagedBean getRubikManagedBean() {
		if (this.rubikManagedBean != null) {
			return this.rubikManagedBean;
		} else {
			return new RubikManagedBean();
		}
	}

	public void setRubikManagedBean(RubikManagedBean rubikManagedBean) {
		this.rubikManagedBean = rubikManagedBean;
	}

}
