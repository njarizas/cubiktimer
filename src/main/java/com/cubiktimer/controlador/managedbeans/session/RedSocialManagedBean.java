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
import com.cubiktimer.controlador.facade.UsuarioFacade;
import com.cubiktimer.error.ExceptionHandler;
import com.cubiktimer.modelo.dao.CredencialDAO;
import com.cubiktimer.modelo.dao.PermisosDAO;
import com.cubiktimer.modelo.dao.RolDAO;
import com.cubiktimer.modelo.dao.UsuarioDAO;
import com.cubiktimer.modelo.dao.UsuarioRedSocialDAO;
import com.cubiktimer.modelo.dto.ConfiguracionDTO;
import com.cubiktimer.modelo.dto.CredencialDTO;
import com.cubiktimer.modelo.dto.PermisoDTO;
import com.cubiktimer.modelo.dto.RolDTO;
import com.cubiktimer.modelo.dto.UsuarioDTO;
import com.cubiktimer.modelo.dto.UsuarioRedSocialDTO;
import com.cubiktimer.util.Constantes;

/**
 * Bean que se utiliza para iniciar sesion, notese que es de vista puesto que
 * las variables las inyecta a #{sesionManagedBean}
 * 
 * @author Nelson
 */
@ManagedBean
@ViewScoped
public class RedSocialManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(RedSocialManagedBean.class);

	private String id;
	private String email;
	private String name;
	private String sistema;

	private UsuarioRedSocialDTO usuarioRedSocialDTO;
	private UsuarioDTO usuario;

	private UsuarioRedSocialDAO usuarioRedSocialDAO;
	private UsuarioDAO usuarioDAO;
	private RolDAO rolDAO;
	private PermisosDAO permisosDAO;
	private CredencialDAO credencialDAO;

	private UsuarioFacade usuarioFacade;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;

	@ManagedProperty(value = "#{configuracionManagedBean}")
	private ConfiguracionManagedBean configuracionManagedBean;

	ConfiguracionFacade configuracionFacade;

	public RedSocialManagedBean() {
		super();
		usuario = new UsuarioDTO();
		usuarioRedSocialDTO = new UsuarioRedSocialDTO();
		usuarioRedSocialDAO = new UsuarioRedSocialDAO();
		usuarioDAO = new UsuarioDAO();
		rolDAO = new RolDAO();
		permisosDAO = new PermisosDAO();
		credencialDAO = new CredencialDAO();
		configuracionFacade = new ConfiguracionFacade();
		usuarioFacade = new UsuarioFacade();
	}

	public String iniciarSesionRedSocial() {
		usuario.setNombres(name);
		usuario.setCorreo(email);
		usuarioRedSocialDTO.setIdUsuarioRedSocial(id);
		usuarioRedSocialDTO.setSistema(sistema);
		UsuarioRedSocialDTO ursDTO = buscarUsuarioRedSocial(id);
		if (ursDTO == null) {
			UsuarioDTO uDTO = buscarUsuario();
			CredencialDTO cDTO = buscarCredencial();
			if (cDTO != null) {
				usuarioRedSocialDTO.setIdUsuario(cDTO.getIdUsuario());
			}
			if (uDTO != null) {
				usuarioRedSocialDTO.setIdUsuario(uDTO.getIdUsuario());
			}
			if (usuarioRedSocialDTO.getIdUsuario() != null) {
				Integer idRegistro = usuarioRedSocialDAO.merge(usuarioRedSocialDTO);
				if (idRegistro != 0) {
					usuarioRedSocialDTO.setIdRegistro(idRegistro);
				} else {
					log.warn("Falló al hacer el merge en tabla usuarios_redes_sociales");
				}
			} else {
				registrarUsuario();
			}
		} 
		return iniciarSesion();
	}

	/**
	 * Método que se encarga de iniciar sesión haciendo unas validaciones
	 * previamente, se debe asegurar que el usuario ya exista
	 * 
	 * @return
	 */
	public String iniciarSesion() {
		log.trace("inicio iniciarSesion");
		UsuarioRedSocialDTO urs = buscarUsuarioRedSocial(id);
		if (urs != null) { // si existe un usuario de red social con el id proporcionado
			usuarioRedSocialDTO = urs;
			UsuarioDTO u = buscarUsuarioPorId(urs.getIdUsuario());
			if (u != null) {
				List<RolDTO> listaRoles = rolDAO.consultarRolesPorIdUsuario(urs.getIdUsuario());
				for (RolDTO rolDTO : listaRoles) {
					log.trace(rolDTO.toString());
				}
				List<PermisoDTO> listaPermisos = permisosDAO.consultarPermisosPorIdUsuario(urs.getIdUsuario());
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
					log.info("Inicio de sesión de usuario mediante red social: " + u.getIdUsuario());
					ConfiguracionDTO cDto = configuracionFacade.obtenerIdiomaPreferidoPorIdUsuario(u.getIdUsuario());
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
			} else {
				// no debería pasar por acá
				log.warn(
						"Existe usuario en tabla usuarios_redes_sociales pero no existe en tabla usuarios. id_usuario_red_social: "
								+ id);
			}
		} else {
			log.warn(" no existe usuario en tabla usuarios_redes_sociales. id_usuario_red_social: " + id);
		}
		log.trace("fin iniciarSesion");
		return "";
	}

	public boolean registrarUsuario() {
		if (!existeUsuario() && !existeCredencial()) {
			usuario.setSexo('X');
			usuario.setFechaCreacion(new Date());
			usuario.setFechaModificacion(new Date());
			usuario.setEstado(1);
			if (persistirUsuario()) {
				usuario = new UsuarioDTO();
				name = "";
				email = "";
				sistema = "";
				return true;
			}
		} else {
			// TODO ya existe un usuario o credencial con el correo retornado por la red
			// social
		}
		return false;
	}

	public boolean existeUsuario() {
		List<UsuarioDTO> lu = usuarioDAO.consultarUsuarioPorCorreo(usuario.getCorreo().trim());
		return !lu.isEmpty();
	}

	public boolean existeCredencial() {
		List<CredencialDTO> lc = credencialDAO.consultarCredencialPorCorreo(usuario.getCorreo().trim());
		return !lc.isEmpty();
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
			List<RolDTO> listaRolesDefault = rolDAO.findById(Constantes.ROL_POR_DEFECTO);
			usuario.setIdUsuario(usuarioFacade.create(usuario, listaRolesDefault, usuarioRedSocialDTO));
			return true;
		} catch (Exception e) {
			log.warn("ocurrio un error al intentar registrar el usuario");
			ExceptionHandler.manejarExcepcionGrave(e);
		}
		return false;
	}

	public UsuarioRedSocialDTO buscarUsuarioRedSocial(String idUsuarioRedSocial) {
		List<UsuarioRedSocialDTO> lista = usuarioRedSocialDAO.consultarUsuarioPorIdUsuarioRedSocial(idUsuarioRedSocial);
		if (lista.size() == 1) {
			return lista.get(0);
		}
		return null;
	}

	public UsuarioDTO buscarUsuarioPorId(Integer idUsuario) {
		List<UsuarioDTO> lista = usuarioDAO.consultarUsuarioPorIdUsuario(idUsuario);
		if (lista.size() == 1) {
			return lista.get(0);
		}
		return null;
	}

	public UsuarioDTO buscarUsuario() {
		List<UsuarioDTO> lista = usuarioDAO.consultarUsuarioPorCorreo(email);
		if (lista.size() == 1) {
			return lista.get(0);
		}
		return null;
	}

	public CredencialDTO buscarCredencial() {
		List<CredencialDTO> lista = credencialDAO.consultarCredencialPorCorreo(email);
		if (lista.size() == 1) {
			return lista.get(0);
		}
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public UsuarioRedSocialDTO getUsuarioRedSocialDTO() {
		return usuarioRedSocialDTO;
	}

	public void setUsuarioRedSocialDTO(UsuarioRedSocialDTO usuarioRedSocialDTO) {
		this.usuarioRedSocialDTO = usuarioRedSocialDTO;
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

}
