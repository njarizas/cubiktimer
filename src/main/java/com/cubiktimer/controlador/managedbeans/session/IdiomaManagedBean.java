package com.cubiktimer.controlador.managedbeans.session;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Nelson
 */
@ManagedBean
@ViewScoped
public class IdiomaManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{sesionManagedBean}")
    private SesionManagedBean sesionManagedBean;
    
    @PostConstruct
    public void init() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(sesionManagedBean.getLocale());
        sesionManagedBean.recursos = ResourceBundle.getBundle("texto",sesionManagedBean.getLocale());
    }
    
    public void foo(){
    	
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
