/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.controlador.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.cubiktimer.controlador.managedbeans.session.ConfiguracionManagedBean;
import com.cubiktimer.controlador.managedbeans.session.SesionManagedBean;
import com.cubiktimer.modelo.dao.AmigoDAO;
import com.cubiktimer.modelo.dao.UsuarioDAO;
import com.cubiktimer.modelo.dto.AmigoDTO;
import com.cubiktimer.modelo.dto.UsuarioDTO;
import com.cubiktimer.util.Constantes;

@ManagedBean
@ViewScoped
public class AmigosManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{configuracionManagedBean}")
	private ConfiguracionManagedBean configuracionManagedBean;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;

	private transient HttpSession session;
	private Integer idUsuario;

	private List<UsuarioDTO> listaUsuarios;
	private List<UsuarioDTO> listaAmigos;
	private List<UsuarioDTO> listaSolicitudesAmistad;
	private List<UsuarioDTO> usuariosSeleccionados;
	private UsuarioDAO usuarioDAO;
	private AmigoDAO amigoDAO;

	public AmigosManagedBean() {
		this.usuarioDAO = new UsuarioDAO();
		this.amigoDAO = new AmigoDAO();
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (session.getAttribute("idUsuario") != null) {
			idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());
		}

	}

	@PostConstruct
	public void init() {
		this.listaUsuarios = usuarioDAO.consultarUsuariosNoAmigos(idUsuario);
		this.listaAmigos = usuarioDAO.consultarUsuariosAmigos(idUsuario);
		this.listaSolicitudesAmistad = usuarioDAO.consultarSolicitudesDeAmistad(idUsuario);
	}

	public String invitarAmigos() {
		// Si tiene sesion iniciada se verifica que el id de usuario se encuentre
		// asignado correctamente
		if (idUsuario == null && sesionManagedBean.getUsuarioLogueado() != null
				&& sesionManagedBean.getUsuarioLogueado().getIdUsuario() != null) {
			idUsuario = sesionManagedBean.getUsuarioLogueado().getIdUsuario();
		}
		for (UsuarioDTO usuarioDTO : usuariosSeleccionados) {
			AmigoDTO amigoDTO = new AmigoDTO();
			amigoDTO.setIdUsuario(idUsuario);
			amigoDTO.setIdAmigo(usuarioDTO.getIdUsuario());
			amigoDTO.setEstado(2);
			amigoDAO.merge(amigoDTO);
		}
		sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
		sesionManagedBean.getMensaje().setText(
				sesionManagedBean.getRecursos().getString("LasSolicitudesDeAmistadSeleccionadasHanSidoEnviadas"));
		sesionManagedBean.getMensaje().setType(Constantes.SUCCESS);
		sesionManagedBean.getMensaje().setMensajePendiente(true);
		init();
		return "";
	}

	public String aceptarSolicitudes() {
		// Si tiene sesion iniciada se verifica que el id de usuario se encuentre
		// asignado correctamente
		if (idUsuario == null && sesionManagedBean.getUsuarioLogueado() != null
				&& sesionManagedBean.getUsuarioLogueado().getIdUsuario() != null) {
			idUsuario = sesionManagedBean.getUsuarioLogueado().getIdUsuario();
		}
		for (UsuarioDTO usuarioDTO : usuariosSeleccionados) {
			AmigoDTO amigoDTO = new AmigoDTO();
			amigoDTO.setIdUsuario(idUsuario);
			amigoDTO.setIdAmigo(usuarioDTO.getIdUsuario());
			amigoDTO.setEstado(1);
			amigoDAO.merge(amigoDTO);
			AmigoDTO amigoDTO2 = new AmigoDTO();
			amigoDTO2.setIdUsuario(usuarioDTO.getIdUsuario());
			amigoDTO2.setIdAmigo(idUsuario);
			amigoDTO2.setEstado(1);
			amigoDAO.merge(amigoDTO2);
		}
		sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
		sesionManagedBean.getMensaje().setText(
				sesionManagedBean.getRecursos().getString("LasSolicitudesDeAmistadSeleccionadasHanSidoAceptadas"));
		sesionManagedBean.getMensaje().setType(Constantes.SUCCESS);
		sesionManagedBean.getMensaje().setMensajePendiente(true);
		init();
		return "";
	}

	/**
	 * Metodo que rechaza solicitudes de amistad
	 * 
	 * @return
	 */
	public String rechazarSolicitudes() {
		eliminarAmigos();
		sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
		sesionManagedBean.getMensaje().setText(
				sesionManagedBean.getRecursos().getString("LasSolicitudesDeAmistadSeleccionadasHanSidoRechazadas"));
		sesionManagedBean.getMensaje().setType(Constantes.SUCCESS);
		sesionManagedBean.getMensaje().setMensajePendiente(true);
		return "";
	}

	/**
	 * Metodo que elimina amigos
	 * 
	 * @return
	 */
	public String eliminarAmigos() {
		// Si tiene sesion iniciada se verifica que el id de usuario se encuentre
		// asignado correctamente
		if (idUsuario == null && sesionManagedBean.getUsuarioLogueado() != null
				&& sesionManagedBean.getUsuarioLogueado().getIdUsuario() != null) {
			idUsuario = sesionManagedBean.getUsuarioLogueado().getIdUsuario();
		}
		for (UsuarioDTO usuarioDTO : usuariosSeleccionados) {
			AmigoDTO amigoDTO = new AmigoDTO();
			amigoDTO.setIdUsuario(usuarioDTO.getIdUsuario());
			amigoDTO.setIdAmigo(idUsuario);
			amigoDTO.setEstado(1);
			amigoDAO.delete(amigoDTO);
			AmigoDTO amigoDTO2 = new AmigoDTO();
			amigoDTO2.setIdUsuario(idUsuario);
			amigoDTO2.setIdAmigo(usuarioDTO.getIdUsuario());
			amigoDTO2.setEstado(1);
			amigoDAO.delete(amigoDTO2);
		}
		sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
		sesionManagedBean.getMensaje().setText(
				sesionManagedBean.getRecursos().getString("LosUsuariosSeleccionadosFueronRemovidosDeTusAmigos"));
		sesionManagedBean.getMensaje().setType(Constantes.SUCCESS);
		sesionManagedBean.getMensaje().setMensajePendiente(true);
		init();
		return "";
	}

	/**
	 * Mètodo que bloquea usuarios que han realizado solicitudes de amistad o amigos
	 * 
	 * @return
	 */
	public String bloquearSolicitudes() {
		// Si tiene sesion iniciada se verifica que el id de usuario se encuentre
		// asignado correctamente
		bloquearUsuarios();
		sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
		sesionManagedBean.getMensaje().setText(sesionManagedBean.getRecursos()
				.getString("LasSolicitudesDeAmistadSeleccionadasHanSidoRechazadasPermanentemente"));
		sesionManagedBean.getMensaje().setType(Constantes.SUCCESS);
		sesionManagedBean.getMensaje().setMensajePendiente(true);
		return "";
	}

	public String bloquearUsuarios() {
		if (idUsuario == null && sesionManagedBean.getUsuarioLogueado() != null
				&& sesionManagedBean.getUsuarioLogueado().getIdUsuario() != null) {
			idUsuario = sesionManagedBean.getUsuarioLogueado().getIdUsuario();
		}
		for (UsuarioDTO usuarioDTO : usuariosSeleccionados) {
			AmigoDTO amigoDTO = new AmigoDTO();
			amigoDTO.setIdUsuario(idUsuario);
			amigoDTO.setIdAmigo(usuarioDTO.getIdUsuario());
			amigoDTO.setEstado(3);
			amigoDAO.merge(amigoDTO);
			AmigoDTO amigoDTO2 = new AmigoDTO();
			amigoDTO2.setIdUsuario(usuarioDTO.getIdUsuario());
			amigoDTO2.setIdAmigo(idUsuario);
			amigoDTO2.setEstado(3);
			amigoDAO.merge(amigoDTO2);
		}
		sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
		sesionManagedBean.getMensaje().setText(
				sesionManagedBean.getRecursos().getString("LosUsuariosSeleccionadosFueronBloqueadosPermanentemente"));
		sesionManagedBean.getMensaje().setType(Constantes.SUCCESS);
		sesionManagedBean.getMensaje().setMensajePendiente(true);
		init();
		return "";
	}

	public List<UsuarioDTO> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<UsuarioDTO> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<UsuarioDTO> getUsuariosSeleccionados() {
		return usuariosSeleccionados;
	}

	public void setUsuariosSeleccionados(List<UsuarioDTO> usuariosSeleccionados) {
		this.usuariosSeleccionados = usuariosSeleccionados;
	}

	public List<UsuarioDTO> getListaSolicitudesAmistad() {
		return listaSolicitudesAmistad;
	}

	public void setListaSolicitudesAmistad(List<UsuarioDTO> listaSolicitudesAmistad) {
		this.listaSolicitudesAmistad = listaSolicitudesAmistad;
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

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<UsuarioDTO> getListaAmigos() {
		return listaAmigos;
	}

	public void setListaAmigos(List<UsuarioDTO> listaAmigos) {
		this.listaAmigos = listaAmigos;
	}

}
