package com.sigolf.juegos.rubik.controlador;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.sigolf.juegos.dto.ConfiguracionDTO;
import com.sigolf.juegos.facade.ConfiguracionFacade;

/**
 *
 * @author Nelson
 */
@ManagedBean
@SessionScoped
public class ConfiguracionManagedBean {

	private Integer idUsuario;
	private Integer tiempoInspeccion;
	private Integer tipoCubo;
	private String idioma;

	private ConfiguracionFacade configuracionFacade;

	public ConfiguracionManagedBean() {
		FacesContext fc = FacesContext.getCurrentInstance();
		idUsuario=(Integer)fc.getExternalContext().getSessionMap().get("idUsuario");
		configuracionFacade = new ConfiguracionFacade();
		this.tiempoInspeccion=15;
		this.tipoCubo=5;
	}

	@PostConstruct
	public void cargarConfiguracion(){
		if (idUsuario != null){
			ConfiguracionDTO tiempoInspeccion= configuracionFacade.obtenerTiempoDeInspeccionPreferidoPorIdUsuario(idUsuario);
			if (tiempoInspeccion != null){
				this.tiempoInspeccion = tiempoInspeccion.getValorEntero();
			} 
			ConfiguracionDTO cubo= configuracionFacade.obtenerTipoCuboPreferidoPorIdUsuario(idUsuario);
			if (cubo != null){
				this.tipoCubo = cubo.getValorEntero();
			} 
			ConfiguracionDTO idioma= configuracionFacade.obtenerIdiomaPreferidoPorIdUsuario(idUsuario);
			if (idioma != null){
				this.idioma = idioma.getValorTexto();
			} 
		}
	}

	public void cambioCubo(ValueChangeEvent event) {
		this.tipoCubo = Integer.parseInt(event.getNewValue().toString());
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getTiempoInspeccion() {
		return tiempoInspeccion;
	}

	public void setTiempoInspeccion(Integer tiempoInspeccion) {
		this.tiempoInspeccion = tiempoInspeccion;
	}

	public Integer getTipoCubo() {
		return tipoCubo;
	}

	public void setTipoCubo(Integer tipoCubo) {
		this.tipoCubo = tipoCubo;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

}