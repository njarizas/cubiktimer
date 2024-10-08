/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.controlador.managedbeans.session;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private static final Logger log = LogManager.getLogger(RecuperarUsuarioManagedBean.class);

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
			insertarCredencial(u);
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
				String sal = usuarioDAO.consultarSalPorUsuario(u.getCorreo());
				if (sal.isEmpty()) {
					log.warn("El correo de la credencial de acceso no tiene sal");
				}
				u.setClave(EncryptService.encriptarClave(nuevaClave + sal));
				usuarioDAO.update(u);
			}
		}
		return retorno;
	}
	
	public void insertarCredencial(UsuarioDTO u) {
		CredencialDTO credencial = new CredencialDTO();
		credencial.setIdUsuario(u.getIdUsuario());
		credencial.setCorreo(u.getCorreo());
		credencial.setClave(u.getClave());
		credencial.setFechaFin(new Date());
		credencial.setEstado(1);
		Date fechaUltimoCambio = u.getFechaCreacion();
		Date f = credencialDAO.obtenerFechaUltimaCredencial(u.getIdUsuario());
		if (f != null) {
			fechaUltimoCambio = f;
		}
		credencial.setFechaInicio(fechaUltimoCambio);
		if (credencialDAO.create(credencial) > 0) {
			log.trace("Creo credencial " + credencial.getCorreo());
		}
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