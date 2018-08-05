package xyz.njas.controlador.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import xyz.njas.controlador.managedbeans.session.ConfiguracionManagedBean;
import xyz.njas.controlador.managedbeans.session.SesionManagedBean;
import xyz.njas.modelo.dao.EstadisticasDAO;
import xyz.njas.modelo.dao.UsuarioDAO;
import xyz.njas.modelo.dto.UsuarioDTO;
import xyz.njas.modelo.rubik.estadisticas.ListaPromedios;

@ManagedBean
@ViewScoped
public class CompararEstadisticasManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{configuracionManagedBean}")
	private ConfiguracionManagedBean configuracionManagedBean;

	@ManagedProperty(value = "#{sesionManagedBean}")
	private SesionManagedBean sesionManagedBean;
	
	private HttpSession session;
	private Integer idUsuario;
	private Integer idAmigo;
	private ListaPromedios listaPromediosComparacion;
	private List<UsuarioDTO> listaAmigos;
	private EstadisticasDAO estadisticasDAO;
	private UsuarioDAO usuarioDAO;
	
	public CompararEstadisticasManagedBean() {
		estadisticasDAO = new EstadisticasDAO();
		usuarioDAO = new UsuarioDAO();
		listaPromediosComparacion = new ListaPromedios();
		listaAmigos = new ArrayList<UsuarioDTO>();
        session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if (session.getAttribute("idUsuario") != null) {
            idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());
        }
        if (idUsuario!=null) {
        	listaAmigos = usuarioDAO.consultarAmigosPorIdUsuario(idUsuario);
        	if (!listaAmigos.isEmpty()) {
        		idAmigo=listaAmigos.get(0).getIdUsuario();
        	}
        }
	}
	
    @PostConstruct
    public void init() {
    	if (idAmigo!=null) {
    		listaPromediosComparacion.setLista(estadisticasDAO.obtenerListaPromediosComparacion(idUsuario, idAmigo));
    	}
    }
    
    public void cambioAmigo(ValueChangeEvent event) {
		this.idAmigo = Integer.parseInt(event.getNewValue().toString());
		init();
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Integer getIdAmigo() {
		return idAmigo;
	}

	public void setIdAmigo(Integer idAmigo) {
		this.idAmigo = idAmigo;
	}

	public ListaPromedios getListaPromediosComparacion() {
		return listaPromediosComparacion;
	}

	public void setListaPromediosComparacion(ListaPromedios listaPromediosComparacion) {
		this.listaPromediosComparacion = listaPromediosComparacion;
	}

	public List<UsuarioDTO> getListaAmigos() {
		return listaAmigos;
	}

	public void setListaAmigos(List<UsuarioDTO> listaAmigos) {
		this.listaAmigos = listaAmigos;
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
