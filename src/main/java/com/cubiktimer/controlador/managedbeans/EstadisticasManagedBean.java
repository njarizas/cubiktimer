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
import com.cubiktimer.modelo.dao.EstadisticasDAO;
import com.cubiktimer.modelo.rubik.estadisticas.CuentaPuzzle;
import com.cubiktimer.modelo.rubik.estadisticas.ListaCuentaPuzzle;
import com.cubiktimer.modelo.rubik.estadisticas.ListaPromedioCategoria;
import com.cubiktimer.modelo.rubik.estadisticas.ListaPromedios;
import com.cubiktimer.modelo.rubik.estadisticas.ListaRecords;

@ManagedBean
@ViewScoped
public class EstadisticasManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{configuracionManagedBean}")
	private ConfiguracionManagedBean configuracionManagedBean;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;

	private transient HttpSession session;
	private Integer idUsuario;
	private EstadisticasDAO estadisticasDAO;
	private ListaCuentaPuzzle listaCuentaPuzzle;
	private ListaRecords listaRecords;
	private ListaPromedioCategoria listaPromedioCategoria;
	private ListaPromedios listaPromedios;
	private ListaPromedios listaAverages;

	public EstadisticasManagedBean() {
		estadisticasDAO = new EstadisticasDAO();
		listaCuentaPuzzle = new ListaCuentaPuzzle();
		listaRecords = new ListaRecords();
		listaPromedioCategoria = new ListaPromedioCategoria();
		listaPromedios = new ListaPromedios();
		listaAverages = new ListaPromedios();
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (session.getAttribute("idUsuario") != null) {
			idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());
		}
	}

	@PostConstruct
	public void init() {
		if (idUsuario != null) {
			listaCuentaPuzzle.setLista(estadisticasDAO.obtenerListaConteoPuzzles(idUsuario));
			listaRecords.setListaPBSingle(estadisticasDAO.obtenerListaPBSingle(idUsuario));
			listaPromedioCategoria.setLista(estadisticasDAO.obtenerListaPromediosCategoria(idUsuario,
					estadisticasDAO.consultarIdPuzzleMasPracticado(idUsuario)));
			listaPromedios.setLista(estadisticasDAO.obtenerListaPromediosTotales(idUsuario));
			listaAverages.setLista(estadisticasDAO.obtenerListaAverages(idUsuario));
		}
	}

	public List<CuentaPuzzle> obtenerListaConteoPuzzles() {
		return estadisticasDAO.obtenerListaConteoPuzzles(idUsuario);
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public EstadisticasDAO getEstadisticasDAO() {
		return estadisticasDAO;
	}

	public void setEstadisticasDAO(EstadisticasDAO estadisticasDAO) {
		this.estadisticasDAO = estadisticasDAO;
	}

	public ListaCuentaPuzzle getListaCuentaPuzzle() {
		return listaCuentaPuzzle;
	}

	public void setListaCuentaPuzzle(ListaCuentaPuzzle listaCuentaPuzzle) {
		this.listaCuentaPuzzle = listaCuentaPuzzle;
	}

	public ListaRecords getListaRecords() {
		return listaRecords;
	}

	public void setListaRecords(ListaRecords listaRecords) {
		this.listaRecords = listaRecords;
	}

	public ListaPromedioCategoria getListaPromedioCategoria() {
		return listaPromedioCategoria;
	}

	public void setListaPromedioCategoria(ListaPromedioCategoria listaPromedioCategoria) {
		this.listaPromedioCategoria = listaPromedioCategoria;
	}

	public ListaPromedios getListaPromedios() {
		return listaPromedios;
	}

	public void setListaPromedios(ListaPromedios listaPromedios) {
		this.listaPromedios = listaPromedios;
	}

	public ListaPromedios getListaAverages() {
		return listaAverages;
	}

	public void setListaAverages(ListaPromedios listaAverages) {
		this.listaAverages = listaAverages;
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

}
