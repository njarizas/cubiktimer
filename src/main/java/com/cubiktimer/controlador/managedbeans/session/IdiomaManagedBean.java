/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.controlador.managedbeans.session;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

/**
 *
 * @author Nelson
 */
@ManagedBean
@ViewScoped
public class IdiomaManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(IdiomaManagedBean.class);
	
	@ManagedProperty(value = "#{sesionManagedBean}")
    private SesionManagedBean sesionManagedBean;
    
    @PostConstruct
    public void init() {
    	log.trace("inicia init");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(sesionManagedBean.getLocale());
        sesionManagedBean.recursos = ResourceBundle.getBundle("texto",sesionManagedBean.getLocale());
        log.trace("fin init");
    }
    
    public void foo(){
    	log.trace("foo");
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
