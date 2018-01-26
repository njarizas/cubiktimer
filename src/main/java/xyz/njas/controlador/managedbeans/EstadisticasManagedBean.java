package xyz.njas.controlador.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import xyz.njas.controlador.managedbeans.session.ConfiguracionManagedBean;
import xyz.njas.controlador.managedbeans.session.SesionManagedBean;
import xyz.njas.modelo.dao.EstadisticasDAO;
import xyz.njas.modelo.rubik.estadisticas.ListaCuentaPuzzle;
import xyz.njas.modelo.rubik.estadisticas.ListaPromedios;

@ManagedBean
@ViewScoped
public class EstadisticasManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{configuracionManagedBean}")
	private ConfiguracionManagedBean configuracionManagedBean;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;
	
	HttpSession session;
	private Integer idUsuario;
	private EstadisticasDAO estadisticasDAO;
	private ListaCuentaPuzzle listaCuentaPuzzle;
	private ListaPromedios listaPromedios;

    public EstadisticasManagedBean() {
        estadisticasDAO = new EstadisticasDAO();
        listaCuentaPuzzle = new ListaCuentaPuzzle();
        listaPromedios = new ListaPromedios();
        session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if (session.getAttribute("idUsuario") != null) {
            idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());
        }
    }

    @PostConstruct
    public void init() {
    	listaCuentaPuzzle.setLista(estadisticasDAO.getListaConteoPuzzles(idUsuario));
    	listaPromedios.setLista(estadisticasDAO.getListaPromedios(idUsuario, 6));
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

	public ListaPromedios getListaPromedios() {
		return listaPromedios;
	}

	public void setListaPromedios(ListaPromedios listaPromedios) {
		this.listaPromedios = listaPromedios;
	}

	public ConfiguracionManagedBean getConfiguracionManagedBean() {
		if(this.configuracionManagedBean!=null){
			return configuracionManagedBean;
		} else{
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
