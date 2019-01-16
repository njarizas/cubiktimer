package com.cubiktimer.controlador.managedbeans.session;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.cubiktimer.modelo.dao.PermisosDAO;
import com.cubiktimer.modelo.dto.PermisoDTO;
import com.cubiktimer.modelo.dto.RolDTO;
import com.cubiktimer.modelo.dto.UsuarioDTO;
import com.cubiktimer.util.Mensaje;

/**
 *
 * @author Nelson
 */
@ManagedBean(name = "sesionManagedBean", eager = true)
@SessionScoped
public class SesionManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SesionManagedBean.class);

	private String idioma;
	private Locale locale;

	private UsuarioDTO usuarioLogueado;
	private List<RolDTO> listaRoles;
	private RolDTO rolActual;

	private PermisosDAO permisosDAO;

	private List<PermisoDTO> listaPermisos;

	transient ResourceBundle recursos;

	private static Map<String, Object> listaIdiomas;

	private Mensaje mensaje;
	private String ipConsultante;

	static {
		listaIdiomas = new LinkedHashMap<>();
		Locale spanish = new Locale("es");
		listaIdiomas.put("Español", spanish);
		listaIdiomas.put("English", Locale.ENGLISH);
	}

	public SesionManagedBean() {
		idioma = "ES";
		locale = new Locale(idioma);
		recursos = ResourceBundle.getBundle("texto", locale);
		mensaje = new Mensaje();
		FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) locale);
		permisosDAO = new PermisosDAO();
		ipConsultante = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
				.getRemoteAddr();
		log.debug("Se ha creado una nueva sesión desde la siguiente ip: " + ipConsultante);
	}

	public void goToIndex() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(getContextPath() + "/index.jsf");
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
	}

	public String cerrarSesion() {
		log.info("Cierre de sesión de usuario : " + usuarioLogueado.getIdUsuario());
		usuarioLogueado = null;
		rolActual = null;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index?faces-redirect=true";
	}

	public String getContextPath() {
		return com.cubiktimer.util.Util.getContextPath();
	}

	public void localeChange(ValueChangeEvent event) {
		String newLocaleValue = event.getNewValue().toString();
		Map.Entry<String, Object> entry = listaIdiomas.entrySet().stream()
				.filter(x -> newLocaleValue.equals(x.getValue().toString())).findAny().orElse(null);
		if (entry != null) {
			idioma = newLocaleValue;
			log.trace("Se cambió el idioma a: " + idioma);
			locale = (Locale) entry.getValue();
			FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
		}
	}

	public int contarPermisosIdPadre(Integer idPadre) {
		return permisosDAO.contarPermisosIdPadre(idPadre);
	}

	public List<PermisoDTO> consultarPermisosPorIdPadre(Integer idPadre) {
		return permisosDAO.consultarPermisosPorIdPadre(idPadre);
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Map<String, Object> getListaIdiomas() {
		return listaIdiomas;
	}

	public static void setListaIdiomas(Map<String, Object> listaIdiomas) {
		SesionManagedBean.listaIdiomas = listaIdiomas;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public ResourceBundle getRecursos() {
		return recursos;
	}

	public void setRecursos(ResourceBundle recursos) {
		this.recursos = recursos;
	}

	public UsuarioDTO getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(UsuarioDTO usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	public List<RolDTO> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<RolDTO> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public RolDTO getRolActual() {
		return rolActual;
	}

	public void setRolActual(RolDTO rolActual) {
		this.rolActual = rolActual;
	}

	public List<PermisoDTO> getListaPermisos() {
		return listaPermisos;
	}

	public void setListaPermisos(List<PermisoDTO> listaPermisos) {
		this.listaPermisos = listaPermisos;
	}

	public String getIpConsultante() {
		return ipConsultante;
	}

}
