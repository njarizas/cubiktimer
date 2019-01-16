package com.cubiktimer.controlador.managedbeans.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.cubiktimer.controlador.facade.ConfiguracionFacade;
import com.cubiktimer.modelo.dto.ConfiguracionDTO;
import com.cubiktimer.util.Constantes;

/**
 *
 * @author Nelson
 */
@ManagedBean
@SessionScoped
public class ConfiguracionManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ConfiguracionManagedBean.class);

	private Integer idUsuario;
	private ConfiguracionDTO tiempoInspeccion;
	private ConfiguracionDTO tipoCubo;
	private ConfiguracionDTO idioma;
	private ConfiguracionDTO paginaInicial;
	private ConfiguracionDTO estiloVisual;

	private ConfiguracionFacade configuracionFacade;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;

	public ConfiguracionManagedBean() {
		this.tiempoInspeccion = new ConfiguracionDTO();
		this.tiempoInspeccion.setIdTipo(18);
		this.tiempoInspeccion.setValorEntero(15);
		this.tipoCubo = new ConfiguracionDTO();
		this.tipoCubo.setIdTipo(22);
		this.tipoCubo.setValorEntero(6);
		this.idioma = new ConfiguracionDTO();
		this.idioma.setIdTipo(19);
		this.idioma.setValorTexto("ES");
		this.paginaInicial = new ConfiguracionDTO();
		this.paginaInicial.setIdTipo(23);
		this.paginaInicial.setValorTexto("rubik");
		this.estiloVisual = new ConfiguracionDTO();
		this.estiloVisual.setIdTipo(28);
		this.estiloVisual.setValorEntero(0);
		FacesContext fc = FacesContext.getCurrentInstance();
		idUsuario = (Integer) fc.getExternalContext().getSessionMap().get("idUsuario");
		configuracionFacade = new ConfiguracionFacade();
	}

	@PostConstruct
	public void cargarConfiguracion() {
		log.debug("inicio cargarConfiguracion, idUsuario: " + idUsuario + ".");
		if (idUsuario != null) {
			ConfiguracionDTO tiempoInspeccionParametrizado = configuracionFacade
					.obtenerTiempoDeInspeccionPreferidoPorIdUsuario(idUsuario);
			if (tiempoInspeccionParametrizado != null) {
				this.tiempoInspeccion = tiempoInspeccionParametrizado;
			}
			this.tiempoInspeccion.setIdUsuario(idUsuario);
			ConfiguracionDTO estiloVisualParametrizado = configuracionFacade
					.obtenerEstiloVisualPreferidoPorIdUsuario(idUsuario);
			if (estiloVisualParametrizado != null) {
				this.estiloVisual = estiloVisualParametrizado;
			}
			this.estiloVisual.setIdUsuario(idUsuario);
			ConfiguracionDTO tipoCuboParametrizado = configuracionFacade
					.obtenerTipoCuboPreferidoPorIdUsuario(idUsuario);
			if (tipoCuboParametrizado != null) {
				this.tipoCubo = tipoCuboParametrizado;
			}
			this.tipoCubo.setIdUsuario(idUsuario);
			ConfiguracionDTO idiomaParametrizado = configuracionFacade.obtenerIdiomaPreferidoPorIdUsuario(idUsuario);
			if (idiomaParametrizado != null) {
				this.idioma = idiomaParametrizado;
			}
			this.idioma.setIdUsuario(idUsuario);
			ConfiguracionDTO paginaInicialParametrizada = configuracionFacade
					.obtenerPaginaInicialPorIdUsuario(idUsuario);
			if (paginaInicialParametrizada != null) {
				this.paginaInicial = paginaInicialParametrizada;
			}
			this.paginaInicial.setIdUsuario(idUsuario);
		}
		log.debug("fin cargarConfiguracion, idUsuario: " + idUsuario + ".");
	}

	public void alternarEstiloVisual() {
		this.estiloVisual.setValorEntero(Math.abs(this.estiloVisual.getValorEntero() - 1));
	}

	public String guardarConfiguracion() {
		FacesContext fc = FacesContext.getCurrentInstance();
		idUsuario = (Integer) fc.getExternalContext().getSessionMap().get("idUsuario");
		if (idUsuario != null) {
			this.tiempoInspeccion.setIdUsuario(idUsuario);
			this.tipoCubo.setIdUsuario(idUsuario);
			this.idioma.setIdUsuario(idUsuario);
			this.paginaInicial.setIdUsuario(idUsuario);
			this.estiloVisual.setIdUsuario(idUsuario);
			List<ConfiguracionDTO> listaConfiguraciones = new ArrayList<>();
			listaConfiguraciones.add(this.tiempoInspeccion);
			listaConfiguraciones.add(this.tipoCubo);
			listaConfiguraciones.add(this.idioma);
			listaConfiguraciones.add(this.paginaInicial);
			listaConfiguraciones.add(this.estiloVisual);
			configuracionFacade.guardar(listaConfiguraciones);
			log.info("Se guarda configuración de usuario: " + idUsuario);
			sesionManagedBean.getMensaje().setTitle(sesionManagedBean.getRecursos().getString(Constantes.ATENCION));
			sesionManagedBean.getMensaje()
					.setText(sesionManagedBean.getRecursos().getString("ElCambioFueRealizadoExitosamente"));
			sesionManagedBean.getMensaje().setType(Constantes.SUCCESS);
			sesionManagedBean.getMensaje().setMensajePendiente(true);
		} else {
			log.trace("No se guarda configuracion ya que es un usuario no logueado");
		}
		return "";
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public ConfiguracionDTO getTiempoInspeccion() {
		return tiempoInspeccion;
	}

	public void setTiempoInspeccion(ConfiguracionDTO tiempoInspeccion) {
		this.tiempoInspeccion = tiempoInspeccion;
	}

	public ConfiguracionDTO getTipoCubo() {
		return tipoCubo;
	}

	public void setTipoCubo(ConfiguracionDTO tipoCubo) {
		this.tipoCubo = tipoCubo;
	}

	public ConfiguracionDTO getIdioma() {
		return idioma;
	}

	public void setIdioma(ConfiguracionDTO idioma) {
		this.idioma = idioma;
	}

	public ConfiguracionDTO getPaginaInicial() {
		return paginaInicial;
	}

	public void setPaginaInicial(ConfiguracionDTO paginaInicial) {
		this.paginaInicial = paginaInicial;
	}

	public ConfiguracionDTO getEstiloVisual() {
		return estiloVisual;
	}

	public void setEstiloVisual(ConfiguracionDTO estiloVisual) {
		this.estiloVisual = estiloVisual;
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